/*
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. 
If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23

Constraints:

1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
*/

class Solution {
    public long fun(int piles[], int k){
        long sum = 0;

        for(int i=0; i<piles.length; i++){
            sum += (int)Math.ceil((double)piles[i]/k);
        }

        return sum;
    }
    public int minEatingSpeed(int[] piles, int hr) {
        int n = piles.length;
        
        int maxi = Integer.MIN_VALUE;
        for(int x : piles) maxi = Math.max(maxi, x);

        int l = 1, h = maxi, ans = maxi;
        if(hr == n) return ans;

        while(l <= h){
            int mid = l + (h-l)/2;
            long sum = fun(piles, mid);

            // System.out.println(mid + " " + sum);
            if(sum <= (long)hr){
                h = mid-1;
                ans = mid;
            }
            else l = mid+1;
        }

        return ans; // also low will give the answer
    }
}
