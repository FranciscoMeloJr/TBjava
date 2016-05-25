package package1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author francisco
 */
public class Tree implements Comparator<Node>, Comparable<Node> {
	ArrayList<Integer> finformation;
	String fNameNode;
	ArrayList<Tree> nodes = new ArrayList<Tree>();

	public Tree() {
		finformation = new ArrayList();
	}

	public Tree(int cargo, Tree new_branch) {
		finformation = new ArrayList();
		this.finformation.add(cargo);
		this.nodes.add(new_branch);
	}

	public Tree(int cargo, String label, Tree new_branch) {
		finformation = new ArrayList();
		this.finformation.add(cargo);
		this.fNameNode = label;
		if (new_branch != null)
			this.nodes.add(new_branch);
	}

	public Tree(Tree new_branch1, Tree new_branch2) {

		this.nodes.add(new_branch1);
		this.nodes.add(new_branch2);
	}

	public Tree(ArrayList<Tree> new_branch1) {

		this.nodes.addAll(new_branch1);
	}

	public Tree(int a) {
		finformation = new ArrayList();
		this.finformation.add(a);
	}

	void add(int a) {
		this.finformation.add(a);
	}

	void addNode(Tree tree) {
		this.nodes.add(tree);
	}

	void delete(int a) {
		int index = this.finformation.indexOf(a);
		this.finformation.remove(index);
	}

	void setLabel(String label) {
		fNameNode = label;
	}

	public String toString() {
		return (fNameNode + " " + finformation.size() + " " + nodes.size());

	}

	// Pos order:
	public static void printPos(Tree tree) {
		if (tree == null)
			return;
		System.out.print(tree.finformation);
		// printPos(tree.right);
		// printPos(tree.left);

	}

	// Pre order:
	public static void printPre(Node tree) {
		if (tree == null)
			return;
		System.out.print(tree.fInformation);
		printPre(tree.fLeft);
		printPre(tree.fRight);
	}

	// This method creates a levelOrderTraversal and then does the sort:
	public static Queue<Tree> Sort(Tree tree1) {

		Queue<Tree> Q1 = Tree.levelOrderTraversal(tree1);

		// System.out.print("Entrance:" + Q1.size());
		Queue<Tree> q1 = new LinkedList<Tree>();
		ArrayList<Tree> AL2 = new ArrayList<>();
		ArrayList<Tree> AL3 = new ArrayList<>();

		AL2 = convertQueue(Q1);

		// System.out.print("AL size:" + AL3.size());

		// Collections.sort(AL2);
		AL3 = mergeArrayList(AL2);

		q1 = convertArrayList(AL3);
		// System.out.print("Q1 size:" + q1.size());
		return q1;

	}

	// Minus
	// Do the minus operation and return a new Queue
	public static Queue<Tree> doMinus(Queue<Tree> N1, Queue<Tree> N2) {
		System.out.println("\n" + "Minus Operation ");
		Tree temp, temp2, temp1;
		Queue<Tree> result = new LinkedList<Tree>();

		// Print(N1);
		// Print(N2);
		System.out.println("size" + N1.size() + " " + N2.size());
		while (N1.size() > 0 && N2.size() > 0) {
			// System.out.print("while");
			temp1 = N1.poll();
			temp2 = N2.poll();
			// System.out.print(temp1.fInformation +" " + temp2.fInformation);
			temp = Tree.minus(temp1, temp2);
			result.add(temp);
		}
		// System.out.print("while 2");
		Queue<Tree> newResult = Tree.eliminateNull(result);
		Queue<Tree> newResult2 = Print(newResult);

		// System.out.print("print");
		return newResult2;
	}

	// This function displays the differences:
	public static void Display(Queue<Tree> N) {
		System.out.print("\n" + "Queue size:" + N.size() + " ");
		
		for (Tree temp : N) {
			System.out.print("[" + temp.fNameNode);
			for (int i = 0; i < Math.abs(temp.finformation.get(0)); i++) {
				System.out.print("-");
			}
			System.out.print("]");
		}

	}

	// This function performs the minus operation
	public static Tree minus(Tree node1, Tree node2) {

		// System.out.print("Minus");
		Tree temp = new Tree();
		ArrayList<Integer> metricsNode1;
		ArrayList<Integer> metricsNode2;
		ArrayList<Integer> result = new ArrayList<>();

		metricsNode1 = node1.finformation;
		metricsNode2 = node2.finformation;
		if (node1.fNameNode.equals(node2.fNameNode)) {
			temp.setLabel(node1.fNameNode);
			for (int i = 0; i < metricsNode1.size(); i++) {
				int a = metricsNode1.get(i);
				int b = metricsNode2.get(i);
				int total = a - b;
				result.add(total);
				System.out.println(a + " " + b + " " + total);
			}
			temp.finformation = result;
		}
		return temp;
	}

