import java.util.Arrays;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SameOccurence {
    
    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt()
        {
            return Integer.parseInt(next());
        }
 
        long nextLong()
        {
            return Long.parseLong(next());
        }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
    
    private static int n, q, arr[], x, y, xFreq, yFreq, maxFreq, xFreqPos[], yFreqPos[], subArrayCountAtPos[], fCount, ans, allSubArraysCount;
    public static boolean foundXY, isAltArr;
    private static FastReader sc;
    private static HashMap<String, Integer> hm = new HashMap<String, Integer>(1000000);
    
    public static void main(String[] args) {
        
        sc = new FastReader(new File("SameOccurenceIn.txt"));  
		PrintStream out = new PrintStream(new FileOutputStream("SameOccurenceOut.txt"));
        
		n = sc.nextInt();
        q = sc.nextInt();
        arr = new int[n];
        subArrayCountAtPos = new int[n];
        allSubArraysCount = (n * (n + 1)) / 2;
        isAltArr = true;
        
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if(isAltArr && i >= 2 && arr[i] != arr[i - 2])
                isAltArr = false;
        }
        
        //System.out.println(isAltArr);
        
        for(int i = 1; i <= q; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            if(x == y)
                System.out.println(allSubArraysCount);
            else {
                Integer r = hm.get(x+","+y);
                if(r != null)
                    System.out.println(r);
                
                else if(isAltArr) {
                    if( (x == arr[0] && y == arr[1]) || (x == arr[1] && y == arr[0]) ) {
                        if(n % 2 == 0)
                            ans = (n/2) * (n/2);
                        else
                            ans = ( (n+1)/2 * (n+1)/2 ) - (n+1)/2;
                    }
                    else if(x == arr[0] || y == arr[0]) 
                        ans = n/2;
                    else if(x == arr[1] || y == arr[1]) {
                        if(n % 2 == 0)
                            ans = n/2;
                        else
                            ans = n/2 + 1;
                    }
                    else
                        ans = allSubArraysCount;
                    
                    hm.put(x+","+y, ans);
                    System.out.println(ans);
                }
                
                else {
                    xFreqPos = new int[n + 1];
                    yFreqPos = new int[n + 1];
                    xFreqPos[0] = -1;
                    yFreqPos[0] = -1;
                    xFreq = 0;
                    yFreq = 0;
                    subArrayCountAtPos[0] = 0;

                    if(arr[0] == x)
                        xFreq = 1;
                    else if(arr[0] == y)
                        yFreq = 1;
                    else
                        subArrayCountAtPos[0] = 1;

                    ans = subArrayCountAtPos[0];

                    for(int j = 1; j < n; j++) {

                        foundXY = false;
                        if(arr[j] == x) {
                            xFreqPos[++xFreq] = j;
                            foundXY = true;
                        }
                        if(arr[j] == y) {
                            yFreqPos[++yFreq] = j;
                            foundXY = true;
                        }

                        if(foundXY) {
                            maxFreq = min(xFreq, yFreq);
                            //System.out.println("ending at "+arr[j]+"\n---------------");

                            fCount = j - max(xFreqPos[xFreq], yFreqPos[yFreq]);
                            //System.out.println("for freq = 0, fCount = "+fCount);

                            for(int f = 1; f <= maxFreq; f++) {
                                int temp = min(xFreqPos[xFreq - (f - 1)], yFreqPos[yFreq - (f - 1)])
                                             - max(xFreqPos[xFreq - f], yFreqPos[yFreq - f]);
                                if(temp > 0)
                                    fCount += temp;
                                //System.out.println("for freq = "+f+", total fCount = "+fCount);
                            }

                            subArrayCountAtPos[j] = fCount;
                            //System.out.println("\n\n");
                        }
                        else
                            subArrayCountAtPos[j] = subArrayCountAtPos[j - 1] + 1;

                        ans += subArrayCountAtPos[j];

                    }
                    
                    hm.put(x+","+y, ans);
                    //System.out.println("subArrayCountAtPos: "+Arrays.toString(subArrayCountAtPos));
                    System.out.println(ans);
                }
            }
        }
    }
    
    private static int max(int a, int b) {
        return a > b ? a : b;
    }
    
    private static int min(int a, int b) {
        return a < b ? a : b;
    }
}
