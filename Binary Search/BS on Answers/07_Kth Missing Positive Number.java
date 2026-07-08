/*
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Return the kth positive integer that is missing from this array.

Example 1:

Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
Example 2:

Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 
Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length
*/

/*
Algorithm
Start by setting two markers: one at the beginning and one at the end of the list.
Keep checking the middle position between the two markers by taking their average.
Count how many numbers are missing up to that middle position by subtracting the expected number from the actual number found at that point.
If the number of missing values is less than the desired position, move your focus to the right side of the list by shifting the beginning marker ahead.
If not, move your focus to the left side by shifting the end marker backward.
Once you've narrowed down the search and exited the loop, return the final answer by adding the desired position to the last marker you checked (plus one).
*/

// O(log(n))
class MissingKFinder {
    // Function to return the k-th missing number
    public int missingK(int[] vec, int k) {
        int low = 0, high = vec.length - 1;

        // Binary search loop
        while (low <= high) {
            int mid = (low + high) / 2;

            // Number of missing elements before index mid
            int missing = vec[mid] - (mid + 1);

            if (missing < k) {
                low = mid + 1;  // Move right
            } else {
                high = mid - 1; // Move left
            }
        }

        // Final result after binary search
        return k + high + 1;
    }
}

// O(N)
class Solution {
    public int findKthPositive(int[] arr, int k) {
        
        int num = 1;
        int miss = 0;

        int idx = 0;
        while(k != miss){
            if(idx < arr.length && num == arr[idx]){
                idx++;
                num++;
            }
            else{
                num++;
                miss++;
            }
        }

        return num-1;
    }
}