	// This function prints the queue
	public static Queue<Tree> Print(Queue<Tree> N) {
		System.out.print("Queue: ");
		for (Tree t : N)
			System.out.print(t.fNameNode + " " + t.finformation);
		return N;
	}

	// This function eliminates the null:
	public static Queue<Tree> eliminateNull(Queue<Tree> queue) {
		Queue<Tree> aux = new LinkedList<Tree>();
		Queue<Tree> result = new LinkedList<Tree>();
		Tree temp;
		aux = queue;
		while (!aux.isEmpty()) {
			temp = aux.poll();
			if (temp.fNameNode != null)
				result.add(temp);
		}
		return result;

	}

	// This function merges nodes with the same label:
	public static ArrayList<Tree> mergeArrayList(ArrayList<Tree> AL) {
		ArrayList<Tree> temp = new ArrayList<Tree>();
		ArrayList<Tree> XXX = new ArrayList<Tree>();
		Tree newNode;
		temp = AL;
		// System.out.println(" Merge Array List");
		// System.out.print("Before ");
		// TreeTest.Print(Node.convertArrayList(AL));

		for (int i = 1; i < temp.size(); i++) {
			Tree obj = temp.get(i);
			for (int j = 0; j < temp.size(); j++) {
				if (obj.equals(temp.get(j)) && (i != j)) {
					// System.out.println(i + " Equals " + j);
					obj.mergePositive(temp.get(j)); // merge
					temp.remove(j);
				}

			}
			XXX.add(obj);
		}
		// System.out.print("After merging ");
		// TreeTest.Print(Node.convertArrayList(XXX));
		return XXX;
	}

	// This function merge two nodes with the same label: A[1] + A[2] = A[2]
	void mergePositive(Tree tree) {
		ArrayList<Integer> metricsNode1;
		ArrayList<Integer> metricsNode2;
		ArrayList<Integer> result = new ArrayList<>();
		if (tree.fNameNode.equals(fNameNode)) {
			metricsNode1 = tree.finformation;
			metricsNode2 = finformation;
			for (int i = 0; i < metricsNode1.size(); i++) {
				int a = metricsNode1.get(i);
				int b = metricsNode2.get(i);
				int total = a + b;
				result.add(total);
				// System.out.println(a + " " + b + " " + total);
			}
			finformation = result;
		}
	}

	// This method converts Queues into ArrayList
	public static ArrayList<Tree> convertQueue(Queue tree) {

		ArrayList<Tree> Q2 = new ArrayList<>();

		Tree temp;
		Queue<Tree> queue = new LinkedList<Tree>();
		queue = tree;

		while (!queue.isEmpty()) {
			temp = queue.poll();
			Q2.add(temp);
		}
		return Q2;
	}

	// This method converts ArrayList into Queues
	public static Queue<Tree> convertArrayList(ArrayList<Tree> tree) {

		Queue<Tree> queue = new LinkedList<Tree>();
		// System.out.println("tree size: " + tree.size());
		for (int i = 0; i < tree.size(); i++) {
			queue.add(tree.get(i));
		}
		return queue;
	}

	// Level order traversal:
	public static Queue<Tree> levelOrderTraversal(Tree startNode) {

		Queue<Tree> queue = new LinkedList<Tree>();
		Queue<Tree> result = new LinkedList<Tree>();
		// Put the first node on the list

		Tree tempNode;
		queue.add(startNode);

		while (!queue.isEmpty()) {

			tempNode = queue.poll();
			result.add(tempNode); // put in the result

			for (int i = 0; i < tempNode.nodes.size(); i++) {
				queue.add(tempNode.nodes.get(i));
			}

		}

		return result;
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
	public static ArrayList In(Node tree) {
		ArrayList temp = new ArrayList();
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
	public static ArrayList Pre(Node tree) {
		ArrayList temp = new ArrayList();
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
	public static ArrayList Pos(Node tree) {
		ArrayList temp = new ArrayList();
		if (tree == null) {
			return temp;
		} else {
			temp.addAll(Pos(tree.fLeft));
			temp.addAll(Pos(tree.fRight)); // printIn(tree.right);
			temp.addAll(tree.fInformation);

		}
		return temp;
	}

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