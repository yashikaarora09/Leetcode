class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // initial score

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(0); // start a new score frame
            } else {
                int v = stack.pop(); // score inside parentheses
                int w = stack.pop(); // score before this frame
                stack.push(w + Math.max(2 * v, 1));
            }
        }
        return stack.pop();
    }
}
