// JAVA program to find modular inverse of a under modulo m
// This program works only if m is prime.
import java.util.Scanner;
class ModInverse{

	// To find GCD of a and b
	// Function to find modular inverse of a under modulo m
	// Assumption: m is prime
	static void modInverse(int a, int m)
	{
		int g = gcd(a, m);
		if (g != 1)
			System.out.println("Inverse doesn't exist");
		else
		{
			// If a and m are relatively prime, then modulo inverse
			// is a^(m-2) mode m
			System.out.println("Modular multiplicative inverse is " + power(a, m-2, m));
		}
	}

	// To compute x^y under modulo m
	static long power(long x, long y, long m)
	{
		if (y == 0)
			return 1;
		long p = power(x, y/2, m) % m;
		p = (p * p) % m;

		return (y%2 == 0)? p : (x * p) % m;
	}

	// Function to return gcd of a and b
	static int gcd(int a, int b)
	{
		if (a == 0)
			return b;
		return gcd(b%a, a);
	}

	// Driver Program
	public static void main(String arg[])
	{
		Scanner sc = new Scanner(System.in);		
		int a, m;
		
		System.out.print("Enter the number for which you need to find out Mod Inverse, a = ");
		a = sc.nextInt();
		
		System.out.print("Enter the MOD value, m = ");
		m = sc.nextInt();
		
		modInverse(a, m);
	}
}