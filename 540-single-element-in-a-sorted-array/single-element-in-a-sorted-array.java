class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n=nums.length;
        int left=0;
        int right=n-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==nums[mid+1]){
                mid=mid-1;
            }
            int leftLength=(mid-left)+1;
            if(leftLength%2==1){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return nums[left];
    }
}