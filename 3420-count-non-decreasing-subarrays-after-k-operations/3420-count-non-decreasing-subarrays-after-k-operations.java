class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;

        Deque<long[]> dq = new ArrayDeque<>();

        for (int i = nums.length - 1, j = nums.length - 1; i >= 0; --i) {
            int num = nums[i];
            int count = 1;

            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                long[] last = dq.pollLast();

                int nextNum = (int) last[0];
                int nextCount = (int) last[1];

                count += nextCount;
                cost += (long) (num - nextNum) * nextCount;
            }

            dq.offerLast(new long[]{num, count});

            while (cost > k) {
                long[] first = dq.pollFirst();

                int rightmostNum = (int) first[0];
                int rightmostCount = (int) first[1];

                cost -= (long) (rightmostNum - nums[j--]);

                if (rightmostCount > 1) {
                    dq.offerFirst(new long[]{rightmostNum, rightmostCount - 1});
                }
            }

            ans += j - i + 1;
        }

        return ans;
    }
}