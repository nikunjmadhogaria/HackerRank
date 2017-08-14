import java.util.Scanner;
public class DigitsSquareBoard{
	
	static Scanner sc = new Scanner(System.in);
	static int boardStateAndGrundyValue[][][][] = new int[30][30][30][30];
	
	public static void main(String arg[]){
		
		int testCaseCount,i;
		String testCaseWinner[];
		
		testCaseCount = sc.nextInt();
		testCaseWinner = new String[testCaseCount];
		
		for(i = 0; i < testCaseCount; i++){
			testCaseWinner[i] = findWinner();
		}
		
		for(i = 0; i < testCaseCount; i++)
			System.out.println(testCaseWinner[i]);
		
			
	}
	
	public static String findWinner(){
		
		int boardSize, board[][], i, j, k, l;
		
		boardSize = sc.nextInt();
		board = new int[boardSize][boardSize];
		
		for(i = 0; i < boardSize; i++)
			for(j = 0; j < boardSize; j++)
				board[i][j] = sc.nextInt();
            
            
        for(i = 0; i < 30; i++)
            for(j = 0; j < 30; j++)
                for(k = 0; k < 30; k++)
                    for(l = 0; l < 30; l++)
                        boardStateAndGrundyValue[i][j][k][l] = -1;  
		

			
		if( getGrundyValue(board, 0, boardSize, 0, boardSize) > 0 )
			return "First";
		else
			return "Second";
		
	}
	
	public static int getGrundyValue(int board[][], int startRow, int endRow, int startCol, int endCol){
		
		int nimValue[], leastNimValue, i, j, arrayPos, possibleMoveCount, counter;
        
		if(boardStateAndGrundyValue[startRow][endRow-1][startCol][endCol-1] != -1)
			return boardStateAndGrundyValue[startRow][endRow-1][startCol][endCol-1];
		
		else{
			
			if( ( ( (endRow - startRow) == 1) && ( (endCol - startCol) == 1) )
				|| !nonPrimeFound(board, startRow, endRow, startCol, endCol) )
				return 0;
				
			else{
				
				possibleMoveCount = (endRow - startRow - 1) + (endCol - startCol - 1);
				nimValue = new int[possibleMoveCount];
				arrayPos = 0;
				
				for(j = startRow + 1; j < endRow; j++){
					nimValue[arrayPos] = getGrundyValue(board, startRow, j, startCol, endCol)
										^getGrundyValue(board, j, endRow, startCol, endCol);
					arrayPos++;
				}
				
				for(j = startCol + 1; j < endCol; j++){
					nimValue[arrayPos] = getGrundyValue(board, startRow, endRow, startCol, j)
										^getGrundyValue(board, startRow, endRow, j, endCol);
					arrayPos++;
				}
				
			}	
			
			leastNimValue = 0;
			
			while(true){
				
				counter = 0;
				
				for(j = 0; j < possibleMoveCount; j++){
					
					if(nimValue[j] == leastNimValue)
						break;
					
					counter++;
				}
				
				if(counter == possibleMoveCount)
					break;
				
				leastNimValue++;

			}
			
		}
		
		boardStateAndGrundyValue[startRow][endRow-1][startCol][endCol-1] = leastNimValue;
		
		return leastNimValue;
		
	}
	
	public static boolean nonPrimeFound(int board[][], int startRow, int endRow, int startCol, int endCol){
		int j,k;
		boolean flag = false;
		
		outerloop:
		for(j = startRow; j < endRow; j++){
			
			for(k = startCol; k < endCol; k++){
				
				if( board[j][k] == 1 || board[j][k] == 4 || board[j][k] == 6 || board[j][k] == 8 || board[j][k] == 9 ){
					flag = true;
					break outerloop;
				}
				
			}
			
		}
		
		return flag;
	}
	
}