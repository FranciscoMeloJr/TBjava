package package1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author francisco
 */
public class BinaryTree {
	ArrayList<Integer> fInformation;
	String fNameNode;
	BinaryTree fLeft;
	BinaryTree fRight;

	public interface ITreeVisitor {
		public void visit(BinaryTree node);
	}

	public BinaryTree() {
		this(0, null, null);
	}

	public BinaryTree(int cargo, BinaryTree left, BinaryTree right) {
		fInformation = new ArrayList<>();
		fInformation.add(cargo);
		fLeft = left;
		fRight = right;
	}

	public BinaryTree(BinaryTree left, BinaryTree right) {
		this(0, left, right);
	}

	public BinaryTree(int a) {
		this(a, null, null);
	}

	void add(int a) {
		fInformation.add(a);
	}

	void addNodeR(BinaryTree tree) {
		fRight = tree;
	}

	// Inorder:
	public ArrayList<Integer> Inorder(BinaryTree tree) {

		System.out.println("x");
		Stack<BinaryTree> fstack = new Stack();
		ArrayList<Integer> out = new ArrayList<>();

		fstack.push(tree);
		BinaryTree froot = tree.fLeft;

		out.addAll(froot.fInformation);
		// System.out.println(froot.fInformation);

		// accumulate in a stack
		while (froot != null) {
			fstack.push(froot);
			froot = froot.fLeft;
		}

		// now, disaccumulate, showing:
		while (fstack.size() > 0) {

			froot = fstack.pop();
			out.addAll(froot.fInformation);
			// System.out.print(froot.fInformation + " ");
			if (froot.fRight != null) {
				froot = froot.fRight;

				// the next node to be visited is the leftmost
				while (froot != null) {
					fstack.push(froot);
					froot = froot.fLeft;
				}
			}
		}
		return out;
	}
	
	//Compare node:
	boolean compareNode(BinaryTree N1 ,BinaryTree N2)
	{
		  return N1.fInformation == N1.fInformation;
	}
	
	//This function here compare 2 binary tree*/
	boolean equals(BinaryTree T1, BinaryTree T2)
	{
		//def equals(T1, T2):
		if(compare(T1, T2))
			return false;
		// if is successful for flat comparison 
		// if it is leaf it is equal
		if (T1.fInformation == null && T2.fInformation == null)
			return true;
		//Deep comparison:

		if (!equals(T1.fLeft, T1.fLeft))
			return false;
		
		if (!equals(T1.fRight, T1.fRight))
			return false;
		return true;

	}
	//Level order traversal
	void  levelOrderTraversal(BinaryTree startNode) {
		System.out.print("levelOrderTraversal");
		  Queue<BinaryTree> queue= new LinkedList<BinaryTree>();  
		  queue.add(startNode);  
		  while(!queue.isEmpty())  
		  {  
		   BinaryTree tempNode=queue.poll();  
		   System.out.print(tempNode.fInformation);  
		   if(tempNode.fLeft!=null)  
			  queue.add(tempNode.fLeft);  
		   if(tempNode.fRight!=null)  
			  queue.add(tempNode.fRight);  
		  }  
    }  

		
    boolean identicalTrees(BinaryTree a, BinaryTree b) {
        
        /*1. both empty */
        if (a == null && b == null) {
            return true;
        }
 
        /* 2. both non-empty -> compare them */
        if (a != null && b != null) {
            return (a.fNameNode == b.fNameNode
                    && identicalTrees(a.fLeft, b.fLeft)
                    && identicalTrees(a.fRight, b.fRight));
        }
 
        /* 3. one empty, one not -> false */
        return false;
    }
	public ArrayList<Integer> PreOrder(BinaryTree tree) {

		System.out.println("x");
		Stack<BinaryTree> fstack = new Stack();
		ArrayList<Integer> out = new ArrayList<>();

		BinaryTree froot = tree;
		fstack.push(froot);

		while (fstack.size() > 0) {
			froot = fstack.pop();
			out.addAll(froot.fInformation);

			if (froot.fRight != null)
				fstack.push(froot.fRight);

			if (froot.fLeft != null)
				fstack.push(froot.fLeft);
		}

		System.out.print(out.toString());
		return out;
	}

