class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1000000007;

        long even = 1;
        long odd = 0;
        long sum = 0;
        long ans = 0;

        for (int num : arr) {
            sum += num;

            if (sum % 2 == 1) {
                ans += even;
                odd++;
            } else {
                ans += odd;
                even++;
            }

            ans %= mod;
        }

        return (int) ans;
    }
}