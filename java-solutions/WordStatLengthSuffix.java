import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import java.util.Arrays;

public class WordStatLengthSuffix {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Использование: java WordStatLengthSuffix <inputFile> <outputFile>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        StringBuilder buffer = new StringBuilder();
        char[] block = new char[1024];
        int read;

        String[] prefixes = new String[4];
        int n = 0;

        try (Reader reader = new InputStreamReader(new FileInputStream(inputFile), "UTF-8")) {
            while ((read = reader.read(block)) != -1) {
                for (int i = 0; i < read; i++) {
                    char c = block[i];
                    if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION) {
                        buffer.append(c);
                    } else {
                        processPrefix(buffer, prefixes, n);
                        if (buffer.length() > 0) n = addPrefix(buffer.toString(), prefixes, n);
                        buffer.setLength(0);
                    }
                }
            }
            // Последнее слово
            if (buffer.length() > 0) {
                if (buffer.length() > 1) {
                    n = addPrefix(buffer.toString(), prefixes, n);
                }
            }
        }

        // Сортировка по длине, при равной длине — по порядку появления
        Arrays.sort(prefixes, 0, n, (a, b) -> {
            int cmp = Integer.compare(a.length(), b.length());
            return cmp != 0 ? cmp : 0; // порядок появления сохранен
        });

        // Запись в файл
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8")) {
            for (int i = 0; i < n; i++) {
                writer.write(prefixes[i] + "\n");
            }
        }
    }

    private static int addPrefix(String word, String[] prefixes, int n) {
        if (word.length() <= 1) return n; // слова длиной 1 игнорируем
        String prefix = word.substring(0, word.length() / 2).toLowerCase();

        // Проверка на уникальность
        for (int i = 0; i < n; i++) {
            if (prefixes[i].equals(prefix)) return n;
        }

        if (n >= prefixes.length) {
            prefixes = Arrays.copyOf(prefixes, prefixes.length * 2);
        }
        prefixes[n] = prefix;
        return n + 1;
    }

    private static void processPrefix(StringBuilder buffer, String[] prefixes, int n) {
        // Пустой метод, можно удалить, оставлен для симметрии
    }
}
