import java.util.Scanner;
import java.util.Arrays;

public class CountSolutions {
	
	private static int q, a, b, c, d, yLim[], yMax, yMin;
	private static long count;
	private static Scanner sc;

    public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		q = sc.nextInt();
		
		String ans = "";
		
		for(int i = 0; i < q; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
			d = sc.nextInt();
			
			yLim = getYLim();
			
			//System.out.println(Arrays.toString(yLim));
			
			yMax = yLim[1] > d ? d : yLim[1];
			yMin = yLim[0] > 0 ? yLim[0] : 1;
			
			count = 0;
			for(int j = yMin; j <= yMax; j++)
				count += (long)xSolCount(j);
			
			ans += count + "\n";
		}
		
		System.out.println(ans);
    }
	
	private static int[] getYLim() {
		int lim[] = new int[2];
		lim[0] = (int)Math.ceil( ( (double)b - Math.sqrt((a*a) + (b*b)) ) / 2);
		lim[1] = (int)Math.floor( ( (double)b + Math.sqrt((a*a) + (b*b)) ) / 2);
		
		return lim;
	}
	
	private static int xSolCount(int y) {
		int xCount = 0;
		double xDouble1 = ( (double)a + Math.sqrt(a*a - 4*(y*y - y*b)) ) / 2;
		double xDouble2 = ( (double)a - Math.sqrt(a*a - 4*(y*y - y*b)) ) / 2;
		
		//System.out.println("x: "+xDouble1+", "+xDouble2);
		
		if(Math.floor(xDouble1) == xDouble1 && xDouble1 > 0.0 && xDouble1 <= c)
			xCount++;
		
		if(Math.floor(xDouble2) == xDouble2 && xDouble2 > 0.0 && xDouble2 <= c && xDouble1 != xDouble2)
			xCount++;
		
		return xCount;
	}
}