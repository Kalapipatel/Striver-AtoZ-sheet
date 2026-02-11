/*
You are given timings of n meetings in the form of (start[i], end[i]) where start[i] is the start time of meeting i and end[i] is the finish time of meeting i. Return the maximum number of meetings that can be accommodated in a single meeting room, when only one meeting can be held in the meeting room at a particular time. 

Note: The start time of one chosen meeting can't be equal to the end time of the other chosen meeting.

Examples :

Input: start[] = [1, 3, 0, 5, 8, 5], end[] =  [2, 4, 6, 7, 9, 9]
Output: 4
Explanation: Maximum four meetings can be held with given start and end timings. The meetings are - (1, 2), (3, 4), (5,7) and (8,9)
Input: start[] = [10, 12, 20], end[] = [20, 25, 30]
Output: 1
Explanation: Only one meetings can be held with given start and end timings.
Input: start[] = [1, 2], end[] = [100, 99]
Output: 1
Constraints:
1 ≤ n ≤ 105
0 ≤ start[i] < end[i] ≤ 106
*/

class Solution {
    class pair implements Comparable<pair>{
        int start;
        int end;
        int pos;
        
        pair(int s, int e, int p){
            this.start = s;
            this.end = e;
            this.pos = p;
        }
        
        @Override
        public int compareTo(pair p2){
            return this.end - p2.end;
        }
    }
    
    public int maxMeetings(int start[], int end[]) {
        int n = start.length;
        PriorityQueue<pair> pq = new PriorityQueue<>();
        
        for(int i=0; i<n; i++){
            pq.add(new pair(start[i], end[i], i));
        }
        
        int count = 1;
        int freetime = pq.remove().end;
        while(!pq.isEmpty()){
            pair curr = pq.remove();
            
            if(curr.start > freetime){
                count++;
                freetime = curr.end;
            }
        }
        
        return count;
    }
}
