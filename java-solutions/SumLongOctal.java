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
                if (arg.charAt(i) == '-' || arg.charAt(i) == '+' ) { //+ и - можно записывать к числу 
                    sBuilder.append(arg.charAt(i));
                    i++;
                    if (i >= arg.length() || !Character.isDigit(arg.charAt(i))) {  
                        continue;
                    }
                }
                while (i < arg.length() && Character.isDigit(arg.charAt(i))) {
                    sBuilder.append(arg.charAt(i));
                    i++;
                }
                int radix = 10;
                if (i < arg.length() && (arg.charAt(i) == 'o' || arg.charAt(i) == 'O')) {
                    radix = 8;  
                    i++;             
                }
                if (sBuilder.length() > 0 && sBuilder.charAt(0) == '-' ) {
                    totalSum += Long.parseLong(sBuilder.toString(), radix);
                }else{
                    totalSum += Long.parseUnsignedLong(sBuilder.toString(), radix); //если переполнен не - Long
                }

            }
        }
        System.out.println(totalSum);
    }
}
