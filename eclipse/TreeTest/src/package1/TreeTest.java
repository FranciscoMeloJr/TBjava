package package1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	
	//Original class:
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
	//New class:
	public class OrderVisitor implements ITreeVisitor{
		final ArrayList<Integer> actual = new ArrayList<>();
		final ArrayList<Integer> actual_level = new ArrayList<>();
		
		@Override
		public void visit(Node node) {
			System.out.println("Calling visit");
			
			actual.addAll(node.fInformation);
			System.out.println("visit " + node.fInformation.toString());
		
		}	
		public void visitNode(Node tempNode) {
			 actual_level.addAll(tempNode.fInformation);
		}

    }
	
	@Test
	public void testCreateTree() {
        System.out.println("Test");

        Node left = new Node(1); 
        Node right = new Node(2); 
        Node rightright = new Node(3);
        Node leftleft = new Node(4);
        Node rightleft = new Node(5);
        Node leftright = new Node(6);
        
        left.addNodeL(leftleft);
        left.addNodeR(leftright);
        right.addNodeR(rightright);
        right.addNodeL(rightleft);
        
        Node root = new Node (left, right); 
        Node root2 = new Node (left, right);
        
        root.PosOrder(root);
        
        Integer[] expected_in = new Integer[]{ 4, 6, 1, 5, 3, 2, 0 };
        Integer[] expected_level = new Integer[]{ 0, 1, 2, 3, 4, 5, 6 };
        
        OrderVisitor orderVisitor = new OrderVisitor();
        Node.printIn(root, orderVisitor);
        
        CounterVisitor counterVisitor = new CounterVisitor();
        Node.printIn(root, counterVisitor);
        
        Node.levelOrderTraversal(root, orderVisitor);
        
        //Showing expected x actual:
        System.out.println(Arrays.toString(expected_in));
        System.out.println(orderVisitor.actual);

        //Asserts expect from counter visitor
        assertEquals(expected_in.length, counterVisitor.fCount);
        assertTrue(expected_in.length == orderVisitor.actual.size());
        assertEquals(expected_in.length, orderVisitor.actual.size());
        assertNotNull(orderVisitor.actual);
        assertTrue(orderVisitor.actual != null);
        
        //Compare each one with the assertion
        for (int i = 0; i < expected_in.length; i++) {
        	int a = expected_in[i];
        	int b = orderVisitor.actual.get(i);
        	assertTrue(a == b);
        }
        
        //Node.levelOrderTraversal(root);
        //Node.levelOrderTraversal(root2);
        
        assertTrue("some test", true);
        
        /*

        */
        //printPre(root);
        //System.out.println(" ");
        //printPos(root);
        //System.out.println(" ");
        //printIn(root);
        /*
        System.out.print(In(root));
        System.out.println(" ");
        System.out.print(Pre(root));
        System.out.println(" ");
        System.out.print(Pos(root));
        System.out.println(" ");
        System.out.printf(Boolean.toString(compare(right,root)));
        */
		
	}
    
}
