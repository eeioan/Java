public class SumLongOctal {
    public static void main(String[] args) {
        long totalSum = 0;

        for (String arg : args) {
            int i = 0; // начало поиска числа
            StringBuilder sBuilder = new StringBuilder();
            while (i < arg.length()) {
                if (Character.isWhitespace(arg.charAt(i))) {
                    i++;
                    continue; //if (i >= arg.length()) break // 
                }
                sBuilder.setLength(0);

                while (i < arg.length() && !Character.isWhitespace(arg.charAt(i))) {
                    sBuilder.append(arg.charAt(i));
                    i++;
                }
                int radix = 10;
                if (i < arg.length() && (arg.charAt(i - 1) == 'o' || arg.charAt(i - 1) == 'O')) {
                    radix = 8;
                    sBuilder.delete(sBuilder.length() - 1, sBuilder.length());
                    i++;
                }
                if (!sBuilder.isEmpty() && sBuilder.charAt(0) == '-') {
                    totalSum += Long.parseLong(sBuilder.toString(), radix);
                } else {
                    totalSum += Long.parseUnsignedLong(sBuilder.toString(), radix); //если переполнен не - Long
                }
            }
        }
        System.out.println(totalSum);
    }
}
