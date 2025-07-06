import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = -1;
        int count = 0;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            String rotated = s.substring(i) + s.substring(0, i);
            Stack<Character> stack = new Stack<>();
            boolean valid = true;

            for (int j = 0; j < len; j++) {
                char c = rotated.charAt(j);

                if (c == '(' || c == '{' || c == '[') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        valid = false;
                        break;
                    }
                    char top = stack.pop();
                    if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                        valid = false;
                        break;
                    }
                }
            }

            if (valid && stack.isEmpty()) {
                count++;
            }
        }

        answer = count;
        return answer;
    }
}
