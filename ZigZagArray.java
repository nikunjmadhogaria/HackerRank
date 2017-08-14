import java.util.*;
public class ZigZagArray{
	
	static int minimumDeletions(int[] a){
        // Complete this function
        int min_del = 0;
        int i;
		int a_length = a.length;
      
		List<Integer> aList = new ArrayList<Integer>();
		for (int index = 0; index < a_length; index++)
			aList.add(a[index]);
		
		for (int index = 0; index < a_length; index++){
			
			if(!isZigzag(index, index + 1, index + 2, aList))
				min_del += removeCount(index, aList);
		}      
        
        return min_del;            
    }
    
    static boolean isZigzag(int i, int j, int k, List<Integer> aList){
        if(aList.get(i) < aList.get(j) && aList.get(j) < aList.get(k))
            return false;
        
		if(aList.get(i) > aList.get(j) && aList.get(j) > aList.get(k))
            return false;
        
        return true;
    }
    
    
    static int removeCount(int i, List<Integer> aList){
        
		if(isZigzag())
       

		return 1;
        
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        // Return the minimum number of elements to delete to make the array zigzag
        int result = minimumDeletions(a);
        System.out.println(result);
    }
}
