class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp =new int[n+1];
        for(int i=0;i<n;i++){
            dp[i]=Integer.MIN_VALUE;
        }
        for(int i=n-1;i>=0;i--){
            int sum =0;
            dp[i]=Integer.MIN_VALUE;
            for(int k=i;k<Math.min(i+3,n);k++){
                sum+=stoneValue[k];
                dp[i]=Math.max(dp[i],sum-dp[k+1]);
            }
        }
        if(dp[0]>0)return "Alice";
        if(dp[0]<0)return "Bob";
        return "Tie";
    }
}