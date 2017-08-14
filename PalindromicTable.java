import java.util.Scanner;
import java.util.Arrays;


public class PalindromicTable {
	
	private static int n, m, dp[][][][][], table[][], maxSize, maxRowStart, maxColStart, maxRowEnd, maxColEnd;
	private static Scanner sc;

    public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		table = new int[n][m];
		dp = new int[n][m][n][m][10];
		
		maxSize = 1;
		maxRowStart = maxColStart = maxRowEnd = maxColEnd = 0;
		
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
				table[i][j] = sc.nextInt();
			
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
					for(int i1 = 0; i1 <= i; i1++) {
						for(int j1 = 0; j1 <= j; j1++) {
							for(int k = 0; k < 10; k++) {
								if(i == i1 && j == j1)
									dp[i1][j1][i][j][table[i][j]] = 0;
								
								else if (i == i1) {
									dp[i1][j1][i][j][k] = dp[i1][j1][i][j-1][k];
									//dp[i1][j1][i][j][table[i][j]]++;
								}
								
								else if (j == j1) {
									dp[i1][j1][i][j][k] = dp[i1][j1][i-1][j][k];
									//dp[i1][j1][i][j][table[i][j]]++;
								}
								
								else {
									dp[i1][j1][i][j][k] = dp[i1][j1][i-1][j][k] + dp[i1][j1][i][j-1][k] - dp[i1][j1][i-1][j-1][k];
									//dp[i1][j1][i][j][table[i][j]]++;
								}
							}
							//System.out.println(Arrays.toString(dp[i1][j1][i][j]));
							//System.out.println("New: "+table[i][j]);
							dp[i1][j1][i][j][table[i][j]]++;
							//System.out.println("Starting at: ("+i1+", "+j1+")\t Ending at: ("+i+", "+j+")");
							
							//for(int k = 0; k < 10; k++)
							//System.out.println(Arrays.toString(dp[i1][j1][i][j]));
							
							
							int oddFrequency = 0;
							//int uniqueFrequency = 0;
							//int lastUniqueNum = 0;
							boolean nonZeroEvenFreqFound = false;
							for(int k = 0; k < 10; k++) {
								if(oddFrequency < 2) {
									/* if(dp[i1][j1][i][j][k] > 0) {
										uniqueFrequency++;
										lastUniqueNum = k;
									}*/
									if(k != 0 && dp[i1][j1][i][j][k] > 0 && dp[i1][j1][i][j][k]%2 == 0 && !nonZeroEvenFreqFound)
										nonZeroEvenFreqFound = true;
									if(dp[i1][j1][i][j][k] % 2 == 1)
										oddFrequency++;
								}
								
								else
									break;
							}
							
							//System.out.println(oddFrequency+"\n\n");
							if(oddFrequency < 2 && nonZeroEvenFreqFound) {
									int size = (i - i1 + 1) * (j - j1 + 1);
									if(maxSize < size) {
										maxSize = size;
										maxRowStart = i1;
										maxColStart = j1;
										maxRowEnd = i;
										maxColEnd = j;
									}
							}
								
						}
					}
				
			}
		}
		
		
		
		System.out.println(maxSize);
		System.out.println(maxRowStart + " " + maxColStart + " " + maxRowEnd + " " + maxColEnd);
	}
}
