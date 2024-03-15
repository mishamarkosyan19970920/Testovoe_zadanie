import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа: либо 2 арабских, либо 2 римских): ");
        String expression = scanner.nextLine();  //читаем с консоли введенное выражение
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int number1;
        int number2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]"); // мат. операции, а слэши перед минусом делают его математическим знаком
        if (operands.length != 2) throw new Exception("Должно быть два операнда"); // введи только 2 числа(операнда)
        oper = detectOperation(expression); // читаем математическую операцию
        if (oper == null) throw new Exception("Некорректная математическая операция"); //если ввел другой мат. знак выдаем исключение
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {  //если оба числа римские

            number1 = Roman.convertToArabian(operands[0]); number2 = Roman.convertToArabian(operands[1]); //конвертируем оба числа в арабские для вычесления
            isRoman = true;
        }
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) { //если оба числа арабские
            number1 = Integer.parseInt(operands[0]);
            number2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else {
            throw new Exception("Числа должны быть в одном формате"); //если одно число римское, а другое - арабское, то исключение
        }
        if (number1 > 10 || number2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10"); // если введенные числа больше 10, то опять исключение
        }
        int arabian = calculator(number1, number2, oper);
        if (isRoman) {

            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля"); //если римское число получилось меньше или равно нулю, генерируем ошибку
            }

            result = Roman.convertToRoman(arabian); //конвертируем результат операции из арабского в римское
        } else {

            result = String.valueOf(arabian);  //Конвертируем арабское число в тип String
        }
        return result; //возвращает результат
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+"; // если знак плюс то возвращаем мат. операцию с знаком +, ниже строки аналогичны
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calculator(int a, int b, String oper) {

        if (oper.equals("+")) return a + b; // возвращаем операцию сложение // анологично нижные строки
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};
    // создали массив римских чисел до 100 так как при математических операции в нашем калькуляторе максимальное число будет равнятся 100 (10*10)


    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) { //конвертируем
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                /* создали цикл, который считывает наш массив при этом за счет
                 конвертации первое значение под индексом 0 = 0,
                 затем значение под индексом 1 равно I и так далее, если введем в консоли X ,
                 то наша прога поймет, что это 10 и анологично с другими римсими числами*/
                return i; //возвращаем число с соответствующим индексом
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}


