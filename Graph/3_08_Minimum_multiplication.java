/*
Given start, end and an array arr of n numbers. At each step, start is multiplied with any number in the array and then mod operation with 100000 is done to get the new start.

Your task is to find the minimum steps in which end can be achieved starting from start. If it is not possible to reach end, then return -1.

Input:
arr[] = {2, 5, 7}
start = 3, end = 30
Output:
2
Explanation:
Step 1: 3*2 = 6 % 100000 = 6 
Step 2: 6*5 = 30 % 100000 = 30

Input:
arr[] = {3, 4, 65}
start = 7, end = 66175
Output:
4
Explanation:
Step 1: 7*3 = 21 % 100000 = 21 
Step 2: 21*3 = 63 % 100000 = 63 
Step 3: 63*65 = 4095 % 100000 = 4095 
Step 4: 4095*65 = 266175 % 100000 = 66175
*/

class Solution {
    class pair{
        int steps;
        int node;
        
        pair(int s, int n){
            this.steps = s;
            this.node = n;
        }
    }
    
    int minimumMultiplications(int[] arr, int start, int end) {
        
        if(start == end) return 0;

        Queue<pair> q = new LinkedList<>();
        q.add(new pair(0, start));
        
        int mod = 100000;
        int ans[] = new int[mod];
        Arrays.fill(ans, Integer.MAX_VALUE);
        ans[start] = 0;
        
        while(!q.isEmpty()){
            pair curr = q.remove();
            
            for(int k=0; k<arr.length; k++){
                int num = (curr.node * arr[k]) % mod;
                
                if(curr.steps + 1 < ans[num]){
                    ans[num] = curr.steps + 1;
                    
                    if(num == end) return curr.steps + 1;
                    q.add(new pair(curr.steps+1, num));
                }
            }
        }
        
        return -1;
    }
}
