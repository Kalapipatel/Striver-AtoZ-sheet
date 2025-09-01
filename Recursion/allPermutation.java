// printing all possible permutation
// solution 1 with extra space
class Main{
    
    public static void fun(int arr[], boolean map[], List<Integer> list, List<List<Integer>> ans){
        
        if(list.size() == arr.length){
            ans.add(new ArrayList<>(list));
            return;
        }
        
        for(int i=0; i<arr.length; i++){
            if(map[i] == false){
                map[i] = true;
                list.add(arr[i]);
                fun(arr, map, list, ans);
                
                list.remove(list.size()-1);
                map[i] = false;
            }
        }
    }
    
    public static List<List<Integer>> allPermutation(int arr[]){
        boolean map[] = new boolean[arr.length];
        List<List<Integer>> ans = new ArrayList<>();
        
        fun(arr, map, new ArrayList<>(), ans);
        
        return ans;
    }
    
}

// solution 2 without any extra space
import java.util.*;

class Main{
    
    public static void fun(int arr[], int idx, List<List<Integer>> ans){
        
        if(idx == arr.length){
            List<Integer> list = new ArrayList<>();
            
            for(int ele : arr){ // array into list
                list.add(ele);
            }
            
            ans.add(list);
            return;
        }
        
        for(int i=idx; i<arr.length; i++){
            swap(i, idx, arr);
            fun(arr, idx+1, ans);
            swap(i, idx, arr);
        }
        
    }
    
    public static void swap(int i, int j, int arr[]){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static List<List<Integer>> allPermutation(int arr[]){
        List<List<Integer>> ans = new ArrayList<>();
        
        fun(arr, 0, ans);
        
        return ans;
    }
    
    public static void main(String args[]){
        int arr[] = {1,2,3};
        
        List<List<Integer>> ans = new ArrayList<>();
        ans = allPermutation(arr);
        
        System.out.println(ans);
    }
}
