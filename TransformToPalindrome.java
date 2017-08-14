import java.util.Scanner;
import java.util.Arrays;


public class TransformToPalindrome {
	private static int n, k, m, input[], id[], sz[];;
	private static Scanner sc;

    public static void main(String[] args) {
		sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		m = sc.nextInt();
		
		quickUnion();
		
		for(int i = 1; i <= k; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			
			union(u - 1 , v - 1);
		}
		
		
		input = new int[m];
		
		for(int i = 0; i < m; i++) {
			input[i] = getRoot(sc.nextInt() - 1);
		}
		
		//System.out.println(Arrays.toString(input));
		
		//findLPS();
		System.out.println(findLPS());
		
		
		//System.out.println(Arrays.toString(parent));		
    }
	
	private static int findLPS() {
		
		int dp[][] = new int[m][m];
		
		for(int i = 0; i < m; i++)
			dp[i][i] = 1;
		
		
		
		for(int length = 1; length < m; length++) {			
			for(int i = 0; i < m - length; i++) {
				int j = i + length;
				if(input[i] == input[j])
					dp[i][j] = 2 + dp[i + 1][j - 1];
				else
					dp[i][j] = max(dp[i][j - 1], dp[i + 1][j]);
			}
		}
		
		return dp[0][m - 1];
		
	}
	
	private static int max(int x, int y) {		
		return (x > y) ? x : y;
	}
	
	private static void quickUnion() {
		
		int i;
		id = new int[n];
		sz = new int[n];
		
		for(i = 0; i < n; i++) {
			id[i] = i;
			sz[i] = 1;
		}
		
	}
	
	static int getRoot(int i) {
		
		while(id[i] != i){
			id[i] = id[id[i]];
			i = id[i];
		}
		
		return i;
		
	}
	
	public static void union(int i, int j) {
		
		int root_i, root_j;
		
		root_i = getRoot(i);
		root_j = getRoot(j);
		
		if(sz[root_i] < sz[root_j]){
			id[root_i] = root_j;
			sz[root_j] += sz[root_i]; 
		}
		else {
			id[root_j] = root_i;
			sz[root_i] += sz[root_j];
		}
	}
}
