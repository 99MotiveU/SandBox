import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++)
            queue.offer(new int[]{i, priorities[i]});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (queue.stream().anyMatch(q -> q[1] > cur[1])) {
                queue.offer(cur);
            } else {
                answer++;
                if (cur[0] == location) return answer;
            }
        }

        return answer;
    }
}
