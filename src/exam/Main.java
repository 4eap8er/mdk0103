package exam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class FinancialCalculator {
    private Scanner inputScanner = new Scanner(System.in);
    private Map<Integer , BigDecimal> expensesMap = new HashMap<>();
    private Map<String , BigDecimal> currencyMap = new HashMap<>();

    private int choice = 5;

    public void start() {
        while (choice !=0) {
            switch (choice) {
                case 1:
                    enterExpenses();
                    break;
                case 2:
                    showAllExpenses();
                    break;
                case 3:
                    showMaxExpenses();
                    break;
                case 4:
                    addCurrency();
                    convertExpenses();
                    break;
                case 5:
                    System.out.println("Меню:");
                    System.out.println("1 – Ввести расходы за определенный день");
                    System.out.println("2 – Траты за месяц");
                    System.out.println("3 – Самая большая сумма расхода за месяц");
                    System.out.println("4 - Конвертация всех трат за месяц в др. валюту");
                    System.out.println("0 – Выход");
                    choice = inputScanner.nextInt();
                    break;
                case 0:
                    System.out.println("До свидания!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Выберите пункт из текущего списка функций");
                    choice = 5;
            }

        }

    }


    private void enterExpenses() {
        System.out.println("Введите расходы за определенный день:");
        String dayStr = getDataFromUser("Введите день (от 1 до 30)" , true);
        int day = Integer.parseInt(dayStr);

        while (day < 1 || day > 30) {
            System.out.println("День должен быть в диапозоне от 1 до 30! \nПовторите попытку ввода!");
            day = inputScanner.nextInt();
        }
        if (expensesMap.containsKey(day)) {
            String response = getDataFromUser(
                    "Вы хотите перезаписать сумму или оставить предыдущую?" +
                            "\n Введите Y если хотите оставить " +
                            "\n Введите N если хотите перезаписать" ,
                    true);

            switch (response) {
                case "Y":
                    returnToMenu();
                    break;
                case "N":
                    BigDecimal money = getDataFromUser("Введите сумму пополнения: " , false);
                    expensesMap.replace(day , money);
                    returnToMenu();
                    break;
                default:
                    System.out.println("Неккоректные данные!");
                    returnToMenu();
                    return;
            }
        }
        BigDecimal expenseToPush = getDataFromUser("Введите сумму трат за этот день: " , false);
        expensesMap.put(day , expenseToPush);

        returnToMenu();
    }

    private void showAllExpenses () {
        for (Map.Entry<Integer , BigDecimal> exp : expensesMap.entrySet()) {
            System.out.println(exp.getKey() + " день - " + exp.getValue() + " руб");
        }

        returnToMenu();
    }

    private void showMaxExpenses () {
        BigDecimal maxValue = BigDecimal.ZERO;
        int day = 0;
        for (Map.Entry<Integer , BigDecimal> exp : expensesMap.entrySet()) {
            if (exp.getValue().compareTo(maxValue) > 0) {
                maxValue = exp.getValue();
                day = exp.getKey();
            }
        }
        System.out.println("Самая большая сумма расхода за месяц: ");
        System.out.println(day + " день - " + maxValue + " руб");

        returnToMenu();
    }

    private void convertExpenses() {
        Set<String> currencies = currencyMap.keySet();
        System.out.println("Доступные валюты для конвертации: ");
        currencies.forEach(System.out::println);

        String currency = getDataFromUser("Введите валюту, в которую хотите перевести все траты за месяц", true);
        BigDecimal multiplier = currencyMap.get(currency);
        BigDecimal multiplierRU = currencyMap.get("RUB");

        for (Map.Entry<Integer, BigDecimal> exp : expensesMap.entrySet()) {
            BigDecimal convertedSum = (exp.getValue().multiply(multiplierRU)).divide(multiplier, 2 , RoundingMode.HALF_UP );
            System.out.println(exp.getKey() + " день - " + convertedSum + " " + currency);
        }

        returnToMenu();
    }

    private void addCurrency() {
        currencyMap.put("USD", BigDecimal.valueOf(1.0));
        currencyMap.put("EUR", BigDecimal.valueOf(1.08));
        currencyMap.put("CNY", BigDecimal.valueOf(1.0 / 7.0));
        currencyMap.put("RUB", BigDecimal.valueOf(1.0 / 91.01));
    }

    private void returnToMenu() {
        String res = "";
        while (!res.equals("Y")) {
            res = getDataFromUser("Введите Y, чтобы выйти в главное меню: ", true);
            if (!res.equals("Y")) {
                System.out.println("Некорректное значение!");
            }
        };
        choice = 5;
    }

    private <T> T getDataFromUser(String text , boolean isString) {
        System.out.println(text);
        return isString ? (T) inputScanner.next() : (T) inputScanner.nextBigDecimal();
    }
}

public class Main {
    public static void main(String[] args) {
        FinancialCalculator sol = new FinancialCalculator();
        sol.start();
    }
}