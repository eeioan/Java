import java.util.*;

public class Reverse {
    public static void main(String[] args) {
        Scanner rowScanner = new Scanner(System.in); //сканнер строк 
        int rows = 4, cols = 4;
        String[][] matrix = new String[rows][cols];
        int i = 0;
        while (rowScanner.hasNextLine()) {
            String line = rowScanner.nextLine();
            if (i >= matrix.length) {
                int newRows = matrix.length * 2;
                String[][] newMatrix = new String[newRows][cols];
                // Копируем уже существующие данные в новую таблицу
                for (int k = 0; k < matrix.length; k++) {
                    newMatrix[k] = Arrays.copyOf(matrix[k], cols);
                }
                matrix = newMatrix;
                rows = newRows;
            }
            if (line.isEmpty()) {
                matrix[i][0] = null;
                i++;
                continue;
            }
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter("\\s+"); //без регулярки пропускает только 1 пробел :(
            int j = 0;
            while (lineScanner.hasNext()) {
                if (j >= cols) {
                    int newCols = cols * 2;
                    for (int k = 0; k < rows; k++) {
                        matrix[k] = Arrays.copyOf(matrix[k], newCols);
                    }
                    cols = newCols;
                }
                matrix[i][j] = lineScanner.next();
                j++;
            }
            lineScanner.close();
            i++;
        }
        rowScanner.close();
        for (int r = i-1; r >= 0; r--) {
            for (int j = cols-1; j >= 0; j--) {
                if (matrix[r][j] != null) {
                    System.out.print(matrix[r][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
    
