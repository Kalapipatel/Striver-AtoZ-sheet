import java.util.*;

public class Main {
    
    static class Node{
      int data;
      Node left;
      Node right;
      
      Node(int d){
        this.data = d;
      }
    }
    
    public static void preOrder(Node root){
      if(root == null) return;
      
      System.out.print(root.data + " ");
      preOrder(root.left);
      preOrder(root.right);
    }
    
    public static int maxHeight(Node root){
      if(root == null) return 0;
      
      int left = maxHeight(root.left);
      int right = maxHeight(root.right);
      
      return 1 + Math.max(left, right);
    }
    
    public static void main(String[] args) {
      
      Node root = new Node(1);
      root.left = new Node(2);
      root.right = new Node(3);
      
      root.right.left = new Node(4);
      root.right.right = new Node(6);
      
      root.right.left.left = new Node(5);
      
      preOrder(root);
      System.out.println();
      
      System.out.println(maxHeight(root));
  }
}
