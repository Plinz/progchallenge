package progchallenge;

import java.util.ArrayList;
import java.util.Scanner;

public class boiteNoire2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a=0;
		int b=0;
		if (in.hasNext()){
			a = Integer.valueOf(in.nextInt());
		}
		if (in.hasNext()){
			b = Integer.valueOf(in.nextInt());
		}
		if(a>75)
			a=16;
		String s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz#-_=+%?,.;/:!".substring(0, a);
		ArrayList<String> list = new ArrayList<String>();
		list.add(s);
		int index = 0;
		for (int i=0; i<9; i++){
			char c = s.charAt((index+b)%a);
			s=s.substring(0, index)+c+s.substring(index+1, s.length());
			s=s.substring(0, (index+b)%a)+"0"+s.substring((index+b)%a+1, s.length());
			index = ((index+b)%a);
			if (list.contains(s)){
				list.add(s);
				break;
			} else {
				list.add(s);
			}
		}
		for(String st : list){
			System.out.println(st);
		}
		
	}
}
