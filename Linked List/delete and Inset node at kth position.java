import java.util.*;

public class Main {
    static class Node{
        int data;
        Node next;
        
        Node(int d){
          this.data = d;
          this.next = null;
        }
    }
    
    public static void printLinkedList(Node head){
        System.out.println();
        Node temp = head;
        
        while(temp != null){
          System.out.print(temp.data + " -> ");
          temp = temp.next;
        }
        
        System.out.print("null");
    }
    
    public static Node deleteNode(Node head, int k){
        Node temp = head;
        Node prev = null;
        int cnt = 1;
        
        if(head == null || head.next == null) return null;
        
        if(k == 1) {
            head = head.next;
            return head;
        }
        
        while(temp != null){
            
            if(cnt == k){
              prev.next = prev.next.next;
            }
            
            prev = temp;
            temp =  temp.next;
            cnt++;
        }
        
        return head;
    }
    
    public static Node insertNode(Node head, int k, int x){
      System.out.println();
        Node temp = head;
        int cnt = 1;
        
        if(k == 1){
            Node n = new Node(x);
            n.next = head;
            return n;
        }
        
        while(temp != null){
            if(cnt == k-1){
                Node n = new Node(x);
                n.next = temp.next;
                temp.next = n;
            }
            
            cnt++;
            temp = temp.next;
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        Node head = new Node(1);
        Node curr = head;
        
        for(int i=2; i<=6; i++){
          curr.next = new Node(i);
          curr = curr.next;
        }
        
        // System.out.println(fun(0, true, n));
        printLinkedList(head);
        
        head = deleteNode(head, 1); // delete head
        System.out.println();
        printLinkedList(head);
        
        
        head = deleteNode(head, 5); // delete head
        printLinkedList(head);
        
        head = insertNode(head, 1, 10); // Insert at head
        printLinkedList(head);
        
    }
}
