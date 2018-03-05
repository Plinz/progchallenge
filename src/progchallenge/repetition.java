package progchallenge;

import java.io.IOException;
import java.util.Scanner;

public class repetition {

	public static void main(String[] args) throws IOException {
	    Scanner line = new Scanner(System.in); 
	    String all = "";
	    while (line.hasNextLine()) {
	        String lineStr = line.nextLine();
	        all += lineStr+"\n";
	    }
		String longest = longestDuplicate(all.trim());
	    System.out.println(longest);
	}

	public static String longestDuplicate(String toto) {
		String longest = "";
	    for (int i=toto.length()-1 ; i!=-1; i--){
	    	String sub = toto.substring(i, toto.length());
	    	if (toto.substring(0,toto.length()-1).contains(sub)){
	    		longest=sub;
	    	}
	    }
	    return longest;
	}
	
}
