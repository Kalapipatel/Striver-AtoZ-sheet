class Solution {
    public double helper(double x, int n){
        if(n == 0 ){
            return 1;
        }
        
        if(n%2 == 0)
            return helper(x*x, n/2);
        else
            return x * helper(x, n-1);
        
    }
    
    public double myPow(double x, int n){
        
        if(n < 0){
            if(n == Integer.MIN_VALUE){
                n = Integer.MAX_VALUE;
                return 1 / (helper(x,n) * x );
            }
            else{
                n = n*-1;
                return 1 / helper(x, n);
            }
        }
        else if(n == 0){
            return 1;
        }
        else{
            return helper(x, n);
        }
    }
}
