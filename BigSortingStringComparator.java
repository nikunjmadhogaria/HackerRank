import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class BigSortingStringComparator {
	
	public static void main(String arg[]) {
		
		int n;
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		String input[] = new String[n];
		
		for(int i = 0; i < n; i++)
			input[i] = sc.next();
		
		Arrays.sort(input, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return StringComparedAsInteger(s1, s2);
			}
		});
		
		for(int i = 0; i < n; i++)
			System.out.println(input[i]);
	}
	
	static int StringComparedAsInteger(String s1, String s2) {
		if ( s1.length() > s2.length() ) return 1;
		if ( s1.length() < s2.length() ) return -1;
		for(int i = 0; i < s1.length(); i++) {
			if( (int)s1.charAt(i) > (int)s2.charAt(i) ) return 1;
			if( (int)s1.charAt(i) < (int)s2.charAt(i) ) return -1;
		}
		return 0;
	}
	
}