package progchallenge;

import java.util.Scanner;

public class codeNucleaire {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		int nb = 0;
		if (in.hasNextInt()){
			nb = in.nextInt();
		}
		int res=0;
		if(nb==0){
			res = 0;
		} else if (nb==1){
			res = 1;
		} else{
			int[]tab = new int[nb+1];
			for (int l :tab){
				l=0;
			}
			int nbAinserer = 3;
			int indice = 2;
			int temp;
			while(nbAinserer <= nb){
				tab[indice] = nbAinserer;
				tab[nbAinserer] = 3*indice;
				tab[nbAinserer/3] = indice;
				if (nbAinserer>3 && nbAinserer-indice == tab[nbAinserer/3]-nbAinserer){
					for (int i = (nbAinserer/3+1); i<indice; i++){
						if(tab[i]==0){
							tab[i]=tab[i-1]+1;
							tab[(tab[i-1]+1)]=3*i;
						}
					}
				}
				
				temp = nbAinserer;
				nbAinserer = indice*3;
				indice = temp;
			}
			res = tab[nb];
		}
		System.out.println(res);
	}
}
