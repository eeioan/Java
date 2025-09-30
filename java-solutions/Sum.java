public class Sum {
    public static void main(String[] args) {
        int totalSum = 0;

        for (String arg : args) {
            StringBuilder sBuilder = new StringBuilder();

            for (int j = 0; j < arg.length(); j++) {
                char symbol = arg.charAt(j);
                // Просто собираем все символы, которые могут быть частью числа
                if (Character.isDigit(symbol) || symbol == '-') {
                    sBuilder.append(symbol);
                } else {
                    // Любой другой символ = конец числа
                    if (sBuilder.length() > 0) {
                        totalSum += Integer.parseInt(sBuilder.toString());
                        sBuilder.setLength(0);
                    }
                }
            }

            // Добавляем последний элемент, если строка закончилась на число
            if (sBuilder.length() > 0) {
                totalSum += Integer.parseInt(sBuilder.toString());
            }
        }

        System.out.println(totalSum);
    }
}
