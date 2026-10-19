// 0 ms | 42.4 MB
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int j = i;
            int totalLetters = 0;

            while (j < words.length &&
                   totalLetters + words[j].length() + (j - i) <= maxWidth) {
                totalLetters += words[j].length();
                j++;
            }

            int numberOfWords = j - i;
            int spaces = maxWidth - totalLetters;
            StringBuilder line = new StringBuilder();
            if (j == words.length || numberOfWords == 1) {
                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {
                        line.append(" ");
                    }
                }
                while (line.length() < maxWidth) {
                    line.append(" ");
                }
            } else {
                int gaps = numberOfWords - 1;
                int spacesPerGap = spaces / gaps;
                int extraSpaces = spaces % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {
                        int count = spacesPerGap;
                        if (extraSpaces > 0) {
                            count++;
                            extraSpaces--;
                        }
                        for (int s = 0; s < count; s++) {
                            line.append(" ");
                        }
                    }
                }
            }
            result.add(line.toString());
            i = j;
        }
        return result;
    }
}
