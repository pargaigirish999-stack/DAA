class Solution {
    public boolean checkDivisibility(int n) {
        int sum=0;
        int product=1;
        int temp=n;
        while(temp>0){
            int digit=temp%10;
            sum=sum+digit;
            product=product*digit;
            temp=temp/10;
        }
        return n%(sum+product)==0;
    }
}