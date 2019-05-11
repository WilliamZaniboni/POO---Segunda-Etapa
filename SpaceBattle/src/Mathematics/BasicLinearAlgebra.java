package Mathematics;

public class BasicLinearAlgebra {

    public BasicLinearAlgebra() {

    }

    public int[][] sumMatrix(int[][] matrix1, int[][] matrix2){
        if((matrix1.length == matrix2.length) && (matrix1[0].length == matrix2[0].length)){
            int[][] result_matrix = new int[matrix1.length][matrix1[0].length];
            for(int i = 0; i < matrix1.length; i++){
                for(int j = 0; j < matrix1[0].length; j++){
                    result_matrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
            return result_matrix;
        }
        else{
            System.out.println("Error - cannot sum matrix with different index");
            return null;
        }
    }
}
