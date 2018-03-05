package progchallenge;

import java.util.ArrayList;
import java.util.Scanner;

public class Syracuse {
	
	public static boolean looper (long n, ArrayList<Long> loop){
		int i =0;
		while(i != loop.size() && loop.get(i) != n)
			i++;
		return i!= loop.size() && loop.get(i) == n;
	}
	
	public static void syra (long n, int nbFrac, int[] num, int[] denom, ArrayList<Long> loop){
		boolean b = true;
		if(n != 0){
			int i = 0;
			while(b && i != nbFrac){
				if ((double)n/denom[i] == ((int)n/denom[i])){
					n = n * num[i] / denom[i];
					if(n > (long)((Math.pow(2, 30)-1))){
						System.out.println("OVERFLOW");
						b = false;
						break;
					}
					if (Syracuse.looper(n, loop)){
						System.out.println("LOOP");
						b = false;
						break;
					}
					loop.add(i, n);
					i = 0;
				}
				else
					i++;
			}
		}
		if(b)
			System.out.println(n);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()){
			String a = in.nextLine();
			String[] tab = a.trim().split(" ");
			long n = (long) Integer.valueOf(tab[0]);
			int nbFrac = tab.length-1;
			int[] num = new int[nbFrac];
			int[] denom = new int[nbFrac];
			for (int i=0; i<nbFrac; i++){
				String[] s = tab[i+1].split("/");
				num[i]=Integer.valueOf(s[0]);
				denom[i]=Integer.valueOf(s[1]);
			}
			ArrayList<Long> loop = new ArrayList<Long>(); 
		boolean b = true;
			if(n != 0){
				int i = 0;
				while(b && i != nbFrac){
					if ((double)n/denom[i] == ((int)n/denom[i])){
						n = n * num[i] / denom[i];
						if(n > (long)((Math.pow(2, 30)-1))){
							System.out.println("OVERFLOW");
							b = false;
							break;
						}
						if (Syracuse.looper(n, loop)){
							System.out.println("LOOP");
							b = false;
							break;
						}
						loop.add(n);
						i = 0;
					}
					else
						i++;
				}
			}
			if(b)
				System.out.println(n);
		}
	}
}
