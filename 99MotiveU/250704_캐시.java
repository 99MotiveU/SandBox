import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList<String> cache = new LinkedList<>();

        for (String city : cities) {
            String cityLower = city.toLowerCase();

            if (cache.contains(cityLower)) {
                cache.remove(cityLower);
                cache.addLast(cityLower);
                answer += 1;
            } else {

                if (cacheSize > 0 && cache.size() >= cacheSize) {
                    cache.removeFirst();
                }
                if (cacheSize > 0) {
                    cache.addLast(cityLower);
                }
                answer += 5;
            }
        }

        return answer;
    }
}
