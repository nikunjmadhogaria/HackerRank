import java.util.Scanner;
public class FunGame{
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String arg[]){
		
		int testCaseCount, i;
		String testCaseWinner[];
		
		testCaseCount = sc.nextInt();
		
		testCaseWinner = new String[testCaseCount];
		
		for(i = 0; i < testCaseCount; i++)
			testCaseWinner[i] = findWinner();		
		
		for(i = 0; i < testCaseCount; i++)
			System.out.println(testCaseWinner[i]);
		
	}
	
	public static String findWinner(){
		
		int arraySize, a[], b[], abSum[], i, j, maxPos, temp, aScore, bScore;
		
		arraySize = sc.nextInt();
		
		a = new int[arraySize];
		b = new int[arraySize];
		abSum = new int[arraySize];
		
		for(i = 0; i < arraySize; i++)
			a[i] = sc.nextInt();
		
		for(i = 0; i < arraySize; i++)
			b[i] = sc.nextInt();
		
		for(i = 0; i < arraySize; i++)
			abSum[i] = a[i] + b[i];
		
		for(i = 0; i < arraySize - 1; i++){
			
			maxPos = i;
			
			for(j = i + 1; j < arraySize; j++){
				
				if(abSum[maxPos] < abSum[j])
					maxPos = j;
				
			}
			
			temp = a[i];
			a[i] = a[maxPos];
			a[maxPos] = temp;
			
			temp = b[i];
			b[i] = b[maxPos];
			b[maxPos] = temp;
			
			temp = abSum[i];
			abSum[i] = abSum[maxPos];
			abSum[maxPos] = temp;
			
		}
		
		aScore = bScore = 0;
		
		/*System.out.println("\n\nPrinting abSum..\n");
		
		for(i = 0; i < arraySize; i++)
			System.out.print(abSum[i] + " ");
		
		System.out.println();*/
		
			
		for(i = 0; i < arraySize; i = i+2)
			aScore += a[i];
		
		for(i = 1; i < arraySize; i = i+2)
			bScore += b[i];
		
		
		if(aScore > bScore)
			return "First";
		
		else if (aScore < bScore)
			return "Second";
		
		else
			return "Tie";		
		
		
	}
	
}