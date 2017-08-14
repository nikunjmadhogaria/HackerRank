import java.util.Scanner;

public class PatternCount {
	
	private static Scanner sc;
	private static String s, ans;
	private static int q;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  
		ans = "";
		
		q = sc.nextInt();
		
		for(int i = 0; i < q; i++) {
			s = sc.next();
			
			int count = 0;
			boolean firstOneFound = false;
			boolean firstZeroFound = false;
			for(int j = 0; j < s.length(); j++) {
				
				if(firstOneFound) {
					if(s.charAt(j) != '0' && s.charAt(j) != '1') {
						firstOneFound = false;
						firstZeroFound = false;
					}
					else if(s.charAt(j) == '0' && !firstZeroFound)
						firstZeroFound = true;
					else if(s.charAt(j) == '1' && firstZeroFound) {
						firstZeroFound = false;
						count++;
					}
				}
					
				
				if(s.charAt(j) == '1' && !firstOneFound) {
					firstOneFound = true;
				}
				
			}
			
			ans += count + "\n";
		}
		
		System.out.println(ans);
    }
}
