class Solution {
    public int[] searchRange(int[] nums, int target) {
        int starting = startingposition(nums,target);
        int ending = endingposition(nums,target);
        return new int[]{starting,ending};
    }
    public int startingposition(int[]nums,int target){
        int low = 0,high=nums.length-1;
        int ans=-1;
        while(low<=high){
            int mid= low+(high-low)/2;

            if(nums[mid]==target){
                ans=mid;
                high=mid-1;
            }else if(nums[mid]<target){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return ans;
    }
    public int endingposition(int[] nums,int target){
        int low =0,high=nums.length-1;
        int ans=-1;
        
        while(low<=high){
            int mid = low+(high-low)/2;

            if(nums[mid]==target){
                ans =mid;
                low = mid+1;
            }else if(nums[mid]<target){
                low =mid+1;
            }else{
                high =mid-1;
            }
        }
        return ans;
    }
}