package progchallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

class Personne {
	String name;
	ArrayList<Personne> amis;
	
	public Personne (String name){
		this.name = name;
		this.amis = new ArrayList<Personne>();
	}
}

public class ReseauSocial {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nbPerson = in.nextInt();
		HashMap<String,Personne> map = new HashMap<String,Personne>();
		ArrayList<Personne> list = new ArrayList<Personne>();
		for (int i=0; i<nbPerson; i++){
			if (in.hasNext()){
				String s = in.next();
				Personne p = new Personne(s);
				map.put(s, p);
				list.add(p);
			}
		}
		while (in.hasNext()){
			String s = in.nextLine();
			if (s.contains("est ami avec")){
				String[] coco = s.split(" est ami avec ");
				Personne p1 = map.get(coco[0].trim());
				Personne p2 = map.get(coco[1].trim());
				p1.amis.add(p2);
				p2.amis.add(p1);  
			} else 	if (s.contains("est amie avec")){
				String[] coco = s.split(" est amie avec ");
				Personne p1 = map.get(coco[0].trim());
				Personne p2 = map.get(coco[1].trim());
				p1.amis.add(p2);
				p2.amis.add(p1);  
			}
		}
		
		boolean ok = false;
		ArrayList<String> reponse = new ArrayList<String>();
		for (Personne p : list){
			ArrayList<Personne> pass = new ArrayList<Personne>();
			Stack<Personne> s = new Stack<Personne>();
			s.push(p);
			while(!s.isEmpty()){
				Personne p1 = s.peek();
				if (pass.contains(p1)){
					pass.remove(s.pop());
				} else {
					pass.add(p1);
					if(pass.size()!=nbPerson){
						ArrayList<Personne> pAmis = p1.amis;
						boolean stillFriend = false;
						for(Personne pAmi : pAmis){
							if (!pass.contains(pAmi)){
								s.push(pAmi);
								stillFriend=true;
							}
						}
					} else {
						ok = true;
						String print = "";
						for(Personne pPrint : pass){
							print += pPrint.name+" ";
						}
						reponse.add(print);
						while (!s.isEmpty() && s.peek() == pass.get(pass.size()-1))
							pass.remove(s.pop());
					}
				}
			}
		}
		if (!ok){
			System.out.println("Pas de solution");
		} else {
			Collections.sort(reponse);
			for (String s : reponse){
				System.out.println(s);
			}
		}
	}
}
