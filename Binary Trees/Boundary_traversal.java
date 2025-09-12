import java.util.*;

public class Main{
  static class Node{
    int data;
    Node left;
    Node right;
    
    Node(int d){
      this.data = d;
    }
  }
  
  public static boolean isLeave(Node root){
    if(root.left == null && root.right == null) return true;
    return false;
  }
  
  public static void addLeft(Node root, List<Integer> list){
    Node curr = root.left;
    
    while(curr != null){
      if(isLeave(curr) == false ) list.add(curr.data);
      if(curr.left != null) curr = curr.left;
      else curr = curr.right;
    }
  }
  
  public static void addLeaves(Node root, List<Integer> list){
    if(root == null) return;
    
    addLeaves(root.left, list);
    if(isLeave(root) == true) list.add(root.data);
    addLeaves(root.right, list);
  }
  
  public static void addRight(Node root, List<Integer> list){
    Stack<Integer> st = new Stack<>();
    Node curr = root.right;
    
    while(curr != null){
      if(isLeave(curr) == false) st.push(curr.data);
      if(curr.right != null) curr = curr.right;
      else curr = curr.left;
    }
    
    while(!st.isEmpty()){
      list.add(st.pop());
    }
  }
  
  public static void main(String args[]){
    Node root = new Node(1);
    
    root.left = new Node(2);
    root.left.left = new Node(3);
    root.left.left.right = new Node(4);
    root.left.left.right.left = new Node(5);
    root.left.left.right.right = new Node(6);
    
    
    
    root.right = new Node(7);
    root.right.right = new Node(8);
    root.right.right.left = new Node(9);
    root.right.right.left.left = new Node(10);
    root.right.right.left.right = new Node(11);
    
    List<Integer> ans = new ArrayList<>();
    ans.add(root.data);
    addLeft(root, ans);
    addLeaves(root, ans);
    addRight(root, ans);
    
    System.out.println(ans); // [1, 2, 3, 4, 5, 6, 10, 11, 9, 8, 7]
    
  }
}
