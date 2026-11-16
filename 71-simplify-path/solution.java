// 5 ms | 44.6 MB
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();

        String[] parts = path.split("/");

        for (String part : parts) {

            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            else if (part.equals("..")) {

                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }

            else {
                stack.push(part);
            }
        }

        StringBuilder result = new StringBuilder();

        for (String directory : stack) {
            result.append("/");
            result.append(directory);
        }

        if (result.length() == 0) {
            return "/";
        }

        return result.toString();
    }
}