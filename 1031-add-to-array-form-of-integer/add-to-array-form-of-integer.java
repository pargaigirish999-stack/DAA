class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        int p = num.length-1;
        int carry = 0;
        while(p>=0||k>0){
            int numval=0;
            if(p>=0){
                numval=num[p];
            }
            int d = k%10; // it is the last digit from the k
            int sum=numval+d+carry;
            int digit = sum%10;
            carry =sum/10;
            ans.add(digit);

             p--; //moving the pointer
            k=k/10;//removing the last digit from the k
        }
        if(carry>0){
            ans.add(carry); //adding the last remaining carry to the list
        }
        Collections.reverse(ans);// reverse the arraylist
        return ans;
    }
}