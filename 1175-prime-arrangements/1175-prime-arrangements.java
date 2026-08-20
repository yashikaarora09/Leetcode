class Solution {
    public int numPrimeArrangements(int n) {
        final long MOD = 1_000_000_007;

        // Count primes from 1 to n
        int primeCount = 0;

        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primeCount++;
            }
        }

        // p! * (n-p)!
        long ans = 1;

        for (int i = 1; i <= primeCount; i++) {
            ans = (ans * i) % MOD;
        }

        for (int i = 1; i <= n - primeCount; i++) {
            ans = (ans * i) % MOD;
        }

        return (int) ans;
    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}