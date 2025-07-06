import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        HashMap<String, Integer> map = new HashMap<>();

        for (String[] cloth : clothes) {
            String type = cloth[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        for (int count : map.values()) {
            if (answer == 0) {
                answer = count + 1;
            } else {
                answer *= (count + 1);
            }
        }

        return answer - 1;
    }
}
