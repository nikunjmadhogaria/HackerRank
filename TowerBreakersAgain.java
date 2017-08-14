import java.util.Scanner;
public class TowerBreakersAgain{
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]){
		
		int testCaseCount, testCaseWinner[], i;
		
		testCaseCount = sc.nextInt();
		testCaseWinner = new int[testCaseCount];
		
		for(i = 0; i < testCaseCount; i++)			
			testCaseWinner[i] = findWinner();
		
		for(i = 0; i < testCaseCount; i++)
			System.out.println(testCaseWinner[i]);
		
	}
	
	public static int findWinner(){
		
		int towerCount, i, towerHeight[], grundyValue[], xorValue, maxHeight;
		
		xorValue = 0;
		maxHeight = 0;
		
		towerCount = sc.nextInt();
		towerHeight = new int[towerCount];
		
		for(i = 0; i < towerCount; i++){
			
			towerHeight[i] = sc.nextInt();
			
			if(maxHeight < towerHeight[i])
				maxHeight = towerHeight[i];
			
		}
		
		
		grundyValue = new int[maxHeight + 1];
		
		for(i = 0; i <= maxHeight; i++)
			grundyValue[i] = -1;
		
		grundyValue[0] = 0;
		grundyValue[1] = 0;
		
				
		for(i = 0; i < towerCount; i++)
			xorValue ^= findGrundyValue(towerHeight[i], grundyValue);
		
		/*for(i = 0; i <= maxHeight; i++)
			System.out.print(grundyValue[i]+" ");
		System.out.println();*/
		
		if(xorValue > 0)
			return (1);
		else
			return (2);	
		
		
	}
	
	
	public static int findGrundyValue(int towerHeight, int grundyValue[]){
		
		
		
		//System.out.println("In findGrundyValue, towerHeight: "+towerHeight);
		
		int divisor, factorCount, factorAndNimValue[][], counter, leastNonNegative, i;
		
		divisor = 2;
		factorCount = 0;
		counter = 0;
		leastNonNegative = 0;
		
		
		/*System.out.println("Printing all grundyValues");
		for(i = 0; i < grundyValue.length; i++)
			System.out.print(grundyValue[i]+" ");
		System.out.println();*/
		
		if(grundyValue[towerHeight] != -1){
			//System.out.println("grundyValue known");
			return grundyValue[towerHeight];
		}
		
		else{
			//System.out.println("grundyValue NOT known");
			while(divisor <= towerHeight / 2){
				
				if(towerHeight % divisor == 0)
					factorCount++;
				
				divisor++;
			}
			
			factorCount++;			
			factorAndNimValue = new int[2][factorCount];
			
			divisor = 2;			
			while(divisor <= towerHeight / 2){
				
				if(towerHeight % divisor == 0){
					factorAndNimValue[0][counter] = divisor;
					factorAndNimValue[1][counter] = -1;
					counter++;
				}
				
				divisor++;
					
			}
			
			factorAndNimValue[0][factorCount - 1] = towerHeight;
			factorAndNimValue[1][factorCount - 1] = -1;
			
			/*System.out.println("Printing factorAndNimValue 1:");
			for(i = 0; i < factorCount; i++)
				System.out.print("("+factorAndNimValue[0][i]+","+factorAndNimValue[1][i]+"), ");
			System.out.println();*/
			
			for(i = 0; i < factorCount; i++){
				if(factorAndNimValue[0][i] % 2 == 0)
					factorAndNimValue[1][i] = 0;
				else
					factorAndNimValue[1][i] = findGrundyValue(towerHeight / factorAndNimValue[0][i], grundyValue);
			}
			
			/*System.out.println("Printing factorAndNimValue 2:");
			for(i = 0; i < factorCount; i++)
				System.out.print("("+factorAndNimValue[0][i]+","+factorAndNimValue[1][i]+"), ");
			System.out.println();*/
			
			while(true){				
				
				counter = 0;
				
				for(i = 0; i < factorCount; i++){
					
					if(leastNonNegative == factorAndNimValue[1][i])
						break;	
					
					counter++;
				}
				
				if(counter == factorCount)
					break;
				
				leastNonNegative++;
				
			}			
			
			grundyValue[towerHeight] = leastNonNegative;
			
		}
		
		
		return grundyValue[towerHeight];
		
	}
	
}