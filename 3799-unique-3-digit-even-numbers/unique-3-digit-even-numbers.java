// class Solution {
//     public int totalNumbers(int[] digits) {
//         int count=0;
//         int n=digits.length;
//         int i,j,k;
//         for(i=1;i<=9;i++){
//             for(j=0;j<=9;j++){
//                 for(k=0;k<=9;k++){
//                     while(i!=0 && i!=j && i!=k && j!=k){
//                         if((i*100+j*10+k*1)%2==0){
//                             count++;
//                         }
//                     }
//                 }
//             }
//         }
//     return count;
//     }
// }
// class Solution {
//     public int totalNumbers(int[] digits) {
//         int count = 0;

//         for (int i = 0; i < digits.length; i++) {
//             for (int j = 0; j < digits.length; j++) {
//                 for (int k = 0; k < digits.length; k++) {

//                     if (digits[i] == 0) continue;

//                     if (i == j || i == k || j == k) continue;

//                     if (digits[k] % 2 != 0) continue;

//                     count++;
//                 }
//             }
//         }

//         return count;
//     }
// }
class Solution {
    public int totalNumbers(int[] digits) {
        int count = 0;
        boolean[] seen = new boolean[1000];

        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                for (int k = 0; k < digits.length; k++) {

                    if (i == j || i == k || j == k)
                        continue;

                    if (digits[i] == 0)
                        continue;

                    if (digits[k] % 2 != 0)
                        continue;

                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];

                    if (!seen[num]) {
                        seen[num] = true;
                        count++;
                    }
                }
            }
        }

        return count;
    }
}