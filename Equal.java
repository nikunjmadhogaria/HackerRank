import java.util.Scanner;

public class Equal {

    public static void main(String[] args) {
        int t, n, c[], sol[], min, minOps, temp, i, j;
        Scanner sc = new Scanner(System.in);
        
        
        t = sc.nextInt();
        sol = new int[t];
        
        for(i = 0; i < t; i++) {
            
            n = sc.nextInt();
            c = new int[n];
            c[0] = sc.nextInt();
            min = c[0];
            
            for(j = 1; j < n; j++) {
                c[j] = sc.nextInt();
                min = c[j] < min ? c[j] : min;
            }
            
            minOps = opsCount(min, c, n);
            for(j = 1; j <= 4; j++){
                temp = opsCount(min - j, c, n);
                if(temp < minOps)
                    minOps = temp;
            }
            
            sol[i] = minOps; 
            
        }
        
        for(i = 0; i < t; i++)
            System.out.println(sol[i]);
        
	}
    
    static int opsCount(int min, int c[], int n) {
        
        int i, count;
        count = 0;
        for(i = 0; i < n; i++) {
            count += (c[i] - min) / 5;
            count += ((c[i] - min) % 5) / 2;
            count += ((c[i] - min) % 5) % 2;
        }
        
        //System.out.println("min: "+min+", count: "+count);
        
        return count;
    }
    
}
