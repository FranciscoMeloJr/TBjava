/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package treestructure;
import java.util.*;
import static treestructure.BinaryTree.printPre;
import static treestructure.BinaryTree.printPos;
import static treestructure.BinaryTree.printIn;
import static treestructure.BinaryTree.In;
import static treestructure.BinaryTree.Pre;
import static treestructure.BinaryTree.Pos;
import static treestructure.BinaryTree.compare;
/**
 *
 * @author francisco
 */
public class TreeTest {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
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
            
            //printPre(root);
            //System.out.println(" ");
            //printPos(root);
            //System.out.println(" ");
            //printIn(root);
            System.out.print(In(root));
            System.out.println(" ");
            System.out.print(Pre(root));
            System.out.println(" ");
            System.out.print(Pos(root));
            System.out.println(" ");
            System.out.printf(Boolean.toString(compare(right,root)));
        }
        catch (Exception e)
       {
            e.printStackTrace ();
        }
    }
    
}
