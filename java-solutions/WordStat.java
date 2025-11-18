import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStat {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Использование: java WordStat <входной_файл> <выходной_файл>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try {
            Map<String, Integer> wordCount = new LinkedHashMap<>();
            List<String> wordOrder = new ArrayList<>();

            File file = new File(inputFile);
            
            try (MyScanner scanner = new MyScanner(file)) {
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
                            
                            if (!wordCount.containsKey(wordStr)) {
                                wordOrder.add(wordStr);
                                wordCount.put(wordStr, 1);
                            } else {
                                wordCount.put(wordStr, wordCount.get(wordStr) + 1);
                            }
                        }
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
                for (String word : wordOrder) {
                    writer.write(word + " " + wordCount.get(word));
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Ошибка записи в файл: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static boolean isWordChar(char c) {
        return Character.isLetter(c) || 
               Character.getType(c) == Character.DASH_PUNCTUATION ||
               c == '\'' || c == '-' || c == '—';
    }
}