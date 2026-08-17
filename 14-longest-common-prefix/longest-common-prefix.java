class Solution {
    public String longestCommonPrefix(String[] strs) {
        String prefix=strs[0];
        for(int i=1;i<strs.length;i++){
            int k=0;
            while(k<Math.min(prefix.length(),strs[i].length())&&prefix.charAt(k)==strs[i].charAt(k)){
                k++;
            }
            prefix=prefix.substring(0,k);
        }
        return prefix;
    }
}