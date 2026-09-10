class Solution {
    public int myAtoi(String s) {
        int n= s.length();
       if(s==null || n==0){
           return 0;
       }
       final int INT_MAX = Integer.MAX_VALUE;
       final int INT_MIN = Integer.MIN_VALUE;

       // Step 1: IGNORING WHITESPACE
       int i=0;
       while(i<n && s.charAt(i)==' '){
           i++;
       }
       //check if we have reached at end
       if(i==n){
           return 0;
       }
       // Step 2: check for sign
       int sign =1;
       if(s.charAt(i)=='+'){
           i++;
       }else if(s.charAt(i)=='-'){
          sign= -1;
          i++;
       }
       // Step 3: read digits and converts
       long res=0;
       while(i<n && Character.isDigit(s.charAt(i))){
        int digit = s.charAt(i)-'0';
        res = res*10+digit;
        if(sign*res<=INT_MIN){
            return INT_MIN;
        }
        if(sign*res>=INT_MAX){
            return INT_MAX;
        }
        i++;
       }
       // apply sign and return 
       return (int)(res*sign);
    }
}