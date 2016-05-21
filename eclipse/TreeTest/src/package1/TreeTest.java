package package1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;

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

	@Test
	public void testCreateTree() {
		System.out.println("Test");

		Node left = new Node(1, String.valueOf('B'));
		Node right = new Node(2, String.valueOf('C'));
		Node rightright = new Node(6, String.valueOf('G'));
		Node leftleft = new Node(3, String.valueOf('D'));
		Node rightleft = new Node(5, String.valueOf('F'));
		Node leftright = new Node(4, String.valueOf('E'));

		left.addNodeL(leftleft);
		left.addNodeR(leftright);
		right.addNodeR(rightright);
		right.addNodeL(rightleft);

		Node root = new Node(0, left, right, String.valueOf('A'));
		
		Node root2 = new Node(left, right); //By default label = A

		root.PosOrder(root);

		Integer[] expected_in = new Integer[] { 3, 1, 4, 0, 5, 2, 6 };
		Integer[] expected_level = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };

		OrderVisitor orderVisitor = new OrderVisitor();
		Node.printIn(root, orderVisitor);

		Queue<Node> temp1 = Node.levelOrderTraversal(root, orderVisitor);
		Queue<Node> temp2 = Node.levelOrderTraversal(root, orderVisitor);

		CounterVisitor counterVisitor = new CounterVisitor();
		Node.printIn(root, counterVisitor);

		// Showing expected x actual:
		System.out.println("Expected in" + Arrays.toString(expected_in));
		System.out.println("In ordr result" + orderVisitor.actual_in);
		System.out.println("level ordr" + orderVisitor.actual_level);
		//Queue:
		System.out.println("x");
		Print(temp1);
		Print(temp2);
		doMinus(temp1,temp2);
		
		// Asserts expect from counter visitor- actual_in
		assertEquals(expected_in.length, counterVisitor.fCount);
		assertTrue(expected_in.length == orderVisitor.actual_in.size());
		assertEquals(expected_in.length, orderVisitor.actual_in.size());
		assertNotNull(orderVisitor.actual_in);
		assertTrue(orderVisitor.actual_in != null);

		// Compare each one with the assertion
		for (int i = 0; i < expected_in.length; i++) {
			int a = expected_in[i];
			int b = orderVisitor.actual_in.get(i);
			assertTrue(a == b);
		}

		// Node.levelOrderTraversal(root);
		// Node.levelOrderTraversal(root2);

		assertTrue("some test", true);

		/*
		
		*/
		// printPre(root);
		// System.out.println(" ");
		// printPos(root);
		// System.out.println(" ");
		// printIn(root);
		/*
		 * System.out.print(In(root)); System.out.println(" ");
		 * System.out.print(Pre(root)); System.out.println(" ");
		 * System.out.print(Pos(root)); System.out.println(" ");
		 * System.out.printf(Boolean.toString(compare(right,root)));
		 */

	}
	//This function just print queue
	public static void Print(Queue<Node> N)
	{
		System.out.print("Queue: ");
		Node temp;
		while(!N.isEmpty())
		{	
			temp = N.poll();
			System.out.print(temp.fNameNode);
		}
		
	}
	//Do the minus operation and return a new Queue
	public static Queue<Node> doMinus(Queue<Node> N1, Queue<Node> N2)
	{
		System.out.print("Minus Operation");
		Node temp;
		Queue<Node> result = null;
		while(!N1.isEmpty())
		{	
			temp = Node.minus(N1.poll(),N2.poll());
			System.out.print(temp.fNameNode);
			result.add(temp);
		}
		Print(result);
		return result;
	}

}
