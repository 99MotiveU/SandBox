class Solution {
    public int[] solution(String s) {
        int[] answer = {};       
        int count = 0, zeroCount = 0;

        while (!s.equals("1")) {
            int beforeLen = s.length();
            s = s.replace("0", "");             
            int afterLen = s.length();

            zeroCount += (beforeLen - afterLen);  
            s = Integer.toBinaryString(afterLen); 
            count++;                           
        }

        answer = new int[2];
        answer[0] = count;
        answer[1] = zeroCount;

        return answer;
    }
}
