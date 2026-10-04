class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; // minimum number of open parentheses
        int maxOpen = 0; // maximum number of open parentheses

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                if (minOpen > 0) minOpen--; // use one '(' to match
                maxOpen--; // must close one '(' if possible
            } else { // c == '*'
                if (minOpen > 0) minOpen--; // treat '*' as ')'
                maxOpen++; // treat '*' as '('
            }

            if (maxOpen < 0) return false; // too many ')'
        }

        return minOpen == 0; // all opens matched
    }
}
