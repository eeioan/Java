import java.util.*;

public class ReverseSumOld {
    public static void main(String[] args) {
        Scanner rowScanner = new Scanner(System.in);
        int rows = 4, cols = 4;
        int[][] matrix = new int[rows][cols];  // Changed to int[][]
        int[] rowSums = new int[rows];         // Array for row sums
        int[] colSums = new int[cols];
        int[][] matrixSum = new int[rows][cols];        // Array for column sums
        int i = 0;
        
        while (rowScanner.hasNextLine()) {
            String line = rowScanner.nextLine();
            if (i >= matrix.length) {
                int newRows = matrix.length * 2;
                int[][] newMatrix = new int[newRows][cols];
                int[][] newMatrixSum = new int[newRows][cols];
                int[][] MatrixSum = new int[newRows][cols];
                int[] newRowSums = new int[newRows];
                for (int k = 0; k < matrix.length; k++) {
                    newMatrix[k] = Arrays.copyOf(matrix[k], cols);
                    newRowSums[k] = rowSums[k];
                    newMatrixSum[k] = Arrays.copyOf(matrixSum[k], cols);
                }
                matrix = newMatrix;
                rowSums = newRowSums;
                rows = newRows;
                matrixSum = newMatrixSum;
            }
            
            if (line.isEmpty()) {
                continue;
            }
            
            Scanner lineScanner = new Scanner(line);
            int j = 0;
            while (lineScanner.hasNextInt()) {
                if (j >= cols) {
                    int newCols = cols * 2;
                    for (int k = 0; k < rows; k++) {
                        matrix[k] = Arrays.copyOf(matrix[k], newCols);
                        matrixSum[k] = Arrays.copyOf(matrixSum[k], newCols);
                    }
                    int[] newColSums = new int[newCols];
                    System.arraycopy(colSums, 0, newColSums, 0, cols);
                    colSums = newColSums;
                    cols = newCols;
                }
                int num = lineScanner.nextInt();
                matrix[i][j] = num;
                rowSums[i] += num;    // Add to row sum
                colSums[j] += num;    // Add to column sum
                j++;
            }
            lineScanner.close();
            i++;
        }
        rowScanner.close();
        // Print matrix with row sums
      for (int r = 0; r < i; r++) {
            for (int j = 0; j < cols; j++) {
                matrixSum[r][j] = rowSums[r] + colSums[j];
                System.out.print(matrixSum[r][j] + " ");
            }
            System.out.println();
        }
    }
}