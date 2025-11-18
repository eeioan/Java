import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MyScanner implements AutoCloseable {
    private final Reader reader;
    private final char[] buffer;
    private int bufferPos;
    private int bufferSize;
    private static final int BUFFER_CAPACITY = 8192;
    private boolean closed;
    private boolean isSystemIn;

    public MyScanner(InputStream inputStream, Charset charset) {
        this.reader = new InputStreamReader(inputStream, charset);
        this.buffer = new char[BUFFER_CAPACITY];
        this.bufferPos = 0;
        this.bufferSize = 0;
        this.closed = false;
        this.isSystemIn = (inputStream == System.in);
    }

    public MyScanner(InputStream inputStream) {
        this(inputStream, StandardCharsets.UTF_8);
    }

    public MyScanner(String text, Charset charset) {
        this.reader = new StringReader(text);
        this.buffer = new char[BUFFER_CAPACITY];
        this.bufferPos = 0;
        this.bufferSize = 0;
        this.closed = false;
        this.isSystemIn = false;
    }

    public MyScanner(String text) {
        this(text, StandardCharsets.UTF_8);
    }

    public MyScanner(File file) {
        try {
            this.reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            this.buffer = new char[BUFFER_CAPACITY];
            this.bufferPos = 0;
            this.bufferSize = 0;
            this.closed = false;
            this.isSystemIn = false;
        } catch (IOException e) {
            throw new RuntimeException("Failed to create scanner for file: " + file.getName(), e);
        }
    }

    private boolean fillBuffer() {
        try {
            if (closed) return false;
            bufferSize = reader.read(buffer);
            bufferPos = 0;
            return bufferSize > 0;
        } catch (IOException e) {
            close();
            return false;
        }
    }

    private boolean hasMoreChars() {
        try {
            return bufferPos < bufferSize || fillBuffer();
        } catch (Exception e) {
            close();
            return false;
        }
    }

    private char peekChar() {
        try {
            if (bufferPos >= bufferSize && !fillBuffer()) {
                return (char) -1;
            }
            return buffer[bufferPos];
        } catch (Exception e) {
            close();
            return (char) -1;
        }
    }

    private char readChar() {
        try {
            if (bufferPos >= bufferSize && !fillBuffer()) {
                return (char) -1;
            }
            return buffer[bufferPos++];
        } catch (Exception e) {
            close();
            return (char) -1;
        }
    }

    private boolean isDelimiter(char c) {

        if (Character.isWhitespace(c)) {
            return true;
        }

        int type = Character.getType(c);
        if (type == Character.START_PUNCTUATION || type == Character.END_PUNCTUATION) {
            return true;
        }
        return false;
    }

    private void skipDelimiters() {
        try {
            while (hasMoreChars()) {
                char c = peekChar();
                if (!isDelimiter(c)) {
                    break;
                }
                readChar();
            }
        } catch (Exception e) {
            close();
        }
    }

    public boolean hasNext() {
        try {
            skipDelimiters();
            return hasMoreChars();
        } catch (Exception e) {
            close();
            return false;
        }
    }

    public boolean hasNextInt() {
        try {
            skipDelimiters();
            if (!hasMoreChars()) {
                return false;
            }

            char first = peekChar();
            if (first == '-' || first == '+') {
                int savedPos = bufferPos;
                readChar(); 
                
                if (!hasMoreChars()) {
                    bufferPos = savedPos;
                    return false;
                }
                
                char next = peekChar();
                bufferPos = savedPos;
                return Character.isDigit(next);
            }
            
            return Character.isDigit(first);
        } catch (Exception e) {
            close();
            return false;
        }
    }

    public String next() {
        try {
            skipDelimiters();
            if (!hasMoreChars()) return null;

            StringBuilder sb = new StringBuilder();
            char firstChar = peekChar();

            if (firstChar == '-' || firstChar == '+') {
                sb.append(readChar());
            }

            while (hasMoreChars()) {
                char c = peekChar();
                if (!Character.isDigit(c)) break;
                sb.append(readChar());
            }

            return sb.isEmpty() ? null : sb.toString();

        } catch (Exception e) {
            close();
            return null;
        }
    }

    public String nextInt() {
        return next();
    }

    public boolean hasNextLine() {
        try {
            return hasMoreChars();
        } catch (Exception e) {
            close();
            return false;
        }
    }

    public String nextLine() {
        try {
            if (!hasMoreChars()) return "";

            StringBuilder sb = new StringBuilder();
            while (hasMoreChars()) {
                char c = readChar();
                if (c == '\n') {
                    break;
                }
                if (c == '\r') {
                    if (hasMoreChars() && peekChar() == '\n') {
                        readChar();
                    }
                    break;
                }
                sb.append(c);
            }
            return sb.toString();
        } catch (Exception e) {
            close();
            return null;
        }
    }

    @Override
    public void close() {
        if (!closed) {
            closed = true;
            try {
                if (!isSystemIn) {
                    reader.close();
                }
            } catch (IOException ignored) {
            }
        }
    }
}