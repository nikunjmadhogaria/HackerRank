import java.util.*;

public class PathMatching {
	
	private static int n, q, parent[], level[], qAns[][], pLen, sLen, matchPosLeft[], matchPosRight[], subCountLeft, subCountRight, count;
	private static ArrayList<ArrayList<Integer>> graph;
	private static Scanner sc;
	private static String s,pattern;
	private static char chFirstPattern, chLastPattern;
	

    public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		
		n = sc.nextInt();
		q = sc.nextInt();		
		s = sc.next();
		pattern = sc.next();
		
		parent = new int[n];
		level = new int[n];
		qAns = new int[n][n];
		pLen = pattern.length();
        sLen = s.length();
		
        if(!(pLen > sLen)) {
            chFirstPattern = pattern.charAt(0);
            chLastPattern = pattern.charAt(pLen - 1);

            for (int[] row: qAns)
                Arrays.fill(row, -1);

            graph = new ArrayList<ArrayList<Integer>>(n);		

            for(int i = 0; i < n; i++)
                graph.add(new ArrayList<Integer>());
        }
		
		for(int i = 0; i < n-1; i++) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
            if(!(pLen > sLen)) {
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
		}
		
        if(!(pLen > sLen))
            BFS();
		
		for(int i = 0; i < q; i++) {
            
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            
            if(!(pLen > sLen)) {
                if(qAns[u][v] == -1)
                    qAns[u][v] = getCount(u, v);

                System.out.println(qAns[u][v]);
            }
            
            else
                System.out.println(0);
		}
    }
	
	private static void BFS() {
		int isVisited[] = new int[n];
		Queue<Integer> queue =  new LinkedList<Integer>();
		queue.add(0);
		while( !queue.isEmpty() ) {
			int u = queue.remove();
			isVisited[u] = 1;
			for( int v : graph.get(u) ) {
				if(isVisited[v] == 0) {
					queue.add(v);
					parent[v] = u;
					level[v] = level[u] + 1;
				}
			}
		}
	}

	private static int getCount(int u, int v) {
		
		subCountLeft = 0;
		subCountRight = 0;
		count = 0;
		matchPosLeft = new int[n];
		matchPosRight = new int[n];
		
		if(level[u] > level[v]) {
			while(level[u] != level[v]) {
				leftCounter(s.charAt(u));				
				u = parent[u];				
			}
		}
		
		
		Arrays.fill(matchPosRight, pLen - 1);		
		if(level[v] > level[u]) {
			while(level[v] != level[u]) {
				rightCounter(s.charAt(v));
				v = parent[v];
			}
		}
		
		while(v != u) {
			leftCounter(s.charAt(u));
			u = parent[u];
			rightCounter(s.charAt(v));
			v = parent[v];
		}
		
		leftCounter(s.charAt(u));
		for(int i = 0; i < subCountLeft; i++) {
			if(matchPosLeft[i] != -1) {
				for(int j = 0; j < subCountRight; j++) {
					if(matchPosRight[j] != -1) {
						int l = matchPosLeft[i];
						int r = pLen - (matchPosRight[j] + 1);
						if((l + r) == pLen)
							count++;
					}
				}
			}
		}
		
		return count;	
	}

	private static void leftCounter(char ch) {
		if(ch == chFirstPattern) {
			subCountLeft++;
		}
		
		for(int j = 0; j < subCountLeft; j++) {
			if(matchPosLeft[j] != -1 && ch == pattern.charAt(matchPosLeft[j])) {
				matchPosLeft[j]++;
				if(matchPosLeft[j] == pLen) {
					count++;
					matchPosLeft[j] = -1;
				}
			}
			else
				matchPosLeft[j] = -1;
		}
	}
	
	
	private static void rightCounter(char ch) {
		if(ch == chLastPattern) {
			subCountRight++;
		}
		
		for(int j = 0; j < subCountRight; j++) {
			if(matchPosRight[j] != -1 && ch == pattern.charAt(matchPosRight[j])) {
				matchPosRight[j]--;
				if(matchPosRight[j] == -1) {
					count++;
				}
			}
			else
				matchPosRight[j] = -1;
		}
		
	}

	
}
