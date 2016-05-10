package package1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import package1.BinaryTree.ITreeVisitor;

/**
 *
 * @author francisco
 */
public class TreeTest {
	
	public class CounterVisitor implements ITreeVisitor {

		public int fCount = 0;
		
		@Override
		public void visit(BinaryTree node) {
			fCount++;
		}
		
	}
	
	@Test
	public void testCreateTree() {
        System.out.println("Test");

        BinaryTree left = new BinaryTree (1); 
        BinaryTree right = new BinaryTree (2); 
        BinaryTree rightright = new BinaryTree (3);
        BinaryTree leftleft = new BinaryTree (4);
        BinaryTree rightleft = new BinaryTree (5);
        BinaryTree leftright = new BinaryTree (6);
        
        left.addNodeL(leftleft);
        left.addNodeR(leftright);
        right.addNodeR(rightright);
        right.addNodeL(rightleft);
        
        BinaryTree root = new BinaryTree (left, right); 
       
        root.PosOrder(root);
        
        Integer[] expected = new Integer[]{ 4, 1, 6, 0, 5, 2, 3 };
        final ArrayList<Integer> actual = new ArrayList<>();
        BinaryTree.printIn(root, new ITreeVisitor() {

			@Override
			public void visit(BinaryTree node) {
				actual.addAll(node.fInformation);
				System.out.println("visit " + node.fInformation.toString());
			}
        	
        });
        
        CounterVisitor counterVisitor = new CounterVisitor();
        BinaryTree.printIn(root, counterVisitor);
        
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
