import java.util.Scanner;
public class ChessBoardGameAgain{
	
	static Scanner sc = new Scanner(System.in);
	static int gridGrundyValue[][] = new int[15][15];
	
	public static void main(String arg[]){
		
		int testCaseCount, i, j;
		String testCaseWinner[];
		
		testCaseCount = sc.nextInt();
		
		testCaseWinner = new String[testCaseCount];
		
		for(i = 0; i < 15; i++){
			for(j = 0; j < 15; j++)
				gridGrundyValue[i][j] = -1;
		}
		
		gridGrundyValue[0][0] = 0;
		gridGrundyValue[0][1] = 0;
		gridGrundyValue[1][0] = 0;
		gridGrundyValue[1][1] = 0;
		
		for(i = 0; i < testCaseCount; i++)
			testCaseWinner[i] = findWinner();
		
		for(i = 0; i < testCaseCount; i++)
			System.out.println(testCaseWinner[i]);
		
	}
	
	public static String findWinner(){
		
		int coinCount, coinPosition[][], xorValue, i;
		
		xorValue = 0;
		
		coinCount = sc.nextInt();
		
		coinPosition = new int[coinCount][2];
		
		for(i = 0; i < coinCount; i++){
			
			coinPosition[i][0] = sc.nextInt(); //x coordinate
			coinPosition[i][1] = sc.nextInt(); //y coordinate
			
		}
		
		for(i = 0; i < coinCount; i++)
			xorValue ^= findGrundyValue(coinPosition[i][0], coinPosition[i][1]);
		
		if(xorValue != 0)
			return "First";
		else
			return "Second";
		
		
	}
	
	public static int findGrundyValue(int x, int y){
		
		int xorValue, leastNimValue, tempX, tempY, i, counter;
		
		if(gridGrundyValue[x - 1][y - 1] != -1) //if grundy value for a particular position is known
			return	gridGrundyValue[x - 1][y - 1];
			
		else{
			
			int nimValueForEachMove[] = {-1, -1, -1, -1};
			
			tempX = x - 2;
			tempY = y + 1;
			if( (tempX >= 1 && tempX <= 15) && (tempY >= 1 && tempY <= 15) )
				nimValueForEachMove[0] = findGrundyValue(tempX, tempY);
			
			tempX = x - 2;
			tempY = y - 1;
			if( (tempX >= 1 && tempX <= 15) && (tempY >= 1 && tempY <= 15) )
				nimValueForEachMove[1] = findGrundyValue(tempX, tempY);
			
			tempX = x + 1;
			tempY = y - 2;
			if( (tempX >= 1 && tempX <= 15) && (tempY >= 1 && tempY <= 15) )
				nimValueForEachMove[2] = findGrundyValue(tempX, tempY);
			
			tempX = x - 1;
			tempY = y - 2;
			if( (tempX >= 1 && tempX <= 15) && (tempY >= 1 && tempY <= 15) )
				nimValueForEachMove[3] = findGrundyValue(tempX, tempY);
			
			leastNimValue = 0;
			
			while(true){
				
				counter = 0;
				
				for(i = 0; i < 4; i++){
					if(nimValueForEachMove[i] == leastNimValue)
						break;
					counter++;
				}
				
				if(counter == 4)
					break;
				
				leastNimValue++;
			}
			
			gridGrundyValue[x - 1][y - 1] = leastNimValue;
			
		}
		
		return gridGrundyValue[x - 1][y - 1];
	}	
	
}