package progchallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
class Node {
	public int i;
	public ArrayList<Node> freres;
	public boolean infect;
	public Node (int i){
		this.i=i;
		freres = new ArrayList<Node>();
		infect = false;
	}
	
//	public int getDepth(){
//		if(infect){
//			return 0;
//		} else {
//			infect = true;
//			int cpt=1;
//			for(Node frere : freres){
//				cpt+=frere.getDepth(i);
//			}
//			return cpt;
//		}
//
//	}
//	public int getDepth(int team){
//		if(infect || team!=i){
//			return 0;
//		} else {
//			infect = true;
//			int cpt=1;
//			for(Node frere : freres){
//				cpt+=frere.getDepth(team);
//			}
//			return cpt;
//		}
//	}
}
public class Tache {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int l = in.nextInt();
		int c = in.nextInt();
		Node[][] tab = new Node[l][c];
		for (int i=0; i<l; i++){
			for (int j=0; j<c; j++){
				if (in.hasNext()){
					tab[i][j]= new Node(Integer.valueOf(in.next()));
				}
				if (i!=0){
					tab[i-1][j].freres.add(tab[i][j]);
					tab[i][j].freres.add(tab[i-1][j]);
				}
				if (j!=0){
					tab[i][j-1].freres.add(tab[i][j]);
					tab[i][j].freres.add(tab[i][j-1]);
				}
			}
		}
			long max = 0;
			for (int i=0; i<l; i++){
				for (int j=0; j<c; j++){
					if (!tab[i][j].infect){
						Stack<Node> s = new Stack<Node>();
						s.push(tab[i][j]);
						int team = tab[i][j].i;
						tab[i][j].infect = true;
						long cpt=0;
						while(!s.isEmpty()){
							Node n = s.pop();
							cpt++;
							List<Node> li = n.freres;
							for(Node no : li){
								if (no.i==team && !no.infect){
									no.infect = true;
									s.push(no);
								}
							}
						}
						if (max<cpt){
							max = cpt;
						}
					}
				}
			}
			System.out.println(max);
	}
}
