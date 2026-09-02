class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        
        int top=0;
        int right=matrix[0].length-1;
        int bottom=matrix.length-1;
        int left=0;

        while(top<=bottom && left<=right){

            // left to right traverse using top
            for(int j=left;j<=right;j++){
                ans.add(matrix[top][j]);
            }
            top++;

            // top to bottom traverse using right
            for(int i=top;i<=bottom;i++){
                ans.add(matrix[i][right]);
            }
            right--;

            // right to left traverse using bottom
            if(top<=bottom){
                for(int j=right;j>=left;j--){
                    ans.add(matrix[bottom][j]);
                }
                bottom--;
            }
            
            // bottom to top traverse using left
            if(left<=right){
                for(int i=bottom;i>=top;i--){
                    ans.add(matrix[i][left]);
                }
                left++;
            }
        }
        return ans;
    }
}