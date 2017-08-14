import java.util.Scanner;
import java.util.Arrays;
public class Candies {
	
	private static int n, rating[], dp[];
	private static long sum;

    public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		rating = new int[n];
		dp = new int[n];
		
		boolean isDescending = true;
		
		rating[0] = sc.nextInt();
		for (int i = 1; i < n; i++) {
			rating[i] = sc.nextInt(); 
			if(rating[i - 1] < rating[i] && isDescending)
				isDescending = false;
		}
		
		if(!isDescending) {
			dp[0] = 1;
			for(int i = 1; i < n; i++) {
				if (rating[i] > rating[i - 1])
					dp[i] = dp[i - 1] + 1;
				
				else if (rating[i] == rating[i - 1])
					dp[i] = 1;
				
				else {
					dp[i] = 1;
					if(dp[i - 1] == 1) {
						int j = i - 1;
						while(j >= 0 && rating[j] > rating[j + 1]) {
							if(dp[j] == dp[j + 1])
								dp[j] = dp[j + 1] + 1;
							else
								break;
							j--;
						}
					}
				}
			}
			for(int i = 0; i < n; i++)
				sum += dp[i];
		}
		else
			sum = ((long)n * ((long)n + 1) ) / 2;
		
		System.out.println(sum);
    }
}
