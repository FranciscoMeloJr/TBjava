package package1;

public class TestMain {
    public static void main()
    {
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
    	
    	
    }
}
