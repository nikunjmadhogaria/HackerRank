import java.util.Scanner;
import java.util.Arrays;
import java.math.BigInteger;

public class BigSortingQuick {
	private static int n;
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String arg[]) {
		
		n = sc.nextInt();
		BigInteger arr[] = new BigInteger[n];
		
		for(int i = 0; i < n; i++)
			arr[i] = new BigInteger(sc.next());
		
		//quickSort(arr, 0, n-1);
		
		Arrays.sort(arr);
		
		for(int i = 0; i < n; i++)
			System.out.println(arr[i]);
	}
	
	static void quickSort(BigInteger arr[], int start, int end) {
		
		if(start < end) {
			int i,j,pivot;
			BigInteger temp;
			
			i = start - 1;
			j = start;
			pivot = end;
			
			while(j < pivot) {
				if( (arr[j].compareTo(arr[pivot])) == -1 ) { //arr[j] < arr[pivot]
					i++;
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}			
				j++;
			}
			
			temp = arr[i + 1];
			arr[i + 1] = arr[pivot];
			arr[pivot] = temp;
			
			quickSort(arr, start, i);
			quickSort(arr, i + 2, end);
		}
		
		else
			return;
	}
	
}