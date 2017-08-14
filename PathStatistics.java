import java.util.*;

import java.io.*;

public class PathStatistics {
    
    

    private static int n, q, lr[][], lrSorted[][], l, nodeVal[], numVal[][], lPro, isVisited[], numFreq[][], lCount[], uniqueInt;
    private static ArrayList<ArrayList<Integer>> graph; 
    private static HashMap<Long, Integer> lrk = new HashMap<Long, Integer>(100000);
    private static HashMap<Long, ArrayList<Integer>> kForlr  = new HashMap<Long, ArrayList<Integer>>(100000);
    private static long key;
    private static Set<Integer> set=new HashSet<Integer>();
    private static List<Integer> list;
	private static HashMap<Integer, Integer> intRank = new HashMap<Integer, Integer>(50000);
	private static Scanner sc;
    
    public static void main(String[] args) throws IOException {
        
        sc = new Scanner(new File("pathStatisticsinput05.txt")); 
		PrintStream out = new PrintStream(new FileOutputStream("pathStatisticsOutput.txt"));
        
        n = sc.nextInt();
        q = sc.nextInt();
        
        nodeVal = new int[n];
        for(int i = 0; i < n; i++) {
            nodeVal[i] = sc.nextInt();
			set.add(nodeVal[i]);
        }
		uniqueInt = set.size();
        numFreq = new int[n][uniqueInt];
        numVal = new int[uniqueInt][2];
        isVisited = new int[n];
		
		list = new ArrayList<Integer>(set);
		Collections.sort(list);
		
		for(int j = 0; j < uniqueInt; j++)
            intRank.put(list.get(j), j);
		
        graph = new ArrayList<ArrayList<Integer>>(n);
        for(int i = 0; i < n; i++)
            graph.add(new ArrayList<Integer>());
        
        for(int i = 1; i <= n-1; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        lr = new int[q][3];
        lrSorted = new int[q][3];                 
        
        lCount = new int[50000];
        for(int i = 0; i < q; i++) {
            lr[i][0] = sc.nextInt() - 1;
            lr[i][1] = sc.nextInt() - 1;
            lr[i][2] = sc.nextInt();  
            
            lCount[lr[i][0]]++;
            
            key = getKey( (long)lr[i][0], (long)lr[i][1] ); 
            if(kForlr.get(key) == null) {
                kForlr.put(key, new ArrayList<Integer>());
                kForlr.get(key).add(lr[i][2]);
            }
            else 
                kForlr.get(key).add(lr[i][2]);
            
            lrSorted[i][0] = lr[i][0];
            lrSorted[i][1] = lr[i][1];
            lrSorted[i][2] = lr[i][2];
        }
        
        
        Arrays.sort(lrSorted, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
               return Integer.compare(a[1], b[1]);
            }
        });
        
        Arrays.sort(lrSorted, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
               return Integer.compare(a[0], b[0]);
            }
        });
        
        int i = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        
        while(i < q) {
            
            l = lrSorted[i][0];  
            
            lPro = 0;
            
			key = getKey(l, l);
			if(kForlr.get(key) != null) {
				lrk.put( getKey(key, 1), nodeVal[l] );
				lPro++;
				i++;
			}
			
			if(lPro < lCount[l]) {
			
				Arrays.fill(isVisited, 0);
				for(int j = 0; j < n; j++)
					Arrays.fill(numFreq[j], 0);            
				
				isVisited[l] = 1;
				queue.clear();
				queue.add(l);
				numFreq[l][intRank.get(nodeVal[l])]++;

				bfs: while(!queue.isEmpty()) {
                    
					int u = queue.remove();
					for(int v : graph.get(u)) {
						if(isVisited[v] == 0) {
							queue.add(v);
                            
							isVisited[v] = 1;
							
							for(int j = 0; j < uniqueInt; j++)
								numFreq[v][j] = numFreq[u][j];
							
							numFreq[v][intRank.get(nodeVal[v])]++;
							
							
							key = getKey(l, v);
							if(kForlr.get(key) != null) {
                                
								numVal[0][0] = 0;
								numVal[0][1] = 0;
								for(int j = 0; j < uniqueInt; j++) {
									numVal[j][0] = uniqueInt - (j + 1);
									numVal[j][1] = numFreq[v][uniqueInt - (j + 1)];
								}
								
								
								Arrays.sort(numVal, new Comparator<int[]>() {
									public int compare(int[] a, int[] b) {
									   return Integer.compare(b[0], a[0]);
									}
								});
								
								Arrays.sort(numVal, new Comparator<int[]>() {
									public int compare(int[] a, int[] b) {
									   return Integer.compare(b[1], a[1]);
									}
								});
								
								for(int k : kForlr.get(key)) {
									lrk.put( getKey(getKey(l, v), k), list.get(numVal[k-1][0]) );
									i++;
									lPro++;
								}
								
								if(lPro == lCount[l])
									break bfs;
							}
						}
					}
				}
			}
        }
        
        for(i = 0; i < q; i++) 
            out.println( lrk.get( getKey( getKey(lr[i][0], lr[i][1]), lr[i][2] ) ) );
        
    }
    
    private static long getKey(long a, long b) {
        long key;
        key = a >= b ? a * a + a + b : a + b * b;
        return key;
    }
}