public class Sum { 
    public static void main(String[] args) {
        int totalSum = 0;
        
        for (String arg : args ) {
            StringBuilder sBuilder = new StringBuilder(); //string builder собирает символы в строчку через
            boolean negative = false; 
            for (int j = 0; j < arg.length(); j++) {
                char symbol = arg.charAt(j);
                if (Character.isWhitespace(symbol)) {
                    if (sBuilder.length() > 0 ) {
                        int number = Integer.parseInt(sBuilder.toString()); // 
                        if (negative) number = -number;
                        totalSum += number;
                        sBuilder.setLength(0);
                        negative = false; 
                    }
                } else if (symbol == '-' && sBuilder.length() == 0) {
                    negative = true;
                } else if (  '0' <= symbol && symbol <= '9') { // java не умеет в двойное сравнение (((
                    sBuilder.append(symbol);
                } else {
                    sBuilder.setLength(0); //отсекаем лищние символы
                    negative = false ;
                }
            }
            if (sBuilder.length() > 0 ) {
                int number = Integer.parseInt(sBuilder.toString());
                if (negative) number = -number;
                    totalSum += number;
                    sBuilder.setLength(0);
                    negative = false; 
                }
        }
        System.out.println(totalSum);
    }
}