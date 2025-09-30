public class SumLongOctal {
    public static void main(String[] args) {
        long totalSum = 0;

        for (String arg : args) {
            StringBuilder sBuilder = new StringBuilder();
            boolean negative = false;
            boolean isOctal = false;

            for (int j = 0; j < arg.length(); j++) {
                char symbol = arg.charAt(j);

                if (Character.isWhitespace(symbol)) {
                    if (sBuilder.length() > 0) {
                        long number = Long.parseUnsignedLong(sBuilder.toString(), isOctal ? 8 : 10); // опрашивает isOctal
                        if (negative) {                                                              // if false, то 10 остается
                            number = -number;                                                       // true меняет на 8
                        }
                        totalSum += number;
                        sBuilder.setLength(0);
                        negative = false;
                        isOctal = false;
                    }
                } else if (symbol == '-' && sBuilder.length() == 0) {
                    negative = true;
                } else if ('0' <= symbol && symbol <= '9') {
                    sBuilder.append(symbol);
                } else if (symbol == 'o' || symbol == 'O') {
                    isOctal = true;
                } else {
                    sBuilder.setLength(0);
                    negative = false;
                    isOctal = false;
                }
            }
            // обработка последнего числа встроке, можно сделать через отдельный метод и вызывать его в двух местах 
            if (sBuilder.length() > 0) {
                long number = Long.parseUnsignedLong(sBuilder.toString(), isOctal ? 8 : 10);
                if (negative) {
                    number = -number;
                }
                totalSum += number;
            }
        }
        System.out.println(totalSum);
    }
}