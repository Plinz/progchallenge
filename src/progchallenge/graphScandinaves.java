package progchallenge;

import java.util.ArrayList;
import java.util.Scanner;

class Graph{
	int [][] noeud;

}


public class graphScandinaves {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		int a = in.nextInt();
		ArrayList<Link> listLink = new ArrayList<Link>();
		ArrayList<Cell> listCell = new ArrayList<Cell>();
		System.out.println(s+" "+a);
		for (int i=0; i<s; i++){
			listCell.add(new Cell());
		}
		while(in.hasNext()){
			String tmp = in.next();
			tmp = tmp.substring(1, tmp.length()-1);
			String[] tab = tmp.split(",");
			listLink.add( new Link( listCell.get(Integer.valueOf(tab[1])), listCell.get(Integer.valueOf(tab[0])) ) );
		}
		int nbA = 0;
		int nbB = 0;
		
		for(Link l : listLink){
			
		}
	}
}
