package progchallenge;

import java.util.ArrayList;
import java.util.Scanner;

public class permutation {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i=1; i<=n; i++){
			list.add(i);
		}
		String ret="";
		int ind = k-1;
		while (!list.isEmpty()){
			while (ind>=list.size())
				ind -= list.size();
			ret += list.get(ind)+" ";
			list.remove(ind);
			ind+=(k-1);
		}
		System.out.println(ret.trim());
	}
}
