/*
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []
*/

class Solution {
    
    public void helper(int arr[], int i, int k, List<Integer> sublist, List<List<Integer>> mlist){

        if(i == arr.length){
            if(k == 0){
                mlist.add(new ArrayList<>(sublist));
            }
            return;
        }

        if(arr[i] <= k){
            sublist.add(arr[i]);
            helper(arr, i, k-arr[i], sublist, mlist);
            sublist.remove(sublist.size()-1);
        }

        helper(arr, i+1, k, sublist, mlist);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        helper(candidates, 0, target, new ArrayList<>(), ans);

        return ans;
    }
}
