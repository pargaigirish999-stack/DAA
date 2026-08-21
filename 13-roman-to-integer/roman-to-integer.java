class Solution {
    public int romanToInt(String s) {
        int ans=0;
        int values[]={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String romanvalues[]={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        for(int i=0;i<romanvalues.length;i++){
            while(s.startsWith(romanvalues[i])){
                ans=ans+values[i];
                s = s.substring(romanvalues[i].length());
            }
        }
        return ans;
    }
}