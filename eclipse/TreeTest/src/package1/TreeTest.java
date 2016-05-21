package package1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import package1.Node.ITreeVisitor;
import package1.Node;

/**
 *
 * @author francisco
 */
public class TreeTest {
	
	public class CounterVisitor implements ITreeVisitor {

		public int fCount = 0;
		
		@Override
		public void visit(Node node) {
			fCount++;
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
        
        Integer[] expected = new Integer[]{ 4, 1, 6, 0, 5, 2, 3 };
        final ArrayList<Integer> actual = new ArrayList<>();
        Node.printIn(root, new ITreeVisitor() {

			@Override
			public void visit(Node node) {
				actual.addAll(node.fInformation);
				System.out.println("visit " + node.fInformation.toString());
			}
        });
        
        CounterVisitor counterVisitor = new CounterVisitor();
        Node.printIn(root, counterVisitor);
        
        System.out.println(Arrays.toString(expected));
        System.out.println(actual);

        assertEquals(expected.length, counterVisitor.fCount);
        assertTrue(expected.length == actual.size());
        assertEquals(expected.length, actual.size());
        assertNotNull(actual);
        assertTrue(actual != null);
        
        for (int i = 0; i < expected.length; i++) {
        	int a = expected[i];
        	int b = actual.get(i);
        	assertTrue(a == b);
        }
        
        //root.levelOrderTraversal(root);
        //root2.levelOrderTraversal(root);
        
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
