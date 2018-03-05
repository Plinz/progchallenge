package progchallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Trolls {
	
	static String ajoutInCell(char troll, char orientation, String cell){
//		System.out.println("troll="+troll+" ori="+orientation+" cell="+cell);
		String newCell = "";
		if (cell == null || cell.equals("") || cell.equals(".")){
			newCell = ""+troll+orientation;
		} else {
			char[] list = cell.toCharArray();
			boolean flag = false;
			boolean write = false;
			for (char c : list){
				if (flag){
					switch (c) {
					case 'n':
						if(orientation=='n'){
							newCell+=orientation+""+c+"";
							flag = false;
							write=true;
						} else {
							newCell+=c+"";
						}
					case 'o':
						if (orientation =='n' || orientation =='o'){
							newCell+=orientation+""+c+"";
							flag = false;
							write=true;
						} else {
							newCell+=c+"";
						}
						break;
					case 's':
						if (orientation=='n' || orientation=='o' || orientation=='s'){
							newCell+=orientation+""+c+"";
							flag = false;
							write=true;
						} else {
							newCell+=c+"";
						}
						break;
					case 'e':
						if (orientation=='n' || orientation=='o' || orientation=='s' || orientation=='e'){
							newCell += orientation+""+c+"";
							flag = false;
							write=true;
						} else {
							newCell+=c+"";
						}
						break;
					default :
						flag = false;
						newCell+=orientation+""+c;
						write=true;
						
					}
				} else {
					if (c == troll){
						flag = true;
					}
					newCell += c+"";
				}
			}
			if (!write){
				if(flag){
					newCell+=orientation+"";
				} else {
					newCell+=troll+""+orientation+"";
				}
			}
		}
		return newCell;
	}
	
	static String[][] move(char troll, char orientation, String[][] oldTab, String[][] tab, int h, int w){
		switch (orientation){
		case 'n':
			if (h==0 || oldTab[w][h-1].equals("#") || oldTab[w][h-1].equals("*")){
				tab[w][h] = ajoutInCell(troll, 'o', tab[w][h]);
			} else {
				tab[w][h-1] = ajoutInCell(troll, 'n', tab[w][h-1]);
				if (tab[w][h] == null || tab[w][h].equals("") || tab[w][h].equals(".")){
					tab[w][h] = ".";
				}
			}
			break;
		case 'o':
			if (w==0 || oldTab[w-1][h].equals("#") || oldTab[w-1][h].equals("*")){
				tab[w][h] = ajoutInCell(troll, 's', tab[w][h]);
			} else {
				tab[w-1][h] = ajoutInCell(troll, 'o', tab[w-1][h]);
				if (tab[w][h] == null || tab[w][h].equals("") || tab[w][h].equals(".")){
					tab[w][h] = ".";
				}			}
			break;
		case 's':
			if (h==oldTab[0].length-1 || oldTab[w][h+1].equals("#") || oldTab[w][h+1].equals("*")){
				tab[w][h] = ajoutInCell(troll, 'e', tab[w][h]);
			} else {
				tab[w][h+1]= ajoutInCell(troll, 's', tab[w][h+1]);	
				if (tab[w][h] == null || tab[w][h].equals("") || tab[w][h].equals(".")){
					tab[w][h] = ".";
				}			}
			break;
		case 'e':
			if (w==oldTab.length-1 || oldTab[w+1][h].equals("#") || oldTab[w+1][h].equals("*")){
				tab[w][h] = ajoutInCell(troll, 'n', tab[w][h]);
			} else {
				tab[w+1][h] = ajoutInCell(troll, 'e', tab[w+1][h]);
				if (tab[w][h] == null || tab[w][h].equals("") || tab[w][h].equals(".")){
					tab[w][h] = ".";
				}			}
			break;
		}
		return tab;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nbTours = in.nextInt();
		int width = in.nextInt();
		int heigth = in.nextInt();
		String [][] tab = new String[width][heigth];
		for (int i = 0 ; i<heigth; i++){
			for (int j = 0; j<width; j++){
				if (in.hasNext()){
					tab[j][i] = in.next();
				}
			}
		}
		List<String> except = new ArrayList<String>();
		
		for (int i=0; i<nbTours; i++){
			String[][] newTab = new String [width][heigth];
			for (int h=0; h<heigth; h++){
				for (int w=0; w<width; w++){
					switch (tab[w][h]) {
					case "." : 
						if (newTab[w][h] == null){
							newTab[w][h] = ".";
						}
						break;
					case "#" :
						newTab[w][h] = "#";
						break;
					case "*" :
						newTab[w][h] = "*";
					default :
						if(i!=0 && tab[w][h].length() > 3 && !except.contains(""+w+""+h+""+(i-1))){
							newTab[w][h]="*";						
						} else {
							if(except.contains(""+w+""+h+""+(i-1))){
								except.remove(""+w+""+h+""+(i-1));
							}
							char[] list = tab[w][h].toCharArray();
							char tribu='a';
							for (char c : list){
								switch (c) {
								case 'n' :
								case 'o' :
								case 's' :
								case 'e' :
									newTab = move(tribu, c, tab, newTab,h,w);
									break;
								default :
									tribu = c;
									break;
								}
							}
						}
					}
				}
			}
			tab = newTab;
			for (int h=0; h<heigth; h++){
				for (int w=0; w<width; w++){
					if(tab[w][h].length() == 3){
						char o1 = tab[w][h].charAt(1);
						char o2 = tab[w][h].charAt(2);
						if (o1 != 'n' && o2 != 'n'){
							tab[w][h]=tab[w][h].charAt(0)+"n"+o1+""+o2;
						} else if (o1 != 'o' && o2 != 'o'){
							tab[w][h]=tab[w][h].charAt(0)+""+o1+"o"+o2;
						} else {
							tab[w][h] += "s";
						}
						except.add(""+w+""+h+""+i);
					} else if(tab[w][h].length() > 3){
						tab[w][h]="*";						
					}
				}
			}
		}
			for (int h=0; h<heigth; h++){
				for (int w=0; w<width; w++){
					if (w+1!=width){
						System.out.print(tab[w][h]+"\t");
					} else {
						System.out.println(tab[w][h]);
					}
					
				}
			}
		
	}
}
