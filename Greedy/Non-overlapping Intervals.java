/*
Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.

 

Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 

Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104
*/

class Solution {
    class pair implements Comparable<pair>{
        int start;
        int end;
        
        pair(int s, int e){
            this.start = s;
            this.end = e;
        }
        
        @Override
        public int compareTo(pair p2){
            return this.end - p2.end;
        }
    }
    
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        PriorityQueue<pair> pq = new PriorityQueue<>();

        for(int[] arr: intervals){
            pq.add(new pair(arr[0], arr[1]));
        }

        int freetime = pq.remove().end;
        int count = 1;

        while(!pq.isEmpty()){
            pair curr = pq.remove();
            
            if(curr.start >= freetime){
                count++;
                freetime = curr.end;
            }
        }

        return n - count;
    }
}
