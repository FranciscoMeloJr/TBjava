package package1;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author francisco
 */
public class Tree { 
        ArrayList information;
        String nameNode;
        ArrayList<Tree> branches = new ArrayList<Tree>();
        
        public Tree(){  
            information = new ArrayList();
        }
        public Tree (int cargo, Tree new_branch) { 
            information = new ArrayList();
            this.information.add(cargo);
            this.branches.add(new_branch);
        }
        
        public Tree (Tree new_branch1, Tree new_branch2) { 
            
            this.branches.add(new_branch1);
            this.branches.add(new_branch2);
        }
        
        public Tree (int a) { 
            information = new ArrayList();
            this.information.add(a);
        }
        void add(int a)
        {
            this.information.add(a);
        }
        void addNode(Tree tree)
        {
            this.branches.add(tree);
        }

        void delete(int a)
        {
            int index = this.information.indexOf(a);
            this.information.remove(index);
        }
        
        //Pos order:
        public static void printPos (Tree tree) { 
            if (tree == null) 
                return; 
            System.out.print(tree.information); 
            //printPos(tree.right);
            //printPos(tree.left); 
 
    } 
        //Pre order:
        public static void printPre (Node tree) { 
            if (tree == null) 
                return; 
            System.out.print(tree.fInformation); 
            printPre(tree.fLeft); 
            printPre(tree.fRight); 
    } 
        //In order:
        public static void printIn  (Node tree) { 
            if (tree == null) 
                return; 
            printIn(tree.fLeft);  
            //Visit the node by Printing the node data    
            System.out.print(tree.fInformation);  
            printIn(tree.fRight);  

    } 

        //In order:
        public static  ArrayList In (Node tree) { 
            ArrayList temp = new ArrayList(); 
            if (tree == null) 
            {
                return temp; 
            }
            else
            {
                temp.addAll(In(tree.fLeft));  
                temp.addAll(tree.fInformation);
                temp.addAll(In(tree.fRight));  //printIn(tree.right);
            }
            return temp;
        }
        
        //Pre order:
        public static  ArrayList Pre (Node tree) { 
            ArrayList temp = new ArrayList(); 
            if (tree == null) 
            {
                return temp; 
            }
            else
            {
                temp.addAll(tree.fInformation);
                temp.addAll(Pre(tree.fLeft));  
                temp.addAll(Pre(tree.fRight));  //printIn(tree.right);
            }
            return temp;
        }
        
        //Pos:
        public static  ArrayList Pos (Node tree) { 
            ArrayList temp = new ArrayList(); 
            if (tree == null) 
            {
                return temp; 
            }
            else
            {
                temp.addAll(Pos(tree.fLeft));  
                temp.addAll(Pos(tree.fRight));  //printIn(tree.right);
                temp.addAll(tree.fInformation);
     
            }
            return temp;
        }
       
        //Compare:
        public static boolean compare(Node A, Node B)
        {    
            ArrayList temp1 = Pos(A);
            ArrayList temp2 = Pos(B);
            
            // Quick Check to see if the two arrayLists have the same number of elements
            if (temp1.size() != temp2.size())
                return false;

            // Optionally Sort the arrays - avoid returning false if the elements are the same but 
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