// now houses is in a circular arrangenment

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // Since houses are circular, we cannot rob both the first and last house.
        // We calculate two scenarios:
        // 1. Rob from index 0 to n-2 (exclude the last house)
        // 2. Rob from index 1 to n-1 (exclude the first house)
        return Math.max(robLinear(nums, 0, n - 2), robLinear(nums, 1, n - 1));
    }

    // Helper method to solve the linear House Robber problem
    private int robLinear(int[] nums, int start, int end) {
        if (start > end) return 0;

        int prev2 = 0; // Represents dp[i-2]
        int prev1 = 0; // Represents dp[i-1]

        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}
