class Solution {
    public int[][] generateMatrix(int n) {
        int matrix[][]= new int[n][n];
        int top=0;
        int left=0;
        int right=n-1;
        int bottom=n-1;

        int num=1;

        while(top<=bottom && left<=right){
            //left to right traversal using top;
            for(int j=left;j<=right;j++){
                matrix[top][j]=num++;
            }
            top++;

            // top to bottom traversal using right
            for(int i=top;i<=bottom;i++){
                matrix[i][right]=num++;
            }
            right--;

            //right to left traversal using bottom
            if(bottom>=top){
                for(int j=right;j>=left;j--){
                    matrix[bottom][j]=num++;
                }
                bottom--;
            }
            //bottom to top traverse using left
            if(right>=left){
                for(int i=bottom;i>=top;i--){
                    matrix[i][left]=num++;
                }
                left++;
            }
        }
        return matrix;
    }
}