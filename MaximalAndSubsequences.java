import java.util.Scanner;
public class MaximalAndSubsequences{
	
	static int andVal = 0;
    static int andValCount = 0;
    
    static void printCombination(int arr[], int n, int r)
    {
        // A temporary array to store all combination one by one
        int data[] = new int[r];

        // Print all combination using temprary array 'data[]'
        combinationUtil(arr, data, 0, n-1, 0, r);
    }
    
    static void combinationUtil(int arr[], int data[], int start, int end, int index, int r)
    {
        // Current combination is ready to be printed, print it
        
        if (index == r)
        {
            int tempAndVal = data[0];
            
            for (int j=0; j<r; j++)
                tempAndVal ^= data[j];
            
            if(tempAndVal > andVal){
                andVal = tempAndVal;
                andValCount = 1;
            }
            else if (tempAndVal == andVal)
                andValCount++;
                
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i=start; i<=end && end-i+1 >= r-index; i++)
        {
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r);
        }
    }    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int r = in.nextInt();
        long[] arr = new long[n];
        for(int a_i=0; a_i < n; a_i++)
            arr[a_i] = in.nextLong();
        
        printCombination(arr, n, r);
        
        System.out.println(andVal);
        System.out.println(andValCount);
    }
    