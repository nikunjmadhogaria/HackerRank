import java.util.Scanner;
public class SamAndSubstrings {
	
	private static Scanner sc;
	private static String input;
	private static long dp[], sum, MOD = 1000000007;

    public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		input = sc.next();
		int n = input.length();
		
		dp = new long[n];
		
		sum = dp[0] = Long.parseLong(input.charAt(0) + "");
		
		for(int i = 1; i < n; i++) {
			//dp[i] = (dp[i - 1] * 10) + (Long.parseLong(input.charAt(i) + "") * (i + 1));
			//sum += dp[i];
			
			dp[i] = (dp[i - 1] * 10) % MOD;
			dp[i] = ( dp[i] + (Long.parseLong(input.charAt(i) + "") * (i + 1)) ) % MOD;
			sum = (sum + dp[i]) % MOD;
		}
		
		System.out.println(sum);
			
    }
}
