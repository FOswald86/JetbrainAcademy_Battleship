package battleship;

import java.util.Scanner;

public class Main {

    static char[][] grid = new char[10][10];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        preSetGrid();
        printGrid();
        placeShip("Aircraft Carrier", 5);
        placeShip("Battleship", 4);
        placeShip("Submarine", 3);
        placeShip("Cruiser", 3);
        placeShip("Destroyer", 2);


    }

    static void placeShip(String shipName, int shipSize) {
        System.out.printf("\nEnter the coordinate of the %s (%d cells):\n", shipName, shipSize);
        while (true) {
            String first = scanner.next();
            String second = scanner.next();

            char firstLetter = first.charAt(0);
            char secondLetter = second.charAt(0);

            int firstNumber = Integer.parseInt(first.substring(1));
            int secondNumber = Integer.parseInt(second.substring(1));

            if (firstLetter < 'A' || firstLetter > 'J' || secondLetter < 'A' || secondLetter > 'J' ||
                    firstNumber < 1 || firstNumber > 10 || secondNumber < 1 || secondNumber > 10) {
                System.out.println("Error! Coordinates should be between A - J and 1 - 10! Try again:");
            } else if ((firstLetter == secondLetter) == (firstNumber == secondNumber)) {
                System.out.println("Error! Wrong ship location! Try again:");
            } else if ((Math.abs(firstNumber - secondNumber) + 1 != shipSize) && (Math.abs(firstLetter - secondLetter) + 1 != shipSize)) {
                System.out.printf("Error! Wrong length of the %s! Try again:\n", shipName);
            } else if (!validPosition(firstLetter, secondLetter, firstNumber, secondNumber)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
            } else {
                placeShip(firstLetter, secondLetter, firstNumber, secondNumber);
                printGrid();
                break;
            }
        }
    }

    static boolean validPosition(char firstLetter, char secondLetter, int firstNumber, int secondNumber) {
        int rowStart = Math.min(firstLetter - 'A', secondLetter - 'A');
        int rowEnd = Math.max(firstLetter - 'A', secondLetter - 'A');
        int columnStart = Math.min(firstNumber - 1, secondNumber - 1);
        int columnEnd = Math.max(firstNumber - 1, secondNumber - 1);

        if ((rowStart > 0 && columnStart > 0) && (rowEnd < 9 && rowEnd < 9)) {
            rowStart -= 1;
            rowEnd += 1;
            columnStart -= 1;
            columnEnd += 1;
        } else if (rowStart == 0 && columnStart == 0) {
            rowEnd += 1;
            columnEnd += 1;
        } else if (rowEnd == 9 && columnStart == 0) {
            rowStart -= 1;
            columnEnd += 1;
        } else if (rowStart == 0 && columnEnd == 9) {
            rowEnd += 1;
            columnStart -= 1;
        } else if (rowEnd == 9 && columnEnd == 9) {
            rowStart -= 1;
            columnStart -= 1;
        }

        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = columnStart; j <= columnEnd; j++) {
                if (grid[i][j] != '~') {
                    return false;
                }
            }
        }
        return  true;
    }

    static void placeShip(char firstLetter, char secondLetter, int firstNumber, int secondNumber) {
        if (firstLetter == secondLetter) {
            int row = firstLetter - 'A';
            int colstart = Math.min(firstNumber - 1, secondNumber - 1);
            int colend = Math.max(firstNumber - 1, secondNumber - 1);
            for (int i = colstart; i <= colend; i++) {
                grid[row][i] = 'O';
            }
        } else if (firstNumber == secondNumber) {
            int rowstart = Math.min(firstLetter - 'A', secondLetter - 'A');
            int rowend = Math.max(firstLetter - 'A', secondLetter - 'A');
            int col = firstNumber - 1;
            for (int i = rowstart; i <= rowend; i++) {
                grid[i][col] = 'O';
            }
        }
    }

    static void preSetGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = '~';
            }
        }
    }

    static void printGrid() {
        String alpha = "ABCDEFGHIJ";
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(alpha.charAt(i));
            for (int j = 0; j < grid.length; j++) {
                System.out.print(" " + grid[i][j]);
            }
            System.out.println();
        }
    }
}