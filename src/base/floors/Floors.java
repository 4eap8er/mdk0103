package base.floors;

import java.util.Scanner;

class Solution {

    private int getApartmentNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер квартиры: ");
        return scanner.nextInt();
    }

    public String getApartmentPosition(int[][] building) {
        int rows = building.length;
        int columns = building[0].length;

        int apartmentNumber = this.getApartmentNumber();

        if (apartmentNumber > rows * columns || apartmentNumber <= 0) {
            return "Такой квартиры не существует в этом доме";
        }

        int floor = (apartmentNumber - 1) / columns;
        int position = (apartmentNumber - 1) % columns;

        String floorStr = (floor == 0) ? "первом этаже" : (floor + 1) + " этаже";
        String positionStr;

        boolean isLeft = position < columns / 2;

        if (position % 2 == 0) {
            positionStr = isLeft ? "слева от лифта" : "справа от лифта";
        } else {
            positionStr = isLeft ? "далеко слева" : "далеко справа";
        }

        return "Квартира №" + apartmentNumber + " находится на " + floorStr + ", " + positionStr;
    }
}

class Apartments {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] building = new int[9][4];

        String result = solution.getApartmentPosition(building);

        System.out.println(result);
    }
}
