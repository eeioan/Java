import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Wspp {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Использование: java Wspp <входной_файл> <выходной_файл>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try {
            Map<String, WordInfo> wordStats = new LinkedHashMap<>();
            List<String> wordOrder = new ArrayList<>();

            File file = new File(inputFile);

            try (MyScanner scanner = new MyScanner(file)) {
                int lineNumber = 1;

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line == null) {
                        break;
                    }

                    int i = 0;
                    while (i < line.length()) {
                        while (i < line.length()) {
                            char c = line.charAt(i);
                            if (isWordChar(c)) {
                                break;
                            }
                            i++;
                        }

                        if (i >= line.length()) {
                            break;
                        }
                        StringBuilder word = new StringBuilder();
                        while (i < line.length()) {
                            char c = line.charAt(i);
                            if (isWordChar(c)) {
                                word.append(c);
                                i++;
                            } else {
                                break;
                            }
                        }

                        if (word.length() > 0) {
                            String wordStr = word.toString().toLowerCase();

                            if (!wordStats.containsKey(wordStr)) {
                                wordOrder.add(wordStr);
                                wordStats.put(wordStr, new WordInfo());
                            }

                            WordInfo info = wordStats.get(wordStr);
                            info.count++;
                            if (info.lastLine != lineNumber) {
                                info.lines.add(lineNumber);
                                info.lastLine = lineNumber;
                            }
                        }
                    }

                    lineNumber++;
                }
            }

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
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

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static class WordInfo {
        int count = 0;
        List<Integer> lines = new ArrayList<>();
        int lastLine = -1;
    }

    private static boolean isWordChar(char c) {
        return Character.isLetter(c) ||
                Character.getType(c) == Character.DASH_PUNCTUATION ||
                c == '\'' || c == '-' || c == '—';
    }
}