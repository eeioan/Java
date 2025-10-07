import java.util.Arrays;
import java.util.Scanner;

public class ReverseSum {
    public static void main(String[] args) {
        Scanner rowScanner = new Scanner(System.in);

        int[][] matrix = new int[4][4]; // ну или [1][1]
        int[] rowSums = new int[4];
        int[] colSums = new int[4];
        int[] actualCols = new int[4];
        int i = 0; 
        while (rowScanner.hasNextLine()) {
            String line = rowScanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            
            if (i >= matrix.length - 1) {
                int newRows = matrix.length * 2;
                matrix = Arrays.copyOf(matrix, newRows);
                rowSums = Arrays.copyOf(rowSums, newRows);
                actualCols = Arrays.copyOf(actualCols, newRows);
            }

            if (!lineScanner.hasNextInt()) {
                actualCols[i] = 0;
                i++;
                lineScanner.close();
                continue;
            }

            int j = 0;
            while (lineScanner.hasNextInt()) {
                int num = lineScanner.nextInt();
                if (j >= matrix[i].length - 1) {
                    int newCols = matrix[i].length * 2;
                    for (int k = 0; k < matrix.length; k++) {
                        matrix[k] = Arrays.copyOf(matrix[k], newCols);
                    }
                    colSums = Arrays.copyOf(colSums, newCols);
                }
                matrix[i][j] = num;
                rowSums[i] += num;
                colSums[j] += num;
                j++;
            }
            actualCols[i] = j; // работаем только с заполнеными ячейками
            i++;
            lineScanner.close();
        }
        rowScanner.close();
        for (int r = 0; r < i; r++) {
            if (actualCols[r] == 0) {
                System.out.println();
                continue;
            }
            for (int c = 0; c < actualCols[r]; c++) {
                if (c > 0) System.out.print(" ");
                System.out.print(rowSums[r] + colSums[c] - matrix[r][c]);
            }
            System.out.println();
        }
    }
}
