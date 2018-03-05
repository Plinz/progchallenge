package progchallenge;

import java.util.Scanner;
import java.util.Stack;

class Bomb {
	public int l;
	public int c;
	public int team;
	public Bomb (int l, int c, int team){
		this.l=l;
		this.c=c;
		this.team=team;
	}
}

public class Pesanteur2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int c = in.nextInt();
		int l = in.nextInt();
		Bomb[][] tab = new Bomb[l][c];
		for (int i=0; i<l; i++){
			for (int j=0; j<c; j++){
				if (in.hasNext()){
					tab[i][j]= new Bomb(i,j,Integer.valueOf(in.next()));
				}
			}
		}
		int c1 = in.nextInt()-1;
		int l1 = l-in.nextInt();
		if (tab[l1][c1].team != 0){
			Stack<Bomb> s = new Stack<Bomb>();
			s.push(tab[l1][c1]);
			while (!s.isEmpty()){
				Bomb b = s.pop();
				if (b.l != 0 && tab[b.l-1][b.c].team == b.team && !s.contains(tab[b.l-1][b.c])){
					s.push(tab[b.l-1][b.c]);
				}
				if (b.l != (l-1) && tab[b.l+1][b.c].team == b.team && !s.contains(tab[b.l+1][b.c])){
					s.push(tab[b.l+1][b.c]);
				}
				if (b.c != 0 && tab[b.l][b.c-1].team == b.team && !s.contains(tab[b.l][b.c-1])){
					s.push(tab[b.l][b.c-1]);
				}
				if (b.c != (c-1) && tab[b.l][b.c+1].team == b.team && !s.contains(tab[b.l][b.c+1])){
					s.push(tab[b.l][b.c+1]);
				}
				b.team=0;
			}
			for (int i=0; i<c; i++){
				int cpt = 0;
				for(int j=l-1; j>=0; j--){
					if(tab[j][i].team==0){
						cpt++;
						boolean exist = false;
						for (int k=j-1; k>=0; k--){
							if(tab[k][i].team!=0){
								exist = true;
								break;
							}
						}
						if (exist){
							cpt=-90;
							for(int k=j; k>0; k--){
								tab[k][i].team=tab[k-1][i].team;
							}
							tab[0][i].team = 0;
							exist = false;
							j=l;
						}
					}
				}
				if (cpt==l){
					boolean emptyColomn = false;
					for (int m=i; m<c; m++){
						for (int d=0; d<l; d++){
							if(tab[d][m].team!=0){
								emptyColomn = true;
								break;
							}

						}
					}
					if (emptyColomn){
						for (int m=i; m<c-1; m++){
							for (int d=0; d<l; d++){
								tab[d][m].team=tab[d][m+1].team;
							}
						}
						for (int d=0; d<l; d++){
							tab[d][c-1].team=0;
						}
						i--;
					}
				}
//				System.out.println("");
//				for (int i2=0; i2<l; i2++){
//					String s2 = "";
//					for (int j=0; j<c; j++){
//						s2+=tab[i2][j].team;
//					}
//					System.out.println(s2.trim());
//				}
			}
		}
		System.out.println("");
		for (int i=0; i<l; i++){
			String s = "";
			for (int j=0; j<c; j++){
				s+=" "+tab[i][j].team;
			}
			System.out.println(s	);
		}
	}
}
