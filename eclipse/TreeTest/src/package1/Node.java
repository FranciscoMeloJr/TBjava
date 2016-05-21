package package1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author francisco
 */
public class Node implements Comparator<Node>, Comparable<Node> {
	ArrayList<Integer> fInformation;
	String fNameNode;
	Node fLeft;
	Node fRight;

	public interface ITreeVisitor {
		public void visit(Node node);

		public void visitNode(Node node);
	}

	public Node() {
		this(0, null, null, null);
	}

	public Node(int cargo, Node left, Node right, String label) {
		fInformation = new ArrayList<>();
		fInformation.add(cargo);
		fLeft = left;
		fRight = right;
		fNameNode = label;
	}

	public Node(Node left, Node right) {
		this(0, left, right, String.valueOf('A'));
	}

	public Node(int a, Node left, Node right) {
		this(a, left, right, String.valueOf('A'));
	}

	public Node(int a) {
		this(a, null, null, null);
	}

	public Node(int a, String b) {
		this(a, null, null, b);
	}

	void setLabel(String label) {
		fNameNode = label;
	}

	void add(int a) {
		fInformation.add(a);
	}

	void addNodeR(Node tree) {
		fRight = tree;
	}

	// Inorder:
	public ArrayList<Integer> Inorder(Node tree) {

		System.out.println("x");
		Stack<Node> fstack = new Stack<Node>();
		ArrayList<Integer> out = new ArrayList<>();

		fstack.push(tree);
		Node froot = tree.fLeft;

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

	// Compare node:
	boolean compareNode(Node N1, Node N2) {
		return N1.fInformation == N1.fInformation;
	}

	// This function here compare 2 binary tree*/
	boolean equals(Node T1, Node T2) {
		// def equals(T1, T2):
		if (compare(T1, T2) == 0)
			return false;
		// if is successful for flat comparison
		// if it is leaf it is equal
		if (T1.fInformation == null && T2.fInformation == null)
			return true;
		// Deep comparison:

		if (!equals(T1.fLeft, T1.fLeft))
			return false;

		if (!equals(T1.fRight, T1.fRight))
			return false;
		return true;

	}

	boolean identicalTrees(Node a, Node b) {

		/* 1. both empty */
		if (a == null && b == null) {
			return true;
		}

		/* 2. both non-empty -> compare them */
		if (a != null && b != null) {
			return (a.fNameNode == b.fNameNode && identicalTrees(a.fLeft, b.fLeft)
					&& identicalTrees(a.fRight, b.fRight));
		}

		/* 3. one empty, one not -> false */
		return false;
	}

	public ArrayList<Integer> PreOrder(Node tree) {

		System.out.println("x");
		Stack<Node> fstack = new Stack<Node>();
		ArrayList<Integer> out = new ArrayList<>();

		Node froot = tree;
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

	public ArrayList<Integer> PosOrder(Node root) {
		System.out.println("Pos");
		Stack<Node> S = new Stack<>();
		ArrayList<Integer> out = new ArrayList<>();
		Node fprev = root;

		S.push(root);
		Node prev = null;

		while (!S.isEmpty()) {
			Node fcurrent = S.peek();

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
		System.out.println(out.toString());
		return out;
	}

	void addNodeL(Node tree) {
		fLeft = tree;
	}

	void delete(int a) {
		int index = fInformation.indexOf(a);
		fInformation.remove(index);
	}
	//This function merge two nodes with the same label: A[1] + A[2] = A[2]
	void merge(Node root){
		ArrayList<Integer> metricsNode1;
		ArrayList<Integer> metricsNode2;
		ArrayList<Integer> result = new ArrayList<>();
		if(root.fNameNode.equals(fNameNode))
		{
			metricsNode1 = root.fInformation;
			metricsNode2 = fInformation;		
			for (int i = 0; i < metricsNode1.size(); i++) {
				int a = metricsNode1.get(i);
				int b = metricsNode2.get(i);
				int total = a - b;
				result.add(total);
				System.out.println(a + " " + b + " " + total);
			}
			fInformation = result;
		}
	}
	// Operation minus:
	public static Node minus(Node node1, Node node2) {

		// System.out.print("Minus");
		Node temp = new Node();
		ArrayList<Integer> metricsNode1;
		ArrayList<Integer> metricsNode2;
		ArrayList<Integer> result = new ArrayList<>();

		metricsNode1 = node1.fInformation;
		metricsNode2 = node2.fInformation;
		if (node1.fNameNode.equals(node2.fNameNode)) {
			temp.setLabel(node1.fNameNode);
			for (int i = 0; i < metricsNode1.size(); i++) {
				int a = metricsNode1.get(i);
				int b = metricsNode2.get(i);
				int total = a - b;
				result.add(total);
				System.out.println(a + " " + b + " " + total);
			}
			temp.fInformation = result;
		}
		return temp;
	}

	// Operation sum:
	public static Node sum(Node node1, Node node2) {

		Node temp = new Node();
		ArrayList<Integer> metricsNode1;
		ArrayList<Integer> metricsNode2;
		ArrayList<Integer> result = new ArrayList<>();

		metricsNode1 = node1.fInformation;
		metricsNode2 = node2.fInformation;
		if (node1.fNameNode.equals(node2.fNameNode)) {
			temp.setLabel(node1.fNameNode);
			for (int i = 0; i < metricsNode1.size(); i++) {
				int a = metricsNode1.get(i);
				int b = metricsNode2.get(i);
				int total = a + b;
				result.add(total);
			}
			temp.fInformation = result;
		}
		return temp;
	}

	// Eliminates the null:
	public static Queue<Node> eliminateNull(Queue<Node> queue) {
		Queue<Node> aux = new LinkedList<Node>();
		Queue<Node> result = new LinkedList<Node>();
		Node temp;
		aux = queue;
		while (!aux.isEmpty()) {
			temp = aux.poll();
			if (temp.fNameNode != null)
				result.add(temp);
		}
		return result;

	}

	// Level order traversal:
	public static Queue<Node> levelOrderTraversal(Node startNode) {
		System.out.println("levelOrderTraversal");
		Queue<Node> queue = new LinkedList<Node>();
		Queue<Node> result = new LinkedList<Node>();
		// Put the first node on the list

		Node tempNode;
		queue.add(startNode);

		while (!queue.isEmpty()) {

			tempNode = queue.poll();
			result.add(tempNode); // put in the result

			System.out.print(tempNode.fInformation);

			if (tempNode.fLeft != null)
				queue.add(tempNode.fLeft);

			if (tempNode.fRight != null)
				queue.add(tempNode.fRight);

		}
		return result;
	}

	// Level order traversal with visitor:
	public static Queue<Node> levelOrderTraversal(Node startNode, ITreeVisitor visitor) {
		System.out.println("levelOrderTraversal");
		Queue<Node> queue = new LinkedList<Node>();
		Queue<Node> result = new LinkedList<Node>();
		// Put the first node on the list

		Node tempNode;
		queue.add(startNode);

		while (!queue.isEmpty()) {

			tempNode = queue.poll();
			visitor.visitNode(tempNode); // send the result to visitorNode
			result.add(tempNode); // put in the result

			System.out.print(tempNode.fInformation);

			if (tempNode.fLeft != null)
				queue.add(tempNode.fLeft);

			if (tempNode.fRight != null)
				queue.add(tempNode.fRight);

		}

		return result;
	}

	//This method does creates a levelOrderTraversal and then does the sort:
	public static Queue<Node> Sort(Node tree1) {
		Queue<Node> Q1 = Node.levelOrderTraversal(tree1);
		System.out.print("Entrance:" + Q1.size());
		Queue<Node> q1 = new LinkedList<Node>();
		ArrayList<Node> AL2 = new ArrayList<>();


		AL2 = convertQueue(Q1);
		System.out.print("AL size:" + AL2.size());
		
		Collections.sort(AL2);

		q1 = convertArrayList(AL2);
		System.out.print("Q1 size:" + q1.size());
		return q1;

	}

	//This method converts Queues into ArrayList
	public static ArrayList<Node> convertQueue(Queue tree) {
		ArrayList<Node> Q2 = new ArrayList<>();

		Node temp;
		Queue<Node> queue = new LinkedList<Node>();
		queue = tree;

		while (!queue.isEmpty()) {
			temp = queue.poll();
			Q2.add(temp);
		}
		return Q2;
	}

	// This method converts ArrayList into Queues 
	public static Queue<Node> convertArrayList(ArrayList<Node> tree) {

		Queue<Node> queue = new LinkedList<Node>();
		System.out.println("tree size: " + tree.size());
		for (int i = 0; i < tree.size(); i++) {
			queue.add(tree.get(i));
		}
		return queue;
	}

	// Pos order:
	public static void printPos(Node tree) {
		if (tree == null)
			return;
		System.out.print(tree.fInformation);
		printPos(tree.fRight);
		printPos(tree.fLeft);

	}

	// Pre order:
	public static void printPre(Node tree) {
		if (tree == null)
			return;
		System.out.print(tree.fInformation);
		printPre(tree.fLeft);
		printPre(tree.fRight);
	}

	// In order:
	public static void printIn(Node tree, ITreeVisitor visitor) {
		if (tree == null)
			return;
		printIn(tree.fLeft, visitor);
		// Visit the node by Printing the node data
		visitor.visit(tree);
		printIn(tree.fRight, visitor);

	}

	// In order:
	public static void printIn(Node tree) {
		if (tree == null)
			return;
		printIn(tree.fLeft);
		// Visit the node by Printing the node data
		System.out.print(tree.fInformation);
		printIn(tree.fRight);

	}

	// In order:
	public static ArrayList<Integer> In(Node tree) {
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
	public static ArrayList<Integer> Pre(Node tree) {
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
	public static ArrayList<Integer> Pos(Node tree) {
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

	/*
	 * Compare: public static boolean compare(Node A, Node B) {
	 * ArrayList<Integer> temp1 = Pos(A); ArrayList<Integer> temp2 = Pos(B);
	 * 
	 * // Quick Check to see if the two arrayLists have the same number of //
	 * elements if (temp1.size() != temp2.size()) return false;
	 * 
	 * // Optionally Sort the arrays - avoid returning false if the elements //
	 * are the same but // have been stored out of sequence
	 * Collections.sort(temp1); Collections.sort(temp2);
	 * 
	 * if (temp1.hashCode() == temp2.hashCode()) { return true; } else { return
	 * false; }
	 * 
	 * }
	 */
	@Override
	public int compareTo(Node d) {
		return (this.fNameNode).compareToIgnoreCase(d.fNameNode);
	}

	@Override
	public int compare(Node node1, Node node2) {
		int compare = node1.compareTo(node2);
		return compare;
	}
}