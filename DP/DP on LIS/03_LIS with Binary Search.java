// LIS with Binary Search
// TC -> O(n log(n))

import java.util.*;

public class Main {
    static int lis(int arr[]) {
        int n = arr.length;
        
        List<Integer> temp = new ArrayList<>();
        temp.add(arr[0]);
        
        for(int i=1; i<n; i++){
          System.out.print(arr[i] + " ");
            if(arr[i] > temp.get(temp.size() - 1)){
                temp.add(arr[i]);
            }
            else{
                int idx = Collections.binarySearch(temp, arr[i]);
                System.out.print(idx);
                if(idx < 0) idx = -(idx+1);
                
                temp.set(idx, arr[i]);
            }
            
            System.out.println();
        }
        
        return temp.size();
        
    }
    
    public static void main(String[] args) {
      int arr[] = {1,4,5,4,2,8};
      System.out.println("ans =  " + lis(arr));
  }
}

/*
Output:

4 
5 
4 1
2 -2
8 
ans =  4
*/
