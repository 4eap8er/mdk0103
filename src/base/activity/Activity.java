package base.activity;

import java.util.Arrays;
import java.util.Random;

class Solution {
    private static final int MIN_EXPENSE = 0;
    private static final int MAX_EXPENSE = 1000;

    public static void fillArrayAndDisplay(int[] expenses) {
        Random random = new Random();
        for (int i = 0; i < expenses.length; i++) {
            expenses[i] = random.nextInt(MAX_EXPENSE - MIN_EXPENSE + 1) + MIN_EXPENSE;
            System.out.println(expenses[i]);
        }
        int totalExpense = Arrays.stream(expenses).reduce(0, Integer::sum);
        System.out.println("Трат за неделю: " + totalExpense);
    }
}

public class Activity {
    public static void main(String[] args) {
        int[] weeklyExpenses = new int[7];
        Solution.fillArrayAndDisplay(weeklyExpenses);
    }
}
