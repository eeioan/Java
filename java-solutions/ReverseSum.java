import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ReverseSum {
    public static void main(String[] args) {
        try {
            MyScanner rowScanner = new MyScanner(System.in, StandardCharsets.UTF_8);
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

            while (rowScanner.hasNextLine()) {
                String line = rowScanner.nextLine();

                ArrayList<Integer> row = new ArrayList<>();

                if (!line.isEmpty()) {
                    MyScanner lineScanner = new MyScanner(line, StandardCharsets.UTF_8);

                    while (lineScanner.hasNext()) {
                        String token = lineScanner.next();
                        try {
                            int num = Integer.parseInt(token);
                            row.add(num);
                        } catch (NumberFormatException ignored) {
                        }
                    }
                    lineScanner.close();
                }

                matrix.add(row);
            }
            rowScanner.close();

            int numRows = matrix.size();
            int numCols = matrix.stream().mapToInt(ArrayList::size).max().orElse(0);

            int[] rowSums = new int[numRows];
            int[] colSums = new int[numCols];

            for (int r = 0; r < numRows; r++) {
                ArrayList<Integer> row = matrix.get(r);
                for (int c = 0; c < row.size(); c++) {
                    int val = row.get(c);
                    rowSums[r] += val;
                    if (c < colSums.length) {
                        colSums[c] += val;
                    }
                }
            }

            for (int r = 0; r < numRows; r++) {
                ArrayList<Integer> row = matrix.get(r);
                for (int c = 0; c < row.size(); c++) {
                    if (c > 0) System.out.print(" ");
                    int sum = rowSums[r] + colSums[c] - row.get(c);
                    System.out.print(sum);
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}