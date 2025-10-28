import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class WordStatLengthPrefix {
    public static void main(String[] args) throws IOException {
        String inputFile = args[0];
        String outputFile = args[1];

        StringBuilder buffer = new StringBuilder();
        char[] block = new char[1024];
        int read;

        String[] prefixes = new String[4];
        int[] counts = new int[4];
        int n = 0;
        try (Reader reader = new InputStreamReader(new FileInputStream(inputFile), "UTF-8")) {
            while ((read = reader.read(block)) != -1) {
                for (int i = 0; i < read; i++) {
                    char c = block[i];
                    if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION) {
                        buffer.append(c);
                    } else {
                        if (buffer.length() > 1) {
                            String word = buffer.toString().toLowerCase();
                            String prefix = word.substring(0, word.length() / 2);

                            int idx = -1;
                            for (int j = 0; j < n; j++) {
                                if (prefixes[j].equals(prefix)) {
                                    idx = j;
                                    break;
                                }
                            }
                            if (idx != -1) {
                                counts[idx]++;
                            } else {
                                if (n >= prefixes.length) {
                                    prefixes = Arrays.copyOf(prefixes, prefixes.length * 2);
                                    counts = Arrays.copyOf(counts, counts.length * 2);
                                }
                                prefixes[n] = prefix;
                                counts[n] = 1;
                                n++;
                            }
                        }
                        buffer.setLength(0);
                    }
                }
            }
            if (buffer.length() > 1) {
                String word = buffer.toString().toLowerCase();
                String prefix = word.substring(0, word.length() / 2);

                int idx = -1;
                for (int j = 0; j < n; j++) {
                    if (prefixes[j].equals(prefix)) {
                        idx = j;
                        break;
                    }
                }
                if (idx != -1) {
                    counts[idx]++;
                } else {
                    if (n >= prefixes.length) {
                        prefixes = Arrays.copyOf(prefixes, prefixes.length * 2);
                        counts = Arrays.copyOf(counts, counts.length * 2);
                    }
                    prefixes[n] = prefix;
                    counts[n] = 1;
                    n++;
                }
            }
        }
        String[] resultPrefixes = Arrays.copyOf(prefixes, n);
        int[] resultCounts = Arrays.copyOf(counts, n);
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, Comparator.comparingInt(i -> resultPrefixes[i].length()));
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8")) {
            for (int i : order) {
                writer.write(resultPrefixes[i] + " " + resultCounts[i] + "\n");
            }
        }
    }
}
