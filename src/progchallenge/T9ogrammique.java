package progchallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Touche{
	int indice;
	String lettres;
	public Touche(int indice, String lettres){
		this.indice=indice;
		this.lettres=lettres;
	}
}

public class T9ogrammique {
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] mots = null;
		ArrayList<Touche> touches = new ArrayList<Touche>();
		
		if (in.hasNextLine()){
			mots = in.nextLine().trim().split(" ");
		}
		int []comb = null;
		if (in.hasNextLine()){
			String[] tmp = in.nextLine().trim().split(" ");
			comb = new int[tmp.length];
			for(int i=0; i<tmp.length; i++){
				comb[i] = Integer.valueOf(tmp[i]);
			}
		}
//		int i2=0;
		while(in.hasNextLine()){
			String[] ti = in.nextLine().trim().split(":");
			if(ti.length==2){
				touches.add(new Touche(Integer.valueOf(ti[0]), ti[1]));
			}
//			i2++;
		}
		
		ArrayList<String> motResultat = new ArrayList<String>();
		int distanceResultat = -1;
		for (String mot : mots){
			int distance = 999999999;
			if(mot.length() == comb.length){
				char[] tabC = mot.toCharArray();
				distance = calculeDistance(touches, comb, tabC);
			} else if (mot.length()<comb.length){
				int decalage = comb.length-mot.length()+1;
				int i=0;
//				System.out.println("i="+i+" decalage="+decalage);
				while(i<decalage){
					int distanceTmp = decalage-1;
					char[] tabC = mot.toCharArray();
					int[]comb2 = new int[mot.length()];
					for (int j=i ;j<mot.length()+i; j++){
						comb2[j-i]=comb[j];
//						System.out.println("j="+j+" i="+i+" comb="+comb[j]);
					}
					distanceTmp += calculeDistance(touches, comb2, tabC);
					if(distance>distanceTmp)
						distance = distanceTmp;
					i++;
				}
			} else {
				int decalage = mot.length()-comb.length+1;
				int i=0;
				while(i<decalage){
					int distanceTmp = decalage-1;
					char[] tabC = mot.substring(i,mot.length()-(decalage-i)+1).toCharArray();
//					System.out.println("mot="+mot+" motle="+mot.length()+" dec="+decalage+" i="+i+" sub="+mot.substring(i,mot.length()-(decalage-i)+1));
					distanceTmp += calculeDistance(touches, comb, tabC);
//					System.out.println("distanceTmp="+distanceTmp);
					if(distance>distanceTmp)
						distance = distanceTmp;
					i++;
				}
			}
//			System.out.println("distanceTmp="+distance+" distanceResultat="+distanceResultat);
			if (distanceResultat == -1 || distanceResultat==distance){
				motResultat.add(mot);
				distanceResultat = distance;
			} else if(distanceResultat>distance){
				motResultat.clear();
				motResultat.add(mot);
				distanceResultat = distance;
			}
		}
		Collections.sort(motResultat);
		String s ="";
		for (String sRes : motResultat)
			s+=sRes+" ";
		System.out.println(s.trim());
		
	}

	public static int calculeDistance(ArrayList<Touche> touches, int[] comb,  char[] tabC) {
		int distance=0;
		for(int j=0; j<tabC.length; j++){
			char c = tabC[j];
			boolean find = false;
			for(int i=0; i<touches.size(); i++){
				String touche = touches.get(i).lettres;
				if (touche.contains(c+"")){
					find = true;
//					System.out.println("c="+c+" touche="+touches.get(i).indice+" comb[j]="+comb[j]);
					if (touches.get(i).indice!=comb[j]){
						distance++;
					}
					break;
				}
			}
			if(!find){
				distance++;
			}
		}
//		System.out.println("distance="+distance);
		return distance;
	}
}
