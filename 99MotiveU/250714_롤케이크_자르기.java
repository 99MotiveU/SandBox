import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = -1;

        HashMap<Integer, Integer> right = new HashMap<>();
        for (int t : topping) {
            right.put(t, right.getOrDefault(t, 0) + 1);
        }

        HashSet<Integer> left = new HashSet<>();
        int count = 0;

        for (int i = 0; i < topping.length - 1; i++) {
            int t = topping[i];
            left.add(t);

            right.put(t, right.get(t) - 1);
            if (right.get(t) == 0) {
                right.remove(t);
            }

            if (left.size() == right.size()) {
                count++;
            }
        }

        answer = count;
        return answer;
    }
}
