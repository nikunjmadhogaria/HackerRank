import java.util.*;
public class KingdomDivision {
	
	static private long MOD = 1000000007;
	static private ArrayList<ArrayList<Integer>> graph;
	static private ArrayList<Integer> sequence;
	static private int parent[];
	static private long dpCounted[], dpMissed[];
	
	public static void main (String arg[]) {
		
		int n;
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		graph = new ArrayList<ArrayList<Integer>>(n);
		sequence = new ArrayList<Integer>(n);
		parent = new int[n];
		dpCounted = new long[n];
		dpMissed = new long[n];
		
		Arrays.fill(parent, -1);
		Arrays.fill(dpCounted, 1);
		Arrays.fill(dpMissed, 0);
		
		for(int i = 0; i < n; i++)
			graph.add(new ArrayList<Integer>());
		
		for(int i = 0; i < n - 1; i++) {
			int u = sc.nextInt() - 1; 
			int v = sc.nextInt() - 1;			
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		bfs();
		count();
		
		System.out.println(dpCounted[0]);
		
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		parent[0] = 0;
		
		while(!queue.isEmpty()) {
			int u = queue.remove();
			sequence.add(u);
			for(int v : graph.get(u)) {
				if(parent[v] == -1) {
					parent[v] = u;
					queue.add(v);					
				}
			}
		}
	}
	
	private static void count() {
		
		for(int i = sequence.size() - 1; i > 0; i--) {
			int u = sequence.get(i);
			if(graph.get(u).size() == 1) //u is a leaf node
				dpCounted[parent[u]] = 2;
		}
		
		for(int i = sequence.size() - 1; i > 0; i--) {
			int u = sequence.get(i);
			if(graph.get(u).size() != 1) { //if u is not a leaf node
				
				if(dpCounted[parent[u]] == 1) {
					/* dpCounted[parent[u]] = dpCounted[u] + dpMissed[u];
					dpMissed[parent[u]] = dpCounted[u]; */
					
					dpCounted[parent[u]] = (dpCounted[u] + dpMissed[u]) % MOD;
					dpMissed[parent[u]] = dpCounted[u];
				}
				else {
					/* dpCounted[parent[u]] = (dpCounted[parent[u]] * dpCounted[u]) + 
											( (dpCounted[u] * dpMissed[parent[u]])
											+ (dpCounted[parent[u]] * dpMissed[u])
											+ (dpMissed[parent[u]] * dpMissed[u]) ) / 2;
					
					dpMissed[parent[u]] = dpMissed[parent[u]] * dpCounted[u] / 2; */
					
					long t1,t2,t3,t4,t5;
					t1 = t2 = t3 = t4 = t5 = 0;
					
					t1 = (dpCounted[parent[u]] * dpCounted[u]) % MOD;
					
					t2 = (dpCounted[u] * dpMissed[parent[u]]) % MOD;
					t3 = (dpCounted[parent[u]] * dpMissed[u]) % MOD;
					t4 = (dpMissed[parent[u]] * dpMissed[u]) % MOD;
					
					t5 = (t5 + t2) % MOD;
					t5 = (t5 + t3) % MOD;
					t5 = (t5 + t4) % MOD;
					t5 = (t5 * 500000004) % MOD;
					
					dpCounted[parent[u]] = (t1 + t5) % MOD;
					
					dpMissed[parent[u]] = (dpMissed[parent[u]] * dpCounted[u]) % MOD;
					dpMissed[parent[u]] = (dpMissed[parent[u]] * 500000004) % MOD;
				}
			}
		}
		
	}
	
}