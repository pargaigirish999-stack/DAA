class Solution {
    public int countCommas(int n) {
        // int ans=0;
        if(n<1000){
            return 0;
        }
        // if(n>=1000000){
        //     ans+=n-999999;
        // }
        // if(n>=1000000000){
        //     ans+=n-999999999;
        // }
        return n-999;
    }
}