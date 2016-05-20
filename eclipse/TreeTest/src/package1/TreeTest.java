package package1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;

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
			/*
			 * the level-order traversal logic should do the sorting
				doLevelOrderTraversal(root1, root2):
				queue1.add(root1)
				queue2.add(root2)
				visitNode(queue1.dequeue(), queue2.dequeue())
				do that until queue1 or queue2 has items 
				(in fact, you should compare the node labels, because the visitor should receive nodes with same labels only)
				you add all children of visited nodes
				sort and repeat
			 * 
			 * */
			public void visitNode(Node tree1, Node tree2) {
				//actual.addAll(node.fInformation);
				//System.out.println("visit " + node.fInformation.toString());
				Queue<Node> Q1 = Node.levelOrderTraversal(tree1);
				Queue<Node> Q2 = Node.levelOrderTraversal(tree2);
					
				Queue<Node> result = null; 	
				
				while(!Q1.isEmpty() && !Q2.isEmpty())
				{
					result.add(Node.minus(Q1.poll(),Q1.poll()));
				}
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
        
        root.levelOrderTraversal(root);
        root2.levelOrderTraversal(root);
        
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
