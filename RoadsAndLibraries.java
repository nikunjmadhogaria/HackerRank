import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class RoadsAndLibraries {
	private static int q, n, m, cRoad, cLib, minRoad;
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<ArrayList<Integer>> graph;
	private static int isVisited[];
	private static long minCost;
	private static String ans;

	
	public static void main(String args[]) {
		
		q = sc.nextInt();	
		ans = "";
		
		for(int j = 0; j < q; j++) {
		
			n = sc.nextInt();
			m = sc.nextInt();
			cLib = sc.nextInt();
			cRoad = sc.nextInt();
			
			graph = new ArrayList<ArrayList<Integer>>(n);
			isVisited = new int[n];
			
			Arrays.fill(isVisited, 0);
			
			for(int i = 0; i < n; i++)
				graph.add(new ArrayList<Integer>());
			
			for(int i = 0; i < m; i++) {
				int u = sc.nextInt() - 1;
				int v = sc.nextInt() - 1;
				graph.get(u).add(v);
				graph.get(v).add(u);			
			}
			
			minCost = minRoad = 0;
			for(int i = 0; i < n; i++) {
				if(isVisited[i] == 0) {
					minCost += (long)cLib;
					dfs(i);
				}
			}
			
			minCost += ((long)minRoad * (long)cRoad);
			
			minCost = minCost > ((long)n * (long)cLib) ? ((long)n * (long)cLib) : minCost;
			
			ans += minCost + "\n";
			
		}
		
		System.out.println(ans);
			
	}
	
	static void dfs(int u) {
		//System.out.println("Visiting city: "+u);
		isVisited[u] = 1;
		for(int v : graph.get(u)) {
			if(isVisited[v] == 0) {
				dfs(v);
				minRoad++;
			}
		}
	}
}