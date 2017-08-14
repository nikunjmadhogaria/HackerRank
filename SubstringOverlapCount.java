import java.util.Scanner;
import java.util.Arrays;

public class SubstringOverlapCount {
	private static String string, substring;
	private static int sLen, subLen, count, matchPos[];
	private static Scanner sc;
	
	public static void main(String arg[]) {
		
		sc = new Scanner(System.in);
		
		System.out.print("Enter string: ");
		string = sc.next();
		System.out.print("Enter substring: ");
		substring = sc.next();
		
		sLen = string.length();
		subLen = substring.length();
		matchPos = new int[sLen];
		
		if(subLen > sLen)
			count = 0;
		
		else {
			int potentialSubCount = 0;
			char ch, chFirstPattern;
			chFirstPattern = substring.charAt(0);
			
			for(int i = 0; i < sLen; i++) {
				ch = string.charAt(i);
				if(ch == chFirstPattern) {
					potentialSubCount++;
				}
				
				for(int j = 0; j < potentialSubCount; j++) {
					if(matchPos[j] != -1 && ch == substring.charAt(matchPos[j])) {
						matchPos[j]++;
						if(matchPos[j] == subLen) {
							count++;
							matchPos[j] = -1;
						}
					}
					else
						matchPos[j] = -1;
				}
				
				//System.out.println("char: " + ch);
				//System.out.println("matchPos: "+Arrays.toString(matchPos));
				//System.out.println("jStart: "+jStart+"\n");
			}
		}
		
		System.out.println(count);
		
	}
	
}