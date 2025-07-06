import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> map = new HashMap<>();

            for (int j = 0; j < 10; j++) {
                map.put(discount[i + j], map.getOrDefault(discount[i + j], 0) + 1);
            }

            boolean matched = true;
            for (int k = 0; k < want.length; k++) {
                if (map.getOrDefault(want[k], 0) < number[k]) {
                    matched = false;
                    break;
                }
            }

            if (matched) answer++;
        }

        return answer;
    }
}
