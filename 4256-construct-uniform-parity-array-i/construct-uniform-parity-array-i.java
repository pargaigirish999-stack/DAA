class Solution {
    public boolean uniformArray(int[] nums1) {
        int n=nums1.length;
        int [] nums2=new int[n];
        nums2[0]=nums1[0];
        for(int i=1;i<n;i++){
            if(nums1[1]==nums1[0]%2){
                nums2[i]=nums1[i];
            }else{
                nums2[i]=nums1[i]-nums1[0];
            }
        }
        return true;
    }
}