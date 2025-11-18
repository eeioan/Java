public class Sum {
    public static void main(String[] args) {
        int totalSum = 0;

        for (String arg : args) {
            StringBuilder sBuilder = new StringBuilder();

            for (int j = 0; j < arg.length(); j++) {
                char symbol = arg.charAt(j);
                if (Character.isDigit(symbol) || symbol == '-') {
                    sBuilder.append(symbol);
                } else {
                    if (sBuilder.length() > 0) {
                        totalSum += Integer.parseInt(sBuilder.toString());
                        sBuilder.setLength(0);
                    }
                }
            }

            if (sBuilder.length() > 0) {
                totalSum += Integer.parseInt(sBuilder.toString());
            }
        }

        System.out.println(totalSum);
    }
}
