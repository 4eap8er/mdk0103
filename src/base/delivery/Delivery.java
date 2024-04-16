package base.delivery;

class Solution {
    public void fillMatrix(int[][] matrix) {
        int apartmentNumber = 1;
        for (int floor = 0; floor < matrix.length; floor++) {
            for (int room = 0; room < matrix[floor].length; room++) {
                matrix[floor][room] = apartmentNumber++;
                System.out.println((floor + 1) + " этаж | квартира №" + (apartmentNumber - 1));
            }
        }
    }
}

public class Delivery {
    public static void main(String[] args) {
        int[][] building = new int[10][5];
        Solution solution = new Solution();

        solution.fillMatrix(building);
    }
}
