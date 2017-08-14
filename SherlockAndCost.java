import java.util.Scanner;
import java.lang.Math;
public class SherlockAndCost {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);      
        int t, n, i, j, dp[][], b[], sol[];
        
        t = sc.nextInt();
        sol = new int[t];
        
        for(i = 0; i < t; i++) {
            
            n = sc.nextInt();
            b = new int[n];
            dp = new int[n][2];            
            
            for(j = 0; j < n; j++)
                b[j] = sc.nextInt();
            
            dp[0][0] = dp[0][1] = 0;            
            for(j = 1; j < n; j++) {
                dp[j][0] = Math.max(dp[j-1][0], dp[j-1][1] + b[j-1] - 1);
                dp[j][1] = Math.max(dp[j-1][0] + b[j] - 1, dp[j-1][1] + Math.abs(b[j] - b[j-1]));
            }
            
            sol[i] = dp[n - 1][0] > dp[n - 1][1] ? dp[n - 1][0] : dp[n - 1][1];
           
        }
        
        for(i = 0; i < t; i++)
            System.out.println(sol[i]);
        
    }
}