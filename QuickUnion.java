import java.util.Scanner;
public class QuickUnion {
	
	static int id[], sz[];
	
	QuickUnion(int n) {
		
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

    public static void main(String[] args) {
		
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
		
		new QuickUnion(n);
		
        int k, x1, x2, max;
		
		for(k = 0; k < m; k++){
			x1 = in.nextInt();
			x2 = in.nextInt();
			
			union(x1 - 1 , x2 - 1);
		}
        // Write Your Code Here
		
		int childCount[] = new int[n];
		for(k = 0; k < n; k++)
			childCount[k] = 0;
		
		for(k = 0; k < n; k++){
			childCount[getRoot(k)]++;
		}
		
		max = childCount[0];
		for(k = 1; k < n; k++)
			max = childCount[k] > max ? childCount[k] : max;

		System.out.println(max);
		
        
    }
}
