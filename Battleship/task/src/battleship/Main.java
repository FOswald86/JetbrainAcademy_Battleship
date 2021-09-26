package battleship;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        char[][] grid = new char[10][10];

        preSetGrid(grid);
        printGrid(grid);

        placeShip("Aircraft Carrier", 5, grid);
        placeShip("Battleship", 4, grid);
        placeShip("Submarine", 3, grid);
        placeShip("Cruiser", 3, grid);
        placeShip("Destroyer", 2, grid);
        play(grid);
    }

    static void play(char[][] grid) {
        char[][] newGrid = new char[10][10];
        preSetGrid(newGrid);
        System.out.println("\nThe game starts!");
        printGrid(newGrid);
        while (!wincheck(grid)) {
            System.out.println("\nTake a shot!\n");
            String target = scanner.next().toUpperCase(Locale.ROOT);
            int row = (target.charAt(0) - 'A');
            int column = Integer.parseInt(target.substring(1));
            try {
                if (grid[row][column - 1] == 'O') {
                    newGrid[row][column - 1] = 'X';
                    grid[row][column - 1] = 'X';
                    printGrid(newGrid);
                    System.out.println("\nYou hit a ship!");
                } else if (grid[row][column - 1] == '~') {
                    newGrid[row][column - 1] = 'M';
                    grid[row][column - 1] = 'M';
                    printGrid(newGrid);
                    System.out.println("\nYou missed!");
                }
                printGrid(grid);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error! Coordinates should be between A - J and 1 - 10! Try again:");
            }
        }
    }

    static boolean wincheck(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 'O') {
                    return true;
                } else {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                }
            }
        }
        return false;
    }

    static void placeShip(String shipName, int shipSize, char[][] grid) {
        System.out.printf("\nEnter the coordinate of the %s (%d cells):\n", shipName, shipSize);
        while (true) {
            String first = scanner.next().toUpperCase(Locale.ROOT);
            String second = scanner.next().toUpperCase(Locale.ROOT);

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
            } else if (!validPosition(firstLetter, secondLetter, firstNumber, secondNumber, grid)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
            } else {
                placeShip(firstLetter, secondLetter, firstNumber, secondNumber, grid);
                printGrid(grid);
                break;
            }
        }
    }

    static boolean validPosition(char firstLetter, char secondLetter, int firstNumber, int secondNumber, char[][] grid) {
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
        return true;
    }

    static void placeShip(char firstLetter, char secondLetter, int firstNumber, int secondNumber, char[][] grid) {
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

    static void preSetGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = '~';
            }
        }
    }

    static void printGrid(char[][] grid) {
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