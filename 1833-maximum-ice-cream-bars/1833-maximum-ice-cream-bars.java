class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int maxCost = 0;

        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        int[] count = new int[maxCost + 1];

        for (int cost : costs) {
            count[cost]++;
        }

        int bars = 0;

        for (int cost = 1; cost <= maxCost; cost++) {
            if (count[cost] == 0) continue;

            int totalCost = cost * count[cost];

            if (coins >= totalCost) {
                bars += count[cost];
                coins -= totalCost;
            } else {
                bars += coins / cost;
                break;
            }
        }

        return bars;
    }
}