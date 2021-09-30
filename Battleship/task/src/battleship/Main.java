package battleship;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Scanner scanner1 = new Scanner(System.in);
    static int player = 1;

    public static void main(String[] args) {

        char[][] gridPlayer1 = new char[10][10];
        char[][] gridPlayer2 = new char[10][10];

        System.out.println("Player 1, place your ships on the game field");
        preSetGrid(gridPlayer1);
        printGrid(gridPlayer1);
        placeShip("Aircraft Carrier", 5, gridPlayer1);
        placeShip("Battleship", 4, gridPlayer1);
        placeShip("Submarine", 3, gridPlayer1);
        placeShip("Cruiser", 3, gridPlayer1);
        placeShip("Destroyer", 2, gridPlayer1);
        passMove();

        System.out.println("Player 2, place your ships on the game field");
        preSetGrid(gridPlayer2);
        printGrid(gridPlayer2);
        placeShip("Aircraft Carrier", 5, gridPlayer2);
        placeShip("Battleship", 4, gridPlayer2);
        placeShip("Submarine", 3, gridPlayer2);
        placeShip("Cruiser", 3, gridPlayer2);
        placeShip("Destroyer", 2, gridPlayer2);
        passMove();

        play(gridPlayer1, gridPlayer2);
    }

    static void play(char[][] gridPlayer1, char[][] gridPlayer2) {
        char[][] newGridgridPlayer1 = new char[10][10];
        char[][] newGridgridPlayer2 = new char[10][10];
        preSetGrid(newGridgridPlayer1);
        preSetGrid(newGridgridPlayer2);

        while (true) {
            String shipsLeftPlayer1 = "";
            String shipsLeftPlayer2 = "";
            for (int i = 0; i < gridPlayer1.length; i++) {
                for (int j = 0; j < gridPlayer1.length; j++) {
                    shipsLeftPlayer1 += gridPlayer1[i][j];
                    shipsLeftPlayer2 += gridPlayer2[i][j];
                }
            }
            if (!shipsLeftPlayer1.contains("O") || !shipsLeftPlayer2.contains("O")) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
            if (player == 1) {
                printGrid(newGridgridPlayer2);
                System.out.println("---------------------");
                printGrid(gridPlayer1);
                System.out.println("\nPlayer 1, it's your turn:");
            } else if (player == 2) {
                printGrid(newGridgridPlayer1);
                System.out.println("---------------------");
                printGrid(gridPlayer2);
                System.out.println("\nPlayer 2, it's your turn:");
            }
            String target = scanner.next().toUpperCase(Locale.ROOT);
            int row = (target.charAt(0) - 'A');
            int column = Integer.parseInt(target.substring(1));
            if (player == 1) {
                try {
                    if (gridPlayer2[row][column - 1] == 'O' || gridPlayer2[row][column - 1] == 'X') {
                        newGridgridPlayer2[row][column - 1] = 'X';
                        gridPlayer2[row][column - 1] = 'X';
                        System.out.println("\nYou hit a ship!");
                    } else if (gridPlayer2[row][column - 1] == '~') {
                        newGridgridPlayer2[row][column - 1] = 'M';
                        gridPlayer2[row][column - 1] = 'M';
                        System.out.println("\nYou missed!");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error! Coordinates should be between A - J and 1 - 10! Try again:");
                }
            } else if (player == 2) {
                printGrid(newGridgridPlayer1);
                System.out.println("---------------------");
                try {
                    if (gridPlayer1[row][column - 1] == 'O' || gridPlayer2[row][column - 1] == 'X') {
                        newGridgridPlayer1[row][column - 1] = 'X';
                        gridPlayer1[row][column - 1] = 'X';
                        System.out.println("\nYou hit a ship!");
                    } else if (gridPlayer1[row][column - 1] == '~') {
                        newGridgridPlayer1[row][column - 1] = 'M';
                        gridPlayer1[row][column - 1] = 'M';
                        System.out.println("\nYou missed!");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error! Coordinates should be between A - J and 1 - 10! Try again:");
                }
            }
            passMove();
        }
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
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(alpha.charAt(i));
            for (int j = 0; j < grid.length; j++) {
                System.out.print(" " + grid[i][j]);
            }
            System.out.println();
        }
    }

    static void passMove() {
        System.out.println("Press Enter and pass the move to another player");
        String passMove = scanner1.nextLine();
        if (passMove.equals("") && player == 1) {
            player++;
        } else if (passMove.equals("") && player == 2) {
            player--;
        }
    }
}