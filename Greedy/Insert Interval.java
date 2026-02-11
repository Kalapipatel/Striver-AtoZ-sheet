/*
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

Note that you don't need to modify intervals in-place. You can make a new array and return it.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105
*/

class Solution {
    class pair{
        int s;
        int e;

        pair(int s, int e){
            this.s = s;
            this.e = e;
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int idx = 0;
        List<pair> list = new ArrayList<>();

        while(idx < n && intervals[idx][1] < newInterval[0]){
            list.add(new pair(intervals[idx][0], intervals[idx][1]));
            idx++;
        }

        while(idx < n && intervals[idx][0] <= newInterval[1]){
            newInterval[0] = Math.min(newInterval[0], intervals[idx][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[idx][1]);
            idx++;
        }
        list.add(new pair(newInterval[0], newInterval[1]));

        while(idx < n){
            list.add(new pair(intervals[idx][0], intervals[idx][1]));
        }

        int arr[][] = new int[list.size()][2];
        for(int i=0; i<list.size(); i++){
            pair p = list.get(i);
            arr[i][0] = p.s;
            arr[i][1] = p.e;
        }

        return arr;
    }
}


// space optimized

class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();
        int s1 = intervals[0][0], e1 = intervals[0][1];
        for(int i=1; i<n; i++) {
            int s2 = intervals[i][0];
            int e2 = intervals[i][1];

            if(e1 >= s2) {
                e1 = Math.max(e1, e2);
            } else {
                ans.add(new int[]{s1,e1});
                s1 = s2;
                e1 = e2;
            }
        }
        ans.add(new int[]{s1, e1});
        return ans.toArray(new int[ans.size()][]);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        boolean insert = false;

        for(int i=0; i<intervals.length; i++) {
            int start = intervals[i][0];
            // if current start is greater than newInterval start
            if(!insert && start >=  newInterval[0]) {
                ans.add(newInterval);
                insert = true;
            }
            // add the remaining intervals
            ans.add(intervals[i]);
        }
        // if not yet inserted
        if(!insert) ans.add(newInterval);
        return merge(ans.toArray(new int[ans.size()][]));
    }
}
