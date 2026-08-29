class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n=nums.length;
        Integer[] idx = new Integer[n]; //storing the original index
        for(int i=0;i<n;i++){
            idx[i]=i;
        }
        //sort the indices a/t array value
        Arrays.sort(idx,(a,b)-> Integer.compare(nums[a],nums[b]));
        int[] ans =new int[n];
        int i=0;
        while(i<n){
            int j=i+1;
            //find one connected group
            while(j<n&& nums[idx[j]]-nums[idx[j-1]]<=limit){
                j++;
            }
            //get indices of group
            Integer[] group=Arrays.copyOfRange(idx,i,j);
            //sort indices
            Arrays.sort(group);
            //put smallest values at smallest indices
            for(int k=i;k<j;k++){
                ans[group[k-i]]=nums[idx[k]];
            }
            i=j;
        }
        return ans;
    }
}