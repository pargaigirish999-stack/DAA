class Solution {
    public void sortColors(int[] nums) {
        int n=nums.length;
        int i=0;   //denotes for 0
        int j=0;   //denotes for 1
        int k=n-1;  //denotes for 2
        while(j<=k){
            if(nums[j]==1){
                j++;
            }else if(nums[j]==2){
                swap(nums,j,k);
                k--;
            }else{
                swap(nums,i,j);
                i++;
                j++;
            }
        }
    }
    public void swap(int[] nums,int i,int j){
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
    }
}