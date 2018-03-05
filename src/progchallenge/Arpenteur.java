package progchallenge;

import java.util.Scanner;
import java.util.Stack;

class toto{

	int taille1;
	int taille2;
	int noeud;
	public toto(int taille1, int taille2, int noeud) {
		this.taille1 = taille1;
		this.taille2 = taille2;
		this.noeud = noeud;
	}
}

public class Arpenteur {
	int nbBaguettes;
	int[] baguettes;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Arpenteur a = new Arpenteur();
		
		if(in.hasNext()){
			a.nbBaguettes = in.nextInt();
			a.baguettes = new int [a.nbBaguettes];
		}
		int i=0;
		int taille=0;
		while(in.hasNext() && i < a.nbBaguettes){
			a.baguettes[i] = in.nextInt();
			taille+=a.baguettes[i];
			i++;
		}
		
//		System.out.println("pass");
//		System.out.println(" "+a.process(0, 0, 0));
		
		Stack<toto> s = new Stack<toto>();
		s.push(new toto(0,0,0));
		
		while(!s.isEmpty()){
			toto t = s.pop();
			if(t.noeud >= a.nbBaguettes){
				int tailleTotale = t.taille1+t.taille2;
				taille = taille<tailleTotale?taille:tailleTotale;
			}
	        else if(t.taille1 == 0 && t.taille2 == 0){
	        	s.push(new toto(a.baguettes[t.noeud], 0, t.noeud+1));
	        }
			else{
				int tailleG1 = t.taille2 + a.baguettes[t.noeud];
				int tailleD1 = t.taille1 - a.baguettes[t.noeud] < 0 ? 0 : t.taille1 - a.baguettes[t.noeud];
				int tailleG2 = t.taille1 + a.baguettes[t.noeud];
				int tailleD2 = t.taille2 - a.baguettes[t.noeud]  <0 ? 0 : t.taille2 - a.baguettes[t.noeud];
				if (tailleG1+tailleD1<taille)
					s.push(new toto(tailleG1, tailleD1, t.noeud+1));
				if (tailleG2+tailleD2<taille)
					s.push(new toto(tailleG2, tailleD2, t.noeud+1));
			}
		}
		System.out.println(" "+taille);
	}
}
