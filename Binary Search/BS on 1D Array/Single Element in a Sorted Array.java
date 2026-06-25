/*
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 105
*/

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        int l = 0, h = n-1, ans = -1;

        while(l <= h){
            int mid = l + (h-l)/2;

            if(mid == 0 && nums[mid] != nums[mid+1]) return nums[mid];
            else if(mid == n-1 && nums[mid] != nums[mid-1]) return nums[mid];
            else if(nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]){
                return nums[mid];
            }

            if(mid % 2 != 0){
                if(nums[mid] == nums[mid-1]){
                    l = mid+1;
                }
                else if(nums[mid] == nums[mid+1]){
                    h = mid-1;
                }
            }
            else{
                if(nums[mid] == nums[mid-1]){
                    h = mid-1;
                }
                else if(nums[mid] == nums[mid+1]){
                    l = mid+1;
                }
            }
        }

        return ans;
    }
}


/*
(1,1) (2,2) 4 (5,5) (6,6)
(even, odd) - sinle ele - (odd, even)
*/