	public ArrayList<Integer> PosOrder(BinaryTree root) {
		Stack<BinaryTree> S = new Stack<>();
		ArrayList<Integer> out = new ArrayList<>();
		BinaryTree fprev = root;

		S.push(root);
		BinaryTree prev = null;

		while (!S.isEmpty()) {
			BinaryTree fcurrent = S.peek();

			if (prev == null || prev.fLeft == fcurrent || prev.fRight == fcurrent) {
				if (fcurrent.fLeft != null) {
					S.push(fcurrent.fLeft);
				} else if (fcurrent.fRight != null) {
					S.push(fcurrent.fRight);
				} else {
					S.pop();
					out.addAll(fcurrent.fInformation);
				}

				/*
				 * go up the tree from left node, if the child is right push it
				 * onto stack otherwise process parent and pop stack
				 */
			} else if (fcurrent.fLeft == prev) {
				if (fcurrent.fRight != null) {
					S.push(fcurrent.fRight);
				} else {
					S.pop();
					out.addAll(fcurrent.fInformation);
				}

				/*
				 * go up the tree from right node and after coming back from
				 * right node process parent and pop stack
				 */
			} else if (fcurrent.fRight == prev) {
				S.pop();
				out.addAll(fcurrent.fInformation);
			}

			prev = fcurrent;
		}
		System.out.print(out.toString());
		return out;
	}

	void addNodeL(BinaryTree tree) {
		fLeft = tree;
	}

	void delete(int a) {
		int index = fInformation.indexOf(a);
		fInformation.remove(index);
	}

	// Pos order:
	public static void printPos(BinaryTree tree) {
		if (tree == null)
			return;
		System.out.print(tree.fInformation);
		printPos(tree.fRight);
		printPos(tree.fLeft);

	}

	// Pre order:
	public static void printPre(BinaryTree tree) {
		if (tree == null)
			return;
		System.out.print(tree.fInformation);
		printPre(tree.fLeft);
		printPre(tree.fRight);
	}

	// In order:
	public static void printIn(BinaryTree tree, ITreeVisitor visitor) {
		if (tree == null)
			return;
		printIn(tree.fLeft, visitor);
		// Visit the node by Printing the node data
		visitor.visit(tree);
		printIn(tree.fRight, visitor);

	}

	// In order:
	public static void printIn(BinaryTree tree) {
		if (tree == null)
			return;
		printIn(tree.fLeft);
		// Visit the node by Printing the node data
		System.out.print(tree.fInformation);
		printIn(tree.fRight);

	}

	// In order:
	public static ArrayList<Integer> In(BinaryTree tree) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		if (tree == null) {
			return temp;
		} else {
			temp.addAll(In(tree.fLeft));
			temp.addAll(tree.fInformation);
			temp.addAll(In(tree.fRight)); // printIn(tree.right);
		}
		return temp;
	}

	// Pre order:
	public static ArrayList<Integer> Pre(BinaryTree tree) {
		ArrayList<Integer> temp = new ArrayList<>();
		if (tree == null) {
			return temp;
		} else {
			temp.addAll(tree.fInformation);
			temp.addAll(Pre(tree.fLeft));
			temp.addAll(Pre(tree.fRight)); // printIn(tree.right);
		}
		return temp;
	}

	// Pos:
	public static ArrayList<Integer> Pos(BinaryTree tree) {
		ArrayList<Integer> temp = new ArrayList<>();
		if (tree == null) {
			return temp;
		} else {
			temp.addAll(Pos(tree.fLeft));
			temp.addAll(Pos(tree.fRight)); // printIn(tree.right);
			temp.addAll(tree.fInformation);

		}
		return temp;
	}

	// Compare:
	public static boolean compare(BinaryTree A, BinaryTree B) {
		ArrayList<Integer> temp1 = Pos(A);
		ArrayList<Integer> temp2 = Pos(B);

		// Quick Check to see if the two arrayLists have the same number of
		// elements
		if (temp1.size() != temp2.size())
			return false;

		// Optionally Sort the arrays - avoid returning false if the elements
		// are the same but
		// have been stored out of sequence
		Collections.sort(temp1);
		Collections.sort(temp2);

		if (temp1.hashCode() == temp2.hashCode()) {
			return true;
		} else {
			return false;
		}

	}
}