// 1 ms | 42.8 MB
class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.trim().split(" ");

        return words[words.length - 1].length();
    }
}