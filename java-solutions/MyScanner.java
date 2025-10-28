import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;

public class MyScanner implements AutoCloseable {
    private final Reader reader;
    private final char[] buffer;
    private int bufferPos;
    private int bufferSize;
    private static final int BUFFER_CAPACITY = 8192;
    private boolean closed;

    public MyScanner(InputStream inputStream, Charset charset) {
        this.reader = new InputStreamReader(inputStream);
        this.buffer = new char[BUFFER_CAPACITY];
        this.bufferPos = 0;
        this.bufferSize = 0;
        this.closed = false;
    }

    public MyScanner(String text, Charset charset) {
        this.reader = new StringReader(text);
        this.buffer = new char[BUFFER_CAPACITY];
        this.bufferPos = 0;
        this.bufferSize = 0;
        this.closed = false;
    }

    public MyScanner(File file) {
        try {
            this.reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            this.buffer = new char[BUFFER_CAPACITY];
            this.bufferPos = 0;
            this.bufferSize = 0;
            this.closed = false;
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

    private void skipDelimiters() {
        try {
            while (hasMoreChars()) {
                char c = peekChar();
                if (Character.isDigit(c) || c == '-') {
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

    public String next() {
        try {
            skipDelimiters();
            if (!hasMoreChars()) return null;
            
            StringBuilder sb = new StringBuilder();
            char firstChar = peekChar();
            
            if (firstChar == '-') {
                sb.append(readChar());
            }
            
            while (hasMoreChars()) {
                char c = peekChar();
                if (!Character.isDigit(c)) break;
                sb.append(readChar());
            }
            
            return sb.length() == 0 ? null : sb.toString();

        } catch (Exception e) {
            close();
            return null;
        }
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
                    if (hasMoreChars() && buffer[bufferPos] == '\n') {
                        bufferPos++;
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
                reader.close();
            } catch (IOException ignored) {

        }
    }
}
}