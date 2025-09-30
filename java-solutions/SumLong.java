public class SumLong {
    public static void main(String[] args) {
        long totalSum = 0;

        for (String arg : args) {
            StringBuilder sBuilder = new StringBuilder();

            for (int j = 0; j < arg.length(); j++) {
                char symbol = arg.charAt(j);

                if (Character.isWhitespace(symbol)) {
                    if (sBuilder.length() > 0) {
                        String candidate = sBuilder.toString();
                        if (candidate.matches("-?\\d+")) { //регулярка для проверки чисел
                            long number = Long.parseLong(candidate);
                            totalSum += number;
                        }
                        sBuilder.setLength(0);
                    }
                } else {
                    sBuilder.append(symbol);
                }
            }

            // обработка последнего числа
            if (sBuilder.length() > 0) {
                String candidate = sBuilder.toString();
                if (candidate.matches("-?\\d+")) {
                    long number = Long.parseLong(candidate);
                    totalSum += number;
                }
            }
        }

        System.out.println(totalSum);
    }
}
