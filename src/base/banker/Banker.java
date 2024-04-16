package base.banker;

import java.util.Scanner;

class Solution {
    private Scanner inputScanner;

    private int getUserInput(String prompt) {
        int result;
        System.out.println("Введите " + prompt + ":");
        result = inputScanner.nextInt();
        return result;
    }

    private static double calculateTime(int initialCapital, int targetAmount, double annualInterestRate) {
        double years = 0;
        while (initialCapital < targetAmount) {
            initialCapital += (int) (initialCapital * annualInterestRate);
            years++;
        }
        return years;
    }

    public void countYears() {
        inputScanner = new Scanner(System.in);

        int initialCapital = getUserInput("сумму вклада");
        int targetAmount = getUserInput("желаемую сумму");
        double annualInterestRate = getUserInput("процент годовых") / 100.0;
        double years = calculateTime(initialCapital, targetAmount, annualInterestRate);

        System.out.println("Чтобы достичь желаемой суммы, потребуется: " + years + " лет");

    }
}

public class Banker {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countYears();
    }
}
