// 23 ms | 47.6 MB
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;

        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);

        for (int i = 1; i < n; i++) {
            char current = s.charAt(i);

            minChar = (char) Math.min(minChar, current);
            maxChar = (char) Math.max(maxChar, current);

            if (current < s.charAt(i - 1)) {
                sorted = false;
            }
        }

        if (sorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        if (s.charAt(0) == minChar ||
            s.charAt(n - 1) == maxChar) {
            return 1;
        }

        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == minChar ||
                s.charAt(i) == maxChar) {
                return 2;
            }
        }

        return 3;
    }
}