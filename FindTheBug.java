import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class FindTheBug {

    static int[] findTheBug(String[] grid){
        // Complete this function
        int[] pos = new int[2];
        int str_length, i, j, string_count;
        String str;
        
        string_count = grid.length;
		System.out.println(string_count);
        outer:for(i = 0; i < string_count; i++){
            str = grid[i];
			System.out.println(str);
            str_length = str.length();
            for(j = 0; j < str_length; j++){
                if(str.charAt(j) == 'X'){
                    pos[0] = i;
                    pos[1] = j;
                    break outer; 
                }
            }
        }
        
        return pos;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] grid = new String[n];
        for(int grid_i=0; grid_i < n; grid_i++){
            grid[grid_i] = in.next();
        }
        // Return an array containing [r, c]
        int[] result = findTheBug(grid);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != n - 1 ? "," : ""));
        }
        System.out.println("");
        

    }
}
