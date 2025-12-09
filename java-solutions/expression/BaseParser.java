package expression;

public abstract class BaseParser {
    protected CharSource source;
    protected char ch;
    public static final char END = (char) -1;


    public BaseParser(CharSource source) {
        this.source = source;
        take();
    }

    protected boolean test(char c) {
        return ch == c;
    }

    protected char take() {
        char res = ch;
        ch = source.hasNext() ? source.next() : END;
        return res;
    }

    protected boolean take(char c) {
        if (test(c)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean checkEOF() {
        return ch == END;
    }

    protected void expect(char c) {
        if (!take(c)) {
            throw source.error("Expected: " + c + " , but found: " + ch);
        }
    }

    protected void expect(String s) {
        for (char c : s.toCharArray()) {
            expect(c);
        }
    }

    protected boolean between(char start, char end) {
        return start <= ch && ch <= end;
    }
    protected RuntimeException error(String message) {
        return source.error(message);
    }
}
