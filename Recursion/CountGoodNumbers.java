class Solution {
    public final long mod = 1000000007;

    public long helper(long base, long expo){
        long ans=1;

        while(expo > 0){
            if(expo%2 == 0){
                base = (base*base) % mod;
                expo = expo/2;
            }
            else{
                ans = (ans*base) % mod;
                expo -= 1;
            }
        }

        return ans;
    }

    public int countGoodNumbers(long n) {
        if(n==1) return 5;
        long even = (n+1)/2;
        long odd = n/2;
        return (int) ( (helper(5,even) * helper(4,odd)) % mod);
    }
}
