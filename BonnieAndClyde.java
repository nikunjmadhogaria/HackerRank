import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class BonnieAndClyde {
		
	private static int n, m, q, u, v, w;
	private static Scanner sc;
	private static ArrayList<ArrayList<Integer>> graph;
	private static String ans = "";
	private static boolean foundU, foundV, foundPath;

    public static void main(String[] args) {
			
		sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		q = sc.nextInt();
		
		graph = new ArrayList<ArrayList<Integer>>(n);
		int isVisited[] = new int[n];
		
		for(int i = 0; i < n; i++)
			graph.add(new ArrayList<Integer>());
		
		for(int i = 0; i < m; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		for(int i = 0; i < q; i++) {
			u = sc.nextInt() - 1;
			v = sc.nextInt() - 1;
			w = sc.nextInt() - 1;
			
			Arrays.fill(isVisited, 0);
			foundU = foundV = foundPath = false;
			
			ans += w2u(w, isVisited) + "\n";
		}
			
		System.out.println(ans);	
		
	}
	
	private static String w2u(int curr, int isVisited[]) {
		
		isVisited[curr] = 1;
		
		//System.out.println("In w2u, visiting "+(curr+1)+"\n"+Arrays.toString(isVisited));
		
		if(curr == u) {
			//System.out.println("w = "+w);
			if( w2v(w, isVisited) ) {
				foundPath = true;
				//System.out.println("success");
				//return "YES";
			}
		}
		//System.out.println("neighbours of "+curr+": "+graph.get(curr)+"\n");
		for(int e : graph.get(curr) ) {
			int isVisitedCopy[] = new int[n];
			System.arraycopy(isVisited, 0, isVisitedCopy, 0, n);
			if(isVisitedCopy[e] == 0 && !foundPath) {
				w2u(e, isVisitedCopy);
			}
		}
		
		return foundPath ? "YES" : "NO";
	}
	
	private static boolean w2v(int curr, int isVisited[]) {			
		
		isVisited[curr] = 1;
		
		//System.out.println("In w2v, visiting "+(curr+1)	+"\n"+Arrays.toString(isVisited)+"\n");
		
		if(curr == v) {
			foundU = true;
			//System.out.println("Found v");
			//return true;
		}
		
		
		for(int e : graph.get(curr) ) {
			int isVisitedCopy[] = new int[n];
			System.arraycopy(isVisited, 0, isVisitedCopy, 0, n);
			if(isVisitedCopy[e] == 0 && !foundU) {
				w2v(e, isVisitedCopy);
			}
		}
		
		return foundU;
		
	}
	
}
