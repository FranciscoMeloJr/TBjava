package package1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.junit.Test;

import package1.Node.ITreeVisitor;
import package1.Node;

/**
 *
 * @author francisco
 */
public class TreeTest {

	// Original class:
	public class CounterVisitor implements ITreeVisitor {

		public int fCount = 0;

		@Override
		public void visit(Node node) {
			fCount++;
		}

		@Override
		public void visitNode(Node result) {
			// TODO Auto-generated method stub

		}

	}

	// New class:
	public class OrderVisitor implements ITreeVisitor {
		// Well, in fact, the actual is independent, therefore just one variable
		// was necessary.
		// But, in this case we are doing 2 tests at the same time
		final ArrayList<Integer> actual_in = new ArrayList<>();
		final ArrayList<Integer> actual_level = new ArrayList<>();

		@Override
		public void visit(Node node) {

			actual_in.addAll(node.fInformation);
			// System.out.println("visit " + node.fInformation.toString());

		}

		public void visitNode(Node tempNode) {
			actual_level.addAll(tempNode.fInformation);
		}

	}

	public Node createTree(String[] labels, int[] numbers) {
		Node[] a = new Node[labels.length];
		Node newNode = new Node();

		int i;
		for (i = 0; i < labels.length; i++) {
			a[i].fInformation.add(numbers[i]);
			a[i].fNameNode = labels[i];
		}

		Node temp = Node.merge(a[i], a[i + 1], a[i + 2]);
		return temp;
	}

	public Node createTree1(String[] labels, int[] cargos) {
		// [0],[1],[2],[3],[4],[5],[6],[7],
		Node left = new Node(cargos[1], labels[1]);
		Node right = new Node(cargos[2], labels[2]);
		Node rightright = new Node(cargos[3], labels[3]);
		Node leftleft = new Node(cargos[4], labels[4]);
		Node rightleft = new Node(cargos[5], labels[5]);
		Node leftright = new Node(cargos[6], labels[6]);

		left.addNodeL(leftleft);
		left.addNodeR(leftright);
		right.addNodeR(rightright);
		right.addNodeL(rightleft);

		Node root = new Node(cargos[0], left, right, labels[0]);

		return root;
	}

	public Node createTree2() {
		Node left = new Node(1, String.valueOf('A'));
		Node right = new Node(2, String.valueOf('C'));
		Node rightright = new Node(6, String.valueOf('G'));
		Node leftleft = new Node(3, String.valueOf('D'));
		Node rightleft = new Node(5, String.valueOf('F'));
		Node leftright = new Node(4, String.valueOf('C'));

		left.addNodeL(leftleft);
		left.addNodeR(leftright);
		right.addNodeR(rightright);
		right.addNodeL(rightleft);

		Node root = new Node(0, left, right, String.valueOf('A'));

		return root;
	}

	public Tree createTree(int x) {
		Tree root = new Tree(x, "root", null);
		Tree temp = null;
		/*
		 * for(int i=0;i<10;i++) { Random r = new Random(); char c =
		 * (char)(r.nextInt(26) + 'a'); temp = new Tree(0, String.valueOf(c),
		 * null); }
		 */
		temp = new Tree(x + 1, String.valueOf('A'), null);
		root.addNode(temp);
		temp = new Tree(x + 2, String.valueOf('B'), null);
		root.addNode(temp);
		temp = new Tree(x + 3, String.valueOf('C'), null);
		root.addNode(temp);
		temp = new Tree(x + 4, String.valueOf('D'), null);
		root.addNode(temp);
		return root;
	}

	@Test
	public void testCreateTree() {
		System.out.println("Test");

		Tree root = createTree(3);
		Tree root2 = createTree(2);
		Queue<Tree> temp1, temp2;
		Queue<Tree> aux;
		
		// Queue<Node> temp1 = Node.levelOrderTraversal(root, orderVisitor);
		temp1 = Tree.Sort(root);
		Tree.Print(temp1);
		
		// Queue<Node> temp2 = Node.levelOrderTraversal(root2, orderVisitor);
		
		temp2 = Tree.Sort(root2);
		System.out.println("\n Size 1 : "+ temp1.size());
		System.out.println("\n Size 2 : "+temp2.size());
		
		Tree.Print(temp2);
		// Queue<Node> temp3 = Node.Sort(root);

		aux =  Tree.doMinus(temp1, temp2);
		Tree.Display(aux);
		
		assertTrue(aux.size() == 4);
		assertTrue("some test", true);

	}

}
