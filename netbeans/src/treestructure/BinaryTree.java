/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treestructure;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author francisco
 */
public class BinaryTree { 
        ArrayList information;
        String nameNode;
        BinaryTree left;
        BinaryTree right;
        
        void Tree(){  
            information = new ArrayList();
        }
        public BinaryTree (int cargo, BinaryTree left, BinaryTree right) { 
            information = new ArrayList();
            this.information.add(cargo);
            this.left = left; 
            this.right = right; 
        }
        
        public BinaryTree (BinaryTree left, BinaryTree right) { 
            information = new ArrayList();
            this.left = left; 
            this.right = right; 
        }
        public BinaryTree (int a) { 
            information = new ArrayList();
            this.information.add(a);
        }
        void add(int a)
        {
            this.information.add(a);
        }
        void addNodeR(BinaryTree tree)
        {
            this.right = tree; 
        }
        void addNodeL(BinaryTree tree)
        {
            this.left = tree; 
        }
        void delete(int a)
        {
            int index = this.information.indexOf(a);
            this.information.remove(index);
        }
        
        //Pos order:
        public static void printPos (BinaryTree tree) { 
            if (tree == null) 
                return; 
            System.out.print(tree.information); 
            printPos(tree.right);
            printPos(tree.left); 
 
    } 
        //Pre order:
        public static void printPre (BinaryTree tree) { 
            if (tree == null) 
                return; 
            System.out.print(tree.information); 
            printPre(tree.left); 
            printPre(tree.right); 
    } 
        //In order:
        public static void printIn  (BinaryTree tree) { 
            if (tree == null) 
                return; 
            printIn(tree.left);  
            //Visit the node by Printing the node data    
            System.out.print(tree.information);  
            printIn(tree.right);  

    } 

        //In order:
        public static  ArrayList In (BinaryTree tree) { 
            ArrayList temp = new ArrayList(); 
            if (tree == null) 
            {
                return temp; 
            }
            else
            {
                temp.addAll(In(tree.left));  
                temp.addAll(tree.information);
                temp.addAll(In(tree.right));  //printIn(tree.right);
            }
            return temp;
        }
        
        //Pre order:
        public static  ArrayList Pre (BinaryTree tree) { 
            ArrayList temp = new ArrayList(); 
            if (tree == null) 
            {
                return temp; 
            }
            else
            {
                temp.addAll(tree.information);
                temp.addAll(Pre(tree.left));  
                temp.addAll(Pre(tree.right));  //printIn(tree.right);
            }
            return temp;
        }
        
        //Pos:
        public static  ArrayList Pos (BinaryTree tree) { 
            ArrayList temp = new ArrayList(); 
            if (tree == null) 
            {
                return temp; 
            }
            else
            {
                temp.addAll(Pos(tree.left));  
                temp.addAll(Pos(tree.right));  //printIn(tree.right);
                temp.addAll(tree.information);
     
            }
            return temp;
        }
       
        //Compare:
        public static boolean compare(BinaryTree A, BinaryTree B)
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
