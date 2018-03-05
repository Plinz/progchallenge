package progchallenge;

import java.util.Scanner;

public class pliage {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int pli = in.nextInt();
		String ret = "1";
		for (int i=1; i<pli; i++){
			String tmp = "";
			boolean flag = true;
			for (char c : ret.toCharArray()){
				if (flag){
					tmp += "1"+c;
					flag = false;
				} else {
					tmp += "0"+c;
					flag = true;
				}
			}
			if (flag){
				tmp += "1";
			} else {
				tmp += "0";
			}
			ret = tmp;
		}
		
		System.out.println(ret);
	}
}
