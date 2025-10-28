import java.util.*;

public class Reverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<String>> data = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Scanner row = new Scanner(line);


            List<String> rowList = new ArrayList<>();
            while (row.hasNextInt()) {
                rowList.add(String.valueOf(row.nextInt()));
            }
            data.add(rowList);
        }

        for (int i = data.size() - 1; i >= 0; i--) {
            List<String> row = data.get(i);
            for (int j = row.size() - 1; j >= 0; j--) {
                System.out.print(row.get(j) + " ");
            }
            System.out.println();
        }
    }
}
