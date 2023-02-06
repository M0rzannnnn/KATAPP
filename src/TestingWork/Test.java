package TestingWork;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws CalcException {
        System.out.print("Введите арифметическую операцию: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().replaceAll("\\s+", "");
        String finalResult = calc(input);
        System.out.print("Результат арифметической операции: " + finalResult);

    }

    public static String calc(String input) throws CalcException {
        String symbol = input.replaceAll("\\w", "");

        String[] stringMassive = input.split("[+*/-]");
        if (stringMassive.length < 2) {
            throw new CalcException("строка не является математической операцией");
        }

        int result;
        boolean rom = false;
        int number1;
        int number2;

        if (RomNumber.getByName(stringMassive[0]) != null && RomNumber.getByName(stringMassive[1]) != null) {
            number1 = RomNumber.valueOf(stringMassive[0].toUpperCase()).getArabianNumber();
            number2 = RomNumber.valueOf(stringMassive[1].toUpperCase()).getArabianNumber();
            rom = true;
        } else if (stringMassive[0].matches("\\d+") && stringMassive[1].matches("\\d+")) {
            number1 = Integer.parseInt(stringMassive[0]);
            number2 = Integer.parseInt(stringMassive[1]);
        } else {
            throw new CalcException("используются одновременно разные системы исчисления");
        }
        if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10){
            throw new CalcException("Введено неверное число");
        }

        switch (symbol) {
            case ("*"):
                result = number1 * number2;
                break;
            case ("+"):
                result = number1 + number2;
                break;
            case ("-"):
                result = number1 - number2;
                break;
            case ("/"):
                result = number1 / number2;
                break;
            default:
                throw new CalcException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if (rom) {
            for (RomNumber y : RomNumber.values()) {
                if (result == y.getArabianNumber()) {
                    return String.valueOf(y);
                } else if (result < 0) {
                    throw new CalcException("в римской системе нет отрицательных чисел");
                }
            }
        }

        return String.valueOf(result);
    }
}
