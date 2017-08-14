import java.util.Scanner;
import java.math.BigInteger;

class Node {
	BigInteger n;
	Node left, right;
	
	Node(BigInteger x) {
		n = x;
		left = null;
		right = null;
	}
	
	BigInteger getData() {
		return n;
	}
	
	void setLeft(Node x) {
		left = x;
	}
	
	Node getLeft() {
		return left;
	}
	
	void setRight(Node x) {
		right = x;
	}
	
	Node getRight() {
		return right;
	}	
}

class BST {
	static Node root;
	
	static void insert(Node x, Node r) {
		
		if(x.getData().compareTo(r.getData()) == -1) { //x.getData() < r.getData()
			if(r.getLeft() == null)
				r.setLeft(x);
			else
				insert(x, r.getLeft());
		}
		
		else {
			if(r.getRight() == null)
				r.setRight(x);
			else
				insert(x, r.getRight());
		}
		
	}	
	
	static void inorder(Node r) {
		
		if(r != null) {
			inorder(r.getLeft());
			System.out.println(r.getData());
			inorder(r.getRight());
		}
		
	}
}

public class BigSortingBST {
	private static int n;
	private static Scanner sc = new Scanner(System.in);
	private static BST tree;
	
	public static void main(String arg[]) {
		
		n = sc.nextInt();
		tree = new BST();
		BST.root = new Node(new BigInteger(sc.next()) );
		
		for(int i = 1; i <= n - 1; i++)
			BST.insert(new Node(new BigInteger(sc.next()) ), BST.root);
		
		BST.inorder(BST.root);
	}
	
}