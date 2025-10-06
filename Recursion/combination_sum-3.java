class Solution {

    public void fun(int arr[], int k, int n, int idx, List<Integer> list, List<List<Integer>> ans, int max){
        if(idx == arr.length){
            System.out.println(list);
            if(n == 0 && list.size() == k){
              ans.add(new ArrayList<>(list));
            }
            return;
        }
        
        if(arr[idx] < max){
            if(list.size() < k){
              
              list.add(arr[idx]);
              fun(arr, k, n-arr[idx], idx+1, list, ans, max);
              list.remove(list.size()-1);
            }
        }

        fun(arr, k, n, idx+1, list, ans, max);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        int arr[] = {1,2,3,4,5,6,7,8,9};
        List<List<Integer>> ans = new ArrayList<>();
        // List<Integer> list = ;
        
        fun(arr, k, n, 0, new ArrayList<>(), ans, n);

        return ans;
    }
}
