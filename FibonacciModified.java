import java.util.Scanner;
import java.math.BigInteger;

public class FibonacciModified {
    
    private static int t1, t2, n;
    private static Scanner sc;

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        //n = 20;
        sc = new Scanner(System.in);
        t1 = sc.nextInt();
        t2 = sc.nextInt();
        n = sc.nextInt();
        
        BigInteger arr[] = new BigInteger[20];
        arr[0] = BigInteger.valueOf(t1);
        arr[1] = BigInteger.valueOf(t2);
        for(int i = 2; i < n; i++)
            arr[i] = (arr[i-1].multiply(arr[i-1])).add(arr[i-2]);
        
        System.out.println(arr[n-1]);
    }
}