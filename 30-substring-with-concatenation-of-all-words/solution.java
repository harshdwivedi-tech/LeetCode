// 12 ms | 46.7 MB
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
               List<Integer> result = new ArrayList<>();

        if (words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int totalWords = words.length;
        int windowLength = wordLength * totalWords;

        if (s.length() < windowLength) {
            return result;
        }

        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (int offset = 0; offset < wordLength; offset++) {
            int left = offset;
            int count = 0;
            Map<String, Integer> seen = new HashMap<>();

            for (int right = offset; right + wordLength <= s.length();
                 right += wordLength) {

                String word = s.substring(right, right + wordLength);

                if (!wordCount.containsKey(word)) {
                    seen.clear();
                    count = 0;
                    left = right + wordLength;
                    continue;
                }

                seen.put(word, seen.getOrDefault(word, 0) + 1);
                count++;

                while (seen.get(word) > wordCount.get(word)) {
                    String leftWord = s.substring(left, left + wordLength);

                    seen.put(leftWord, seen.get(leftWord) - 1);
                    left += wordLength;
                    count--;
                }

                if (count == totalWords) {
                    result.add(left);

                    String leftWord = s.substring(left, left + wordLength);
                    seen.put(leftWord, seen.get(leftWord) - 1);
                    left += wordLength;
                    count--;
                }
            }
        }

        return result;
    }
}