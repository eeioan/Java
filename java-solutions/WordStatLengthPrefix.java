import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatLengthPrefix {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java WordStatLengthPrefix <inputFile> <outputFile>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        Map<String, Integer> prefixCounts = new LinkedHashMap<>();
        StringBuilder buffer = new StringBuilder();
        char[] block = new char[1024];

        try (Reader reader = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8)) {
            int read;
            while ((read = reader.read(block)) != -1) {
                for (int i = 0; i < read; i++) {
                    char c = block[i];
                    if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION) {
                        buffer.append(c);
                    } else if (buffer.length() > 1) {
                        String word = buffer.toString().toLowerCase();
                        String prefix = word.substring(0, word.length() / 2);
                        if (!prefixCounts.containsKey(prefix)) prefixCounts.put(prefix, 1);
                        else prefixCounts.put(prefix, prefixCounts.get(prefix) + 1);
                        buffer.setLength(0);
                    } else {
                        buffer.setLength(0);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(prefixCounts.entrySet());
        entries.sort(Comparator.comparingInt(a -> a.getKey().length()));

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
            for (Map.Entry<String, Integer> entry : entries) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
