class Solution {
    private int m, n;
    private char[][] grid;
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;

        // Quick checks
        if ((m + n - 1) % 2 == 1) return false; // odd length path
        if (grid[0][0] == ')' || grid[m-1][n-1] == '(') return false;

        // Memoization: dimensions m x n x (m+n)
        memo = new Boolean[m][n][m+n];
        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int balance) {
        // Update balance
        balance += (grid[i][j] == '(' ? 1 : -1);

        // Invalid states
        if (balance < 0) return false;
        if (balance > (m + n - i - j - 1)) return false;

        // End condition
        if (i == m-1 && j == n-1) return balance == 0;

        // Memo check
        if (memo[i][j][balance] != null) return memo[i][j][balance];

        // Explore next moves
        boolean res = false;
        if (i + 1 < m) res |= dfs(i+1, j, balance);
        if (j + 1 < n) res |= dfs(i, j+1, balance);

        memo[i][j][balance] = res;
        return res;
    }
}
