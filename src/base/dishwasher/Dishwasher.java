package base.dishwasher;

import java.util.Scanner;

class Solution {
    Scanner scanner = new Scanner(System.in);
    private static int dishes = 0;
    private double detergent;

    private int getData(String text) {
        System.out.println("Введите количество " + text + ":");
        return scanner.nextInt();
    }

    public void wash() {
        dishes = getData("тарелок");
        detergent = getData("моющего средства");

        for (int i = 0; i < dishes; i++) {
            detergent -= 0.5;
            if (detergent < 0) {
                throw new Error("Закончилось моющее средство, вымыто " + i + " тарелок");
            }
            System.out.println(detergent);
        }
    }
}

public class Dishwasher {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.wash();
    }
}
