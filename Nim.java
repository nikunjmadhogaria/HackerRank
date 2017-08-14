import java.util.Scanner;

public class Nim{
	static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        int testCaseCount,i,stackCount,stackAddLimit;
        String testCaseResult[];
        
        testCaseCount = sc.nextInt();
        
        testCaseResult = new String[testCaseCount];
        
        for (i = 0; i < testCaseCount; i++){
            
            stackCount = sc.nextInt();
            stackAddLimit = sc.nextInt();
            
            testCaseResult[i] = checkBalanced(stackCount, stackAddLimit);
        }
        
        for (i = 0; i < testCaseCount; i++)
            System.out.println(testCaseResult[i]);
        
    }
    
    public static String checkBalanced(int stackCount, int stackAddLimit){
        
		int stackValue[],maxStackValue,k,maxBinaryPosition,temp,sumOfBinaryPositions[],binaryPosition;
		boolean isBalanced;
		
        stackValue = new int[stackCount];
        maxStackValue = 0;
        isBalanced = true;
        
       
        for (k = 0; k < stackCount; k++) {
            stackValue[k] = sc.nextInt();
            if(maxStackValue < stackValue[k])
                maxStackValue = stackValue[k];
        }
		
		System.out.println("Max stack value: "+maxStackValue);
        
        maxBinaryPosition = 0;
        temp = 1;
        while(temp <= maxStackValue){
            temp *= 2;
            maxBinaryPosition++;
        }
		
		System.out.println("Max binary position: "+maxBinaryPosition);
        
        sumOfBinaryPositions = new int[maxBinaryPosition];
        for (k = 0; k < maxBinaryPosition; k++)
            sumOfBinaryPositions[k] = 0;
        
        for (k = 0; k < stackCount; k++) {
             
             temp = stackValue[k];
             binaryPosition = 0;
             
             while(temp != 0){
                 sumOfBinaryPositions[binaryPosition] += temp % 2;
                 binaryPosition++;
                 temp = temp / 2;
             }
        
        }
        
        
        for(k = 0; k < maxBinaryPosition; k++){
            
            if(sumOfBinaryPositions[k] % 2 == 1){                
                isBalanced = false;
                break;                
            }
            
        }
        
        if(isBalanced)
            return "Second";
        else
            return "First";           
        
        
    }
}