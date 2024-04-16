package base.game;

import java.util.Random;
import java.util.Scanner;

class Solution {
    public void guessNumber() {
        Scanner inputScanner = new Scanner(System.in);
        Random randomGenerator = new Random();

        int secretNumber = randomGenerator.nextInt(9) + 1;

        int remainingAttempts = 3;

        System.out.println("Компьютер загадал число от 1 до 9. У вас есть всего 3 попытки.");

        while (remainingAttempts > 0) {
            System.out.print("Введите ваше число: ");
            int guessedNumber = inputScanner.nextInt();

            if (guessedNumber == secretNumber) {
                System.out.println("Вы угадали число!");
                break;
            } else if (guessedNumber < secretNumber) {
                System.out.println("Загаданное число больше!");
            } else {
                System.out.println("Загаданное число меньше!");
            }

            remainingAttempts--;
        }

        if (remainingAttempts == 0) {
            System.out.println("Попытки закончились! Загаданное число было: " + secretNumber);
        }

        inputScanner.close();
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.guessNumber();
    }
}
