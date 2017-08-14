import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BFS {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] route = new int[m][2];
        for(int route_i=0; route_i < m; route_i++){
            for(int route_j=0; route_j < 2; route_j++){
                route[route_i][route_j] = in.nextInt();
            }
        }
        // Write Your Code Here
		
		Stack<Integer> stack = new Stack<Integer>();
		
		int i,j,removed_node,maxCityCount,tempCityCount;
		
		maxCityCount = 0;
        
        int isVisited[] = new int[n];
        for(i = 0; i < n; i++)
            isVisited[i] = 0;
        
        
        int adjency[][] = new int [n][n];
        for(i = 0; i < n; i++)
            for(j = 0; j < n; j++)
                adjency[i][j] = 0;
        
        for(i = 0; i < m; i++){
            adjency[route[i][0]-1][route[i][1]-1] = 1;
            adjency[route[i][1]-1][route[i][0]-1] = 1;            
        }
        
        for(i = 0; i < n; i++){
			
			tempCityCount = 0;
            if(isVisited[i] == 0) {
				
				//System.out.println("Starting at " + i);
				
                stack.push(i);
				tempCityCount++;
				isVisited[i] = 1;
				
                while(!stack.isEmpty()){
					
                    removed_node = stack.pop();
                    //System.out.println(removed_node);
					
                    for(j = 0; j < n; j++){
                        if(isVisited[j] == 0 && adjency[removed_node][j] == 1){
							//System.out.println("Visiting "+j);
                            stack.push(j);
							tempCityCount++;
                            isVisited[j] = 1;
                        }
                    }
                }
				
				if(tempCityCount > maxCityCount)
					maxCityCount = tempCityCount;
				
				if(maxCityCount >= (n - n/2))
					break;
            }
        }
        
        
        System.out.println(maxCityCount);
        
    }
}
