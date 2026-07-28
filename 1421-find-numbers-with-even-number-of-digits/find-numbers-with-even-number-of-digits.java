class Solution {
    public int findNumbers(int[] nums) {
        int count =0;
        for(int i:nums){
            int digits = CountDigit(i);
            if (digits % 2 == 0){
                count+=1;
            }
        }
        return count;
    }
    public int CountDigit(int i){
        int count =0;
        while (i>0){
            i =i/10;
            count+=1;
        }
        return count;
    }
}