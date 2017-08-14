import java.util.Scanner;
public class ShortPalindrome{
	
	static Scanner sc = new Scanner(System.in);
	static String input, modInput, subSeq;
	static long tbl[][], subSeqCount;
	
	public static void main(String arg[]){
		
		input = sc.next();
		
		int i, j;
		char fChar, sChar;
		
		for(i = 97; i <= 122; i++){
			for(j = 97; j <= 122; j++){
				
				fChar = (char)(i);
				sChar = (char)(j);
				
				if(hasPalindrome(fChar,sChar))
					getSubSeqCount(fChar,sChar);
				
			}
		}
		
		System.out.println(subSeqCount);
	}
	
	static boolean hasPalindrome(char fChar,char sChar) {
		
		boolean flag = false;
		
		if(fChar == sChar){
			int count = input.length() - input.replace(fChar+"","").length();
			if (count >= 4)
				flag = true;
		}
		
		else{
			
			int fCharFirstPos = input.indexOf(fChar);
			int fCharLastPos = input.lastIndexOf(fChar);
			
			if(fCharFirstPos < fCharLastPos){ //if more than once occurence of 1st character
			
				int sCharFirstPos = input.indexOf(sChar, fCharFirstPos);
				int sCharLastPos = input.lastIndexOf(sChar, fCharLastPos);
				
				if(sCharFirstPos < sCharLastPos) //if the 2nd character occurs more than once between first and last occurence of 1st character
					flag = true;
			}
		}
		
		return flag;

	}

	static void getSubSeqCount(char fChar, char sChar) {	
		
		if(fChar != sChar) {
			
			modInput = input;
			int i, j;
			
			modInput = modInput.substring(modInput.indexOf(fChar));
			modInput = modInput.substring(0, modInput.lastIndexOf(fChar) + 1);
			
			//System.out.print(modInput + ", ");
			
			for(i = 97; i < 122; i++) {
				if(i != (int)fChar && i != (int)sChar)
					modInput = modInput.replace((char)(i)+"","");
			}
			
			//System.out.println(input + ", (" + fChar + ", " + sChar + ") " + modInput);
			
			subSeq = "" + fChar + sChar + sChar + fChar;
			//System.out.println(subSeq);
			tbl = new int[modInput.length() + 1][subSeq.length() + 1];
			
			for(i = 0; i < tbl.length; i++) {
				for(j = 0; j < tbl[i].length; j++) {
					tbl[i][j] = fillTable(i,j);
					//System.out.print(tbl[i][j] + "\t");
				}
				//System.out.println();
			}
				
			subSeqCount = (subSeqCount + tbl[modInput.length()][subSeq.length()]) % 1000000007;
			
		}
		
		else { //when both 1st & 2nd character are same
			
			int charCount, i;
			long nC4Mod;
			
			charCount = input.length() - input.replace(fChar+"", "").length();
			nC4Mod = charCount;
			
			for(i = 1; i <= 3; i++)
				nC4Mod = (nC4Mod * (charCount - i)) % 1000000007;
			
			nC4Mod = (nC4Mod * 41666667) % 1000000007; //41666667 is modInverse for 24 (i.e. 4!), m = 1000000007
			
			subSeqCount = (subSeqCount + nC4Mod) % 1000000007;
		
		}
			
	}
	
	static long fillTable(int i, int j) {
		
		if(j == 0)
			return 1;
		
		if(i == 0)
			return 0;
		
		long val = 0;
		
		val = (val + tbl[i - 1][j]) % 1000000007;
		
		char modFChar, subFChar;

		modFChar = modInput.charAt(modInput.length() - i);
		subFChar = subSeq.charAt(subSeq.length() - j);
		
		//System.out.print(modFChar + ", " + subFChar + " ");
		
		if(modFChar == subFChar)
			val = (val + tbl[i - 1][j - 1]) % 1000000007;
		
		return val;
	}
}