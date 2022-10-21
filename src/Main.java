import java.util.List;
import java.util.Scanner;
public class Main {

        public static void main(String[] args) {
            System.out.println("Калькулятор");
            System.out.println("Введите 2 числа и оператор:");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            System.out.println(Main.calc(input));
        }
        public static String calc(String input) {
            try {
                String[] raqamlar = input.split(" ");
                if (raqamlar.length > 3) {
                    throw new Exception("Можно ввести только два числа и оператор");
                }
                if (raqamlar.length <= 2) {
                    throw new Exception("Можно ввести только два числа и оператор");
                }
                if ((input.contains("I") || input.contains("V") || input.contains("X")) &&
                        (input.contains("0") || input.contains("1") || input.contains("2") || input.contains("3") || input.contains("4") || input.contains("5") ||
                                input.contains("6") || input.contains("7") || input.contains("8") || input.contains("9"))) {
                    throw new Exception("Нельзя одновременно использовать римские и арабские цифры");
                }
                boolean romanSwitch = false;
                if (isRoman(raqamlar[0], raqamlar[2])) {
                    raqamlar[0] = String.valueOf(getRomanToArab(raqamlar[0]));
                    raqamlar[2] = String.valueOf(getRomanToArab(raqamlar[2]));
                    romanSwitch = true;
                }
                if (input.contains(".") || input.contains(",")) {
                    throw new Exception("Вводите только целые числа");
                }
                int raqamlar1 = Integer.parseInt(raqamlar[0]);
                String operator = raqamlar[1];
                int raqamlar2 = Integer.parseInt(raqamlar[2]);
                if (raqamlar1 > 10 || raqamlar2 > 10) {
                    throw new Exception("Калькулятор работает только с числами не больше 10");
                }
                if (operator.equals("-") && isRoman(raqamlar[0], raqamlar[2])) {
                    throw new Exception("В римской системе нет отрицательных чисел");
                }
                int result;
                switch (operator) {
                    case ("+") -> result = raqamlar1 + raqamlar2;
                    case ("-") -> result = raqamlar1 - raqamlar2;
                    case ("*") -> result = raqamlar1 * raqamlar2;
                    case ("/") -> result = raqamlar1 / raqamlar2;
                    default -> throw new Exception("Вы не ввели оператор");
                }
                if (romanSwitch) {
                    if (result < 0) {
                        throw new Exception("В римской системе нет отрицательных чисел");
                    }
                    return "Output:\n" + getArabToRoman(String.valueOf(result));
                }
                return "Output:\n" + result;
            }
            catch (Exception ex) {
                return ex.getMessage();
            }
        }
        public static boolean isRoman(String raqamlar1, String raqamlar2) {
            return raqamlar1.contains("I") || raqamlar2.contains("I") || raqamlar1.contains("V") || raqamlar2.contains("V") ||
                    raqamlar1.contains("X") || raqamlar2.contains("X");
        }
        public static int getRomanToArab(String input) {
            String romanNumeral = input.toUpperCase();
            int result = 0;
            List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
            int i = 0;
            while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
                RomanNumeral symbol = romanNumerals.get(i);
                if (romanNumeral.startsWith(symbol.name())) {
                    result += symbol.getValue();
                    romanNumeral = romanNumeral.substring(symbol.name().length());
                } else {
                    i++;
                }
            }
            return result;
        }
        public static String getArabToRoman(String number) {
            int arabNumber = Integer.parseInt(number);
            List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
            int i = 0;
            StringBuilder sb = new StringBuilder();
            while ((arabNumber > 0) && (i < romanNumerals.size())) {
                RomanNumeral currentSymbol = romanNumerals.get(i);
                if (currentSymbol.getValue() <= arabNumber) {
                    sb.append(currentSymbol.name());
                    arabNumber -= currentSymbol.getValue();
                } else {
                    i++;
                }
            }
            return sb.toString();
        }
    }