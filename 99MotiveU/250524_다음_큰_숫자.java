class Solution {
    public int solution(int n) {
        int answer = 0;
        int count = Integer.bitCount(n);

        int next = n + 1;
        while (true) {
            if (Integer.bitCount(next) == count) {
                answer = next;
                break;
            }
            next++;
        }
        return answer;
    }
}
