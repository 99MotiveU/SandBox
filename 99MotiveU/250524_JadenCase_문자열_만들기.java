class Solution {
    public String solution(String s) {
        String answer = "";
        boolean isStart = true;

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                answer += c;
                isStart = true;
            } else {
                if (isStart) {
                    answer += Character.toUpperCase(c);
                    isStart = false;
                } else {
                    answer += Character.toLowerCase(c);
                }
            }
        }
        return answer;
    }
}
