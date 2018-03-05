package progchallenge;

import java.io.InputStreamReader;

public class Boggle1 {
	static int n, nb;
	static String[] mots;
	static int[] marquemos,marquelettre;
	static String motDonee;
	
	public static boolean estToutMarquerLettre(){
		int i = 0;
		while(i != motDonee.length() && marquelettre[i] != -1){
			i++;
		}
		return i == motDonee.length();
	}
	
	public static boolean appartient(int lettre, int mot){
		int i=0;
		while (i != mots[mot].length() && motDonee.charAt(lettre) != mots[mot].charAt(i)){
			i++;
		}
		return i != mots[mot].length();
	}
	
	public static void afficher(){
		System.out.println();
		int i=0;
		while(i != marquelettre.length){
			int j=0;
			if(marquelettre[i] != -1){
				while(j != mots[marquelettre[i]].length() && mots[marquelettre[i]].charAt(j) != motDonee.charAt(i)){
					j++;
				}
				switch(j){
				case 0:
					j=4;
					break;
				case 1:
					j=3;
					break;
				case 2:
					j=5;
					break;
				case 3:
					j=1;
					break;
				case 4:
					j=0;
				case 5:
					j=2;
					break;
				}
				System.out.print(mots[marquelettre[i]].charAt(j));
			}
			i++;
		}
		System.out.println();
	}
	
	public static void traiter(){
		marquelettre = new int[motDonee.length()];
		marquemos = new int[nb];
		int i=0;
		while(i != motDonee.length()){
			marquelettre[i] = -1;
			i++;
		}
		i=0;
		while(i != nb){
			marquemos[i] = -1;
			i++;
		}
		int lettre=0;
		int mot = 0;
		
		while(!estToutMarquerLettre()){
			mot=0;
			while(mot != mots.length){
				if(marquemos[mot] == -1 && appartient(lettre, mot)){
					marquemos[mot] = lettre;
					marquelettre[lettre] = mot;
					break;
				}
				mot++;
			}
			if(mot == mots.length){
				mot = 0 + (int)(Math.random()*mots.length);
				while(!appartient(lettre, mot)){
					mot =0 + (int)(Math.random()*mots.length);
				}
				if(marquemos[mot] != -1 && appartient(lettre, mot)){
					marquelettre[marquemos[mot]] = -1;
					marquemos[mot] = lettre;
					marquelettre[lettre] = mot;
				}
			}
			lettre = 0 + (int)(Math.random()*motDonee.length());
			while(!estToutMarquerLettre() && marquelettre[lettre] != -1){
				lettre = 0 + (int)(Math.random()*motDonee.length());
			}
		}
		afficher();
	}
	
	public static void lire(){
		try{
			n=0;
			nb=0;
			InputStreamReader ipsr = new InputStreamReader(System.in);
			int i = ipsr.read();
			while (i != -1 && Character.isWhitespace((char) i)){
				i = ipsr.read();
			}
			int permu = 0;
			String chaine = "";
			while( i!= -1){
				char c = (char) i;
				if(Character.isWhitespace(c)){
					if(n==0){
						nb = Integer.parseInt(chaine);
						nb = nb*nb;
						mots = new String[nb];
						n++;
					} else if (n <= nb){
						mots[n++-1] = chaine;
					} else {
						motDonee = chaine;
					}
					chaine = "";
					while (i != -1 && Character.isWhitespace((char) i)){
						i = ipsr.read();
					}
				} else {
					chaine += c;
					i =  ipsr.read();
				}
			}
			ipsr.close();
		}
		catch(Exception e){
			System.out.print(e.toString());
		}
	}
	
	public static void main(String[] args) {
		lire();
		traiter();
	}
}
