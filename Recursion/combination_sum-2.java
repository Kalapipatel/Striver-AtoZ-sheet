/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]
*/

class Solution {

    public void fun(int arr[], int k, int idx, List<Integer> sublist, List<List<Integer>> mainList){
        
        if(k == 0){
            mainList.add(new ArrayList<>(sublist));
            return;
        }
        
        for(int i=idx; i<arr.length; i++){
            if(i > idx && arr[i] == arr[i-1]) continue;
            
            if(arr[i] > k) break;
            
            sublist.add(arr[i]);
            fun(arr, k-arr[i], i+1, sublist, mainList);
            sublist.remove(sublist.size()-1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] arr, int target) {
        Arrays.sort(arr);
        
        List<List<Integer>> ans = new ArrayList<>();
        fun(arr, target, 0, new ArrayList<>(), ans);

        return ans;
    }
}
