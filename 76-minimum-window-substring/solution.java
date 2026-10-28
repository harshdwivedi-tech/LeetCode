// 2 ms | 44.8 MB
class Solution {
    public String minWindow(String s, String t) {
                if (s.length() < t.length()) {
            return "";
        }

        int[] freq = new int[128];

        for (char ch : t.toCharArray()) {
            freq[ch]++;
        }

        int left = 0;
        int count = t.length();

        int minLength = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            if (freq[ch] > 0) {
                count--;
            }

            freq[ch]--;

            while (count == 0) {
                int windowLength = right - left + 1;

                if (windowLength < minLength) {
                    minLength = windowLength;
                    start = left;
                }

                char leftChar = s.charAt(left);
                freq[leftChar]++;

                if (freq[leftChar] > 0) {
                    count++;
                }

                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(start, start + minLength);
    }
}