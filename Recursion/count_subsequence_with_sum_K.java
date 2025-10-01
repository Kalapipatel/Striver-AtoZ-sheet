import java.util.*;

public class practice {

    public static int fun(int idx, int arr[], int n, int k){
        if(idx == n){
            if(k == 0) return 1;
            else return 0;
        }
        int l = fun(idx+1, arr, n, k-arr[idx]);
        int r = fun(idx+1, arr, n, k);
        return  l+r;
    }
    
    public static void main(String args[]){
        int arr[] = {1,2,3};
        
        System.out.println(fun(0, arr, arr.length, 3));
    }
}
