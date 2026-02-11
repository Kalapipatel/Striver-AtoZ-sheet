/*
You are given two arrays: deadline[], and profit[], which represent a set of jobs, where each job is associated with a deadline, and a profit. Each job takes 1 unit of time to complete, and only one job can be scheduled at a time. You will earn the profit associated with a job only if it is completed by its deadline.

Your task is to find:

The maximum number of jobs that can be completed within their deadlines.
The total maximum profit earned by completing those jobs.
Examples :

Input: deadline[] = [4, 1, 1, 1], profit[] = [20, 10, 40, 30]
Output: [2, 60]
Explanation: Job1 and Job3 can be done with maximum profit of 60 (20+40).
Input: deadline[] = [2, 1, 2, 1, 1], profit[] = [100, 19, 27, 25, 15]
Output: [2, 127]
Explanation: Job1 and Job3 can be done with maximum profit of 127 (100+27).
Input: deadline[] = [3, 1, 2, 2], profit[] = [50, 10, 20, 30]
Output: [3, 100]
Explanation: Job1, Job3 and Job4 can be completed with a maximum profit of 100 (50 + 20 + 30).
*/

// my logic

class Solution {
    class pair implements Comparable<pair>{
        int deadline;
        int profit;
        
        pair(int d, int p){
            this.deadline = d;
            this.profit = p;
        }
        
        @Override
        public int compareTo(pair p2){
            if(this.profit != p2.profit){
                return p2.profit - this.profit;
            }
            
            return p2.deadline - this.deadline;
        }
    }
    
    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int n = profit.length;
        
        PriorityQueue<pair> pq = new PriorityQueue<>();
        int maxdeadline = 0;
        
        for(int i=0; i<n; i++){
            pq.add(new pair(deadline[i], profit[i]));
            maxdeadline = Math.max(maxdeadline, deadline[i]);
        }
        
        
        boolean vis[] = new boolean[maxdeadline+1];
        int count = 0, totalprofit = 0;
        
        while(count <= maxdeadline && !pq.isEmpty()){
            pair curr = pq.remove();
            int p = curr.profit;
            int d = curr.deadline;
            
            for(int i=d; i>0; i--){
                if(vis[i] == false){
                    vis[i] = true;
                    count++;
                    totalprofit += p;
                    break;
                }
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(count);
        ans.add(totalprofit);
        
        return ans;
    }
}


// optmized one by using Disjoin set
import java.util.*;

class Solution {
    class pair implements Comparable<pair> {
        int deadline;
        int profit;
        
        pair(int d, int p) {
            this.deadline = d;
            this.profit = p;
        }
        
        @Override
        public int compareTo(pair p2) {
            // Sort by profit descending
            return p2.profit - this.profit;
        }
    }

    // DSU find function with path compression
    private int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent, parent[i]);
    }

    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int n = profit.length;
        int maxDeadline = 0;
        pair[] jobs = new pair[n];

        for (int i = 0; i < n; i++) {
            jobs[i] = new pair(deadline[i], profit[i]);
            maxDeadline = Math.max(maxDeadline, deadline[i]);
        }

        // 1. Sort jobs by profit descending
        Arrays.sort(jobs);

        // 2. Initialize DSU parent array
        // Each slot points to itself initially
        int[] parent = new int[maxDeadline + 1];
        for (int i = 0; i <= maxDeadline; i++) {
            parent[i] = i;
        }

        int count = 0, totalProfit = 0;

        for (int i = 0; i < n; i++) {
            // Find the latest available slot for this job's deadline
            int availableSlot = find(parent, jobs[i].deadline);

            // If availableSlot > 0, we found a valid slot (0 is the dummy limit)
            if (availableSlot > 0) {
                // Occupy the slot and point it to the next available (slot - 1)
                parent[availableSlot] = find(parent, availableSlot - 1);
                
                count++;
                totalProfit += jobs[i].profit;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(count);
        ans.add(totalProfit);
        return ans;
    }
} 

/*
To understand why this is faster, think of the parent[] array as a shortcut map. 
Instead of manually checking every single day to see if it's free, the DSU "points" you directly to the 
next available day.The SetupSuppose we have these jobs (already sorted by profit):
JobDeadlineProfit
J13 100
J2 250
J3 340
J4 230
Initial DSU State:parent array: [0, 1, 2, 3] (Each index represents a time slot and points to itself).
The Dry Run1. Process J1 (Deadline: 3, Profit: 100)Find slot: We call find(3). Since parent[3] == 3, slot 3 
is available.Action: Occupy slot 3.Update Map: We link slot 3 to the next available slot before it: 
parent[3]= find(2).State: parent = [0, 1, 2, 2]Stats: Count: 1, Profit: 1002. 
Process J2 (Deadline: 2, Profit: 50)Find slot: We call find(2). Since parent[2] == 2, slot 2 is available.
Action: Occupy slot 2.Update Map: parent[2] = find(1).State: parent = [0, 1, 1, 2]Stats: Count: 2, Profit: 1503. 
Process J3 (Deadline: 3, Profit: 40)Find slot: We call find(3).parent[3] is 2.parent[2] is 1.parent[1] is 1.
The Shortcut (Path Compression): The find function returns 1. Because of path compression, parent[3] now 
points directly to 1 for next time.Action: Occupy slot 1.Update Map: parent[1] = find(0).
State: parent = [0, 0, 1, 1]Stats: Count: 3, Profit: 1904. Process J4 (Deadline: 2, Profit: 30)Find slot: 
We call find(2).parent[2] is 1.parent[1] is 0.Result: It returns 0. This tells us no slots are available before or at time 2.Action: Skip Job.
Why this beats the $O(N \cdot D)$ loop:In your original code, if you had a job with a deadline of 
$10,000$ and slots $1 \dots 10,000$ were full, your for loop would run 10,000 times just to realize it 
can't fit the job.With DSU:The find(10000) call would see parent[10000] points to 0.It tells you "No" in 
one jump.
*/
