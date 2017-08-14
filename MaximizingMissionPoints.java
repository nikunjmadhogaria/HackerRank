import java.util.Scanner;
class City{
	int longitude, latitude, height, point;
}

public class MaximizingMissionPoints{
	
	static Scanner sc = new Scanner(System.in);
	
	
	
	public static void main(String arg[]){
		
		int n, x, y, i;
		
		n = sc.nextInt();
		x = sc.nextInt();
		y = sc.nextInt();
		
		City c[] = new City[n];
		
		for(i = 0; i < n; i++){
			c[i] = new City();
			c[i].latitude = sc.nextInt();
			c[i].longitude = sc.nextInt();
			c[i].height = sc.nextInt();
			c[i].point = sc.nextInt();
		}
		
		quicksort(c,0,n);
		
		for(i = 0; i < n; i++) {
			System.out.println(c[i].height);
		}
	}
	
	public static void quicksort(City c[], int start, int end) {
		
		if(end - start == 1 || end - start == 0)
			return;
		
		City temp;
		int pivot, i, j, k;
		
		pivot = end - 1;
		i = start - 1;
		j = start;
		System.out.println("pivot: "+pivot+", start: "+start+", end: "+end);
		
		while(j < pivot) {
			
			if(c[j].height < c[pivot].height) {
				i++;
				temp = c[i];
				c[i] = c[j];
				c[j] = temp;
			}
			
			j++;
			
		}
		
		System.out.println("pivot height: "+c[pivot].height+", sorted position: "+(i+1));
		
		temp = c[i + 1];
		c[i + 1] = c[pivot];
		c[pivot] = temp;
		
		for(k = 0; k < c.length; k++)
			System.out.print(c[k].height+" ");
		
		System.out.println("\n\n");
		
		quicksort(c, start, i+1);
		quicksort(c, i+2, end);
	}
}