class Solution {
    public int[] frequencySort(int[] nums) {
        int freq[]=new int[201];
        for(int num:nums){
            freq[num+100]++;
        }
        Integer[]arr=new Integer[nums.length];
        for(int i=0;i<nums.length;i++){
            arr[i]=nums[i];
        }
        Arrays.sort(arr,(a,b)->{
            if(freq[a+100]==freq[b+100]){
                return b-a;
            }
            return freq[a+100]-freq[b+100];
        });
        for(int i=0;i<nums.length;i++){
            nums[i]=arr[i];
        }
        return nums;
    }
}