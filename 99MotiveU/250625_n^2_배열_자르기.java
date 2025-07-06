class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = {};
        int size = (int)(right - left + 1);
        answer = new int[size];

        for (int i = 0; i < size; i++) {
            long idx = left + i;
            int row = (int)(idx / n);
            int col = (int)(idx % n);
            answer[i] = Math.max(row, col) + 1;
        }

        return answer;
    }
}
