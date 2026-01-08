// count number of ways

class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int prev[] = new int[amount + 1];

        for(int t=0; t<=amount; t++) prev[t] = (t%coins[0] == 0) ? 1 : 0;

        for(int i=1; i<n; i++){
            int curr[] = new int[amount+1];
            for(int t=0; t<=amount; t++){
                int notpick = prev[t];
                int pick = 0;
                if(coins[i] <= t) pick = curr[t - coins[i]];

                curr[t] = pick + notpick;
            }
            prev = curr;
        }

        return prev[amount];
    }
}
