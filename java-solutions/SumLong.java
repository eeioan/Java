public class SumLong {
    public static void main(String[] args) {
        long totalSum = 0;

        for (String arg : args) {
            StringBuilder sBuilder = new StringBuilder();

            for (int j = 0; j < arg.length(); j++) {
                char symbol = arg.charAt(j);

                if (Character.isWhitespace(symbol)) {
                    if (sBuilder.length() > 0) {
                        try {
                            long number = Long.parseLong(sBuilder.toString());
                            totalSum += number;
                        } catch (NumberFormatException e) {
                            // если не число — просто игнорируем
                        }
                        sBuilder.setLength(0);
                    }
                } else {
                    sBuilder.append(symbol); // просто добавляем всё, что не пробел
                }
            }

            // добавляем последнее число, если строка закончилась на число
            if (sBuilder.length() > 0) {
                try {
                    long number = Long.parseLong(sBuilder.toString());
                    totalSum += number;
                } catch (NumberFormatException e) {
                    // игнорируем
                }
            }
        }

        System.out.println(totalSum);
    }
}
