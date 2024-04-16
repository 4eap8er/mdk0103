package base.fillArray;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

class Solution {
    public void fillArray(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(50 + 1);
        }
    }

    public void fillArrayWithEvenNumbers(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(25) * 2;
        }
    }

    public int findAbsoluteNumber(int[] array, boolean isMax) {
        int temp = array[0];
        for (int i = 0; i < array.length; i++) {
            if (isMax) {
                if (array[i] > temp) {
                    temp = array[i];
                }
            } else {
                if (array[i] < temp) {
                    temp = array[i];
                }
            }
        }
        return temp;
    }

    public float findAverage(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Invalid Array!");
        }
        int sum = Arrays.stream(array).reduce(0, Integer::sum);
        return (float) sum / array.length;
    }
}

public class Fill {
    public static void main(String[] args) {
        int[] array = new int[10];
        int[] evenNumbersArray = new int[10];
        Solution solution = new Solution();

        solution.fillArray(array);
        solution.fillArrayWithEvenNumbers(evenNumbersArray);

        int max = solution.findAbsoluteNumber(array, true);
        int min = solution.findAbsoluteNumber(array, false);
        float average = solution.findAverage(array);
        String averageResult = new DecimalFormat("#0.00").format(average);

        System.out.printf("\nМаксимальное значение: %d \nМинимальное значение: %d \nСреднее значение: %s", max, min, averageResult);
    }
}
