class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int ones = 0;

        String ans = "";

        for (int right = 0; right < n; right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // If we have more than k ones, move left
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // We have exactly k ones
            if (ones == k) {

                // Remove leading zeros
                while (left < right && s.charAt(left) == '0') {
                    left++;
                }

                String current = s.substring(left, right + 1);

                // Update answer if:
                // 1. No answer yet
                // 2. Current substring is shorter
                // 3. Same length but lexicographically smaller
                if (ans.equals("")
                        || current.length() < ans.length()
                        || (current.length() == ans.length()
                            && current.compareTo(ans) < 0)) {
                    ans = current;
                }
            }
        }

        return ans;
    }
}