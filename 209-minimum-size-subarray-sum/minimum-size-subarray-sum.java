class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        //Sliding Window used here
        int n=nums.length;
        int window=0;
        int ans=n+1;
        int left=0;
        for(int right=0;right<n;right++){
            window+=nums[right];
            while(window>=target){
                ans=Math.min(ans,right-left+1);
                window-=nums[left++];
            }
        }
        return ans==n+1 ? 0:ans;
    }
}