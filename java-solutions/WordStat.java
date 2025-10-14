import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.Arrays;

public class WordStat {
    public static void main(String[] args) throws IOException {
        String inputFile = args[0];
        String outputFile = args[1];
        StringBuilder buffer = new StringBuilder();
        char[] block = new char[1024];
        int read;
        String[] words = new String[4];
        int[] counts = new int[4];
        int n = 0;
        try (Reader reader = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8)) {
            while ((read = reader.read(block)) != -1) {
                for (int i = 0; i < read; i++) {
                    char c = block[i];
                    if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION) {
                        buffer.append(c);
                    } else {
                        if (buffer.length() > 0) {
                            String word = buffer.toString().toLowerCase();
                            buffer.setLength(0);

                            int idx = -1;
                            for (int j = 0; j < n; j++) {
                                if (words[j].equals(word)) {
                                    idx = j;
                                    break;
                                }
                            }

                            if (idx != -1) {
                                counts[idx]++;
                            } else {
                                if (n >= words.length) {
                                    words = Arrays.copyOf(words, words.length * 2);
                                    counts = Arrays.copyOf(counts, counts.length * 2);
                                }
                                words[n] = word;
                                counts[n] = 1;
                                n++;
                            }
                        }
                    }
                }
            }
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
            for (int i = 0; i < n; i++) {
                writer.write(words[i] + " " + counts[i] + "\n");
            }
        }
    }
}
}
