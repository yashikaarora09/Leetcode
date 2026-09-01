class Solution {
    public String countAndSay(int n) {
        return Count(n, "1");
    }

    private static String Count(int n, String str) {
        if (n == 1)
            return str;

        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 0, j = 1; j < str.length(); j++) {
            if (str.charAt(i) == str.charAt(j))
                count++;
            else {
                sb.append(count);
                sb.append(str.charAt(i));
                i = j;
                count = 1;
            }
        }
        sb.append(count);
        sb.append(str.charAt(str.length() - 1));
        return Count(n - 1, sb.toString());
    }
}