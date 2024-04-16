package base.dates;

import java.time.Year;
import java.util.Scanner;

class Solution {

    private int getYearFromUser() {
        Scanner yearScanner = new Scanner(System.in);
        System.out.print("Введите год, начиная с 15 года и по сегодняшний день:");
        return yearScanner.nextInt();
    }

    public String validateYear() {
        int year = getYearFromUser();

        int currentYear = Year.now().getValue();

        if (year > currentYear || year < 15) {
            return "Неккорекнтое значение! Год должен быть больше 15 и не больше " + currentYear ;
        }

        int century = (year - 1) / 100 + 1;

        return String.format("%d год принадлежит %d веку", year, century);
    }
}

public class Dates {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String result = solution.validateYear();

        System.out.print(result);

    }
}
