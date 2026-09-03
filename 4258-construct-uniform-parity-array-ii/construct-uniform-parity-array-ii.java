class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd=Integer.MAX_VALUE;
        //FIND SMALLEST ODD
        for(int num:nums1){
            if(num%2!=0){
                minOdd=Math.min(minOdd,num);
            }
        }

        //If no odd ->all even->valid
        if(minOdd==Integer.MAX_VALUE)
            return true;

        //Check if all even become odd
        for(int num:nums1){
            if(num%2==0 && num<=minOdd){
                return false;
            }
        }    
        return true;
    }
}