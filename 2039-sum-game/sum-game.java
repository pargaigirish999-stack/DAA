class Solution {
    public boolean sumGame(String num) {
        int mid=num.length()/2;
        int leftSum=0;
        int rightSum=0;
        int leftQ=0;
        int rightQ=0;
        for(int i=0;i<mid;i++){
            if(num.charAt(i)=='?'){
                leftQ++;
            }else{
                leftSum+=num.charAt(i)-'0';
            }
        }
        for(int i=mid;i<num.length();i++){
            if(num.charAt(i)=='?'){
                rightQ++;
            }else{
                rightSum+=num.charAt(i)-'0';
            }
        }
        int diff=leftSum-rightSum;
        int qdiff=leftQ-rightQ;
        if(qdiff%2!=0){
            return true;
        }

        return diff!= -9 *(qdiff/2);
    }
}