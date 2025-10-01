class Solution {
    public void fun(int arr[], int idx, List<Integer> list, int n, List<List<Integer>> ans){
        if(idx == n){
            ans.add(new ArrayList<>(list));
            return ;
        }

        list.add(arr[idx]);
        fun(arr, idx+1, list, n, ans);
        list.remove(list.size()-1);

        fun(arr, idx+1, list, n, ans);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        fun(nums, 0, new ArrayList<>(), nums.length, ans);
        return ans;
    }
}
