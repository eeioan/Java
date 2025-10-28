import java.io.*;
import java.util.*;

public class Wspp {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Использование: java Wspp <входной_файл> <выходной_файл>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        Map<String, WordInfo> wordStats = new LinkedHashMap<>();
        List<String> wordOrder = new ArrayList<>();

        File file = new File(inputFile);
        MyScanner scanner = new MyScanner(file);

        try {
            int lineNumber = 1;

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                // Пропускаем пустые строки
                if (currentLine == null || currentLine.isEmpty()) {
                    lineNumber++;
                    continue;
                }

                MyScanner lineScanner = new MyScanner(currentLine);

                while (lineScanner.hasNext()) {
                    String token = lineScanner.next();
                    if (token == null || token.isEmpty()) {
                        continue;
                    }

                    String word = extractWord(token);
                    if (word.isEmpty()) {
                        continue;
                    }

                    word = word.toLowerCase();

                    if (!wordStats.containsKey(word)) {
                        wordOrder.add(word);
                        wordStats.put(word, new WordInfo());
                    }

                    WordInfo info = wordStats.get(word);
                    info.count++;
                    if (info.lastLine != lineNumber) {
                        info.lines.add(lineNumber);
                        info.lastLine = lineNumber;
                    }
                }
                lineScanner.close();
                lineNumber++;
            }
        } finally {
            scanner.close();
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"))) {
            for (String word : wordOrder) {
                WordInfo info = wordStats.get(word);
                writer.write(word + " " + info.count);
                for (int line : info.lines) {
                    writer.write(" " + line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    private static String extractWord(String token) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION) {
                word.append(c);
            }
        }
        return word.toString();
    }

    private static class WordInfo {
        int count = 0;
        List<Integer> lines = new ArrayList<>();
        int lastLine = -1;
    }
}