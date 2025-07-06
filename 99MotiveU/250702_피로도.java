class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        int n = dungeons.length;
        boolean[] visited = new boolean[n];
        int[] result = new int[1];

        java.util.StackObject[] stack = new java.util.Stack();
        stack.push(new Object[]{k, 0, visited.clone()});

        while (!stack.isEmpty()) {
            Object[] state = stack.pop();
            int fatigue = (int) state[0];
            int count = (int) state[1];
            boolean[] visitedState = (boolean[]) state[2];

            result[0] = Math.max(result[0], count);

            for (int i = 0; i  n; i++) {
                if (!visitedState[i] && fatigue = dungeons[i][0]) {
                    boolean[] nextVisited = visitedState.clone();
                    nextVisited[i] = true;
                    stack.push(new Object[]{fatigue - dungeons[i][1], count + 1, nextVisited});
                }
            }
        }

        answer = result[0];
        return answer;
    }
}
