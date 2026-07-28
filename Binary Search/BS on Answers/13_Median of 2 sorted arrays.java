/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 
Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
*/

// O(log(n + m))
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums2.length < nums1.length) return findMedianSortedArrays(nums2, nums1);
        int n1 = nums1.length;
        int n2 = nums2.length;

        int l = 0;
        int h = n1;

        while(l <= h){
            int cut1 = (l+h)/2;
            int cut2 = (n1+n2+1)/2 - cut1;

            int left1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right1 = cut1 == n1 ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = cut2 == n2 ? Integer.MAX_VALUE : nums2[cut2];

            if(left1 <= right2 && left2 <= right1){
                if((n1+n2) % 2 == 0){
                    return ((double)Math.max(left1, left2) + Math.min(right1, right2))/2;
                }
                else return (double)Math.max(left1, left2);
            }
            else if(left1 > right2) h = cut1 - 1;
            else l = cut1 + 1;
        }

        return 0.0;
    }
}

// O(n + m)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        List<Integer> list = new ArrayList<>();

        int i=0, j=0;
        while(i<n && j<m){
            if(nums1[i] < nums2[j]){
                list.add(nums1[i]);
                i++;
            }
            else{
                list.add(nums2[j]);
                j++;
            }
        }

        while(i<n){
            list.add(nums1[i]);
            i++;
        }

        while(j<m){
            list.add(nums2[j]);
            j++;
        }

        if((m+n) % 2 != 0 ){
            return (double) list.get((m+n)/2);
        }

        int num1 = list.get((m+n)/2-1);
        int num2 = list.get(((m+n)/2));

        return (double)(num1+num2)/2.0;
    }
}
