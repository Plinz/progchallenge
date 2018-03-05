package progchallenge;

import java.util.Scanner;

public class SousListeEnZigZag {

	static int longueur(int c, int variation, int longueur, int n, int[][] tabList){
		int longueurFinale = longueur;
		int longueurFinaleTmp;
		
		for (int x= c+1; x<n; x++){

			if (variation == 1){
				
//				System.out.println(c+" c="+tabList[c][0]+" "+x+" x="+tabList[x][0]);
				if(tabList[c][0] < tabList[x][0]){
					longueurFinaleTmp = tabList[x][1];
					if(longueurFinaleTmp == -1){
						longueurFinaleTmp = 1+longueur(x, 1, longueur, n, tabList);
						tabList[x][1] = longueurFinaleTmp;
					}
				} else {
					longueurFinaleTmp = tabList[x][2];
					if (longueurFinaleTmp == -1){
						longueurFinaleTmp = 1+longueur(x,2,longueur,n,tabList);
						tabList[x][2] = longueurFinaleTmp;
					}
				}
			} else if (variation == 2){
				if(tabList[c][0] > tabList[x][0]){
					longueurFinaleTmp = tabList[x][2];
					if(longueurFinaleTmp == -1){
						longueurFinaleTmp = 1+longueur(x, 2, longueur, n, tabList);
						tabList[x][2] = longueurFinaleTmp;
					}
				} else {
					longueurFinaleTmp = tabList[x][3];
					if (longueurFinaleTmp == -1){
						longueurFinaleTmp = 1+longueur(x,3,longueur,n,tabList);
						tabList[x][3] = longueurFinaleTmp;
					}
				}
			} else {
				if(tabList[c][0] < tabList[x][0]){
					longueurFinaleTmp = tabList[x][3];
					if(longueurFinaleTmp == -1){
						longueurFinaleTmp = 1+longueur(x, 3, longueur, n, tabList);
						tabList[x][3] = longueurFinaleTmp;
					}
				} else {
				longueurFinaleTmp = 0;
				}
			}
			longueurFinale = longueurFinaleTmp<longueurFinale?longueurFinale:longueurFinaleTmp;
		}
		return longueurFinale;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nb = in.nextInt();
		int[][] list = new int[nb][4];
		int i=0;
		while (i<nb && in.hasNextInt()){
			int t = in.nextInt();
			list[i][0]=t;
			list[i][1]=-1;
			list[i][2]=-1;
			list[i][3]=-1;
			i++;
		}
		
		int longueurFinale = 0;
		for (int j=nb-1; j >=0; j--){
			int longueuTmp = SousListeEnZigZag.longueur(j, 1, 1, nb, list);
			longueurFinale = longueurFinale<longueuTmp?longueuTmp:longueurFinale;
		}
		System.out.println(longueurFinale);
	}
}
