import java.util.Scanner;
import java.util.HashMap;

public class StoneDivision{
	
	static Scanner sc = new Scanner(System.in);
	static long stoneCount, divisor[];
	static HashMap<Long, Integer> stoneCountAndGrundyValue = new HashMap<Long, Integer>();
	static int divisorCount;
	
	public static void main(String args[]){
		
		int i;
		
		stoneCount = sc.nextLong();
		divisorCount = sc.nextInt();
		
		divisor = new long[divisorCount];
		
		stoneCountAndGrundyValue.put(Long.valueOf(0), 0);
		stoneCountAndGrundyValue.put(Long.valueOf(1), 0);
		
		for(i = 0; i < divisorCount; i++)
			divisor[i] = sc.nextLong();
		
		if( findGrundy(stoneCount) > 0)
			System.out.println("First");
		else
			System.out.println("Second");
		
	}
	
	public static int findGrundy(long stoneCount){
		
		int nimValue[], leastNimValue, counter, i;
		long quotient;
		
		if(stoneCountAndGrundyValue.get(stoneCount) != null)
			return stoneCountAndGrundyValue.get(stoneCount);
		
		if(!factorExists(stoneCount))
			return 0;
		
		nimValue = new int[divisorCount];
		
		for(i = 0; i < divisorCount; i++)
			nimValue[i] = -1;
		
		
		for(i = 0; i < divisorCount; i++){
			
			if(stoneCount % divisor[i] == 0){
				
				quotient = stoneCount / divisor[i];
				
				if( quotient % 2 == 0)
					nimValue[i] = 0;
				else
					nimValue[i] = findGrundy(quotient);
			}
			
		}
		
		leastNimValue = 0;
		
		while(true){
			
			counter = 0;
			
			for(i = 0; i < divisorCount; i++){
				
				if(nimValue[i] != leastNimValue)
					counter++;
				
			}
			
			if(counter == divisorCount)
				break;
			
			leastNimValue++;
				
		}
		
		stoneCountAndGrundyValue.put(stoneCount, leastNimValue);
		
		return leastNimValue;		
		
	}
	
	public static boolean factorExists(long stoneCount){
		
		int i;
		
		for(i = 0; i < divisorCount; i++){
			
			if(stoneCount % divisor[i] == 0)
				return true;
			
		}
		
		return false;
	}
}