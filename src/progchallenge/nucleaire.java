package progchallenge;

import java.util.Scanner;

public class nucleaire {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int b= in.nextInt();
		double c = (b*1.5);
		double a = (b/1.5);
		if(a == (int)a){
			System.out.println(a*3);
		} else {
			System.out.println();
		}
		
		
	}
}
