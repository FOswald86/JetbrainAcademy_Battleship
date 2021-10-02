package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class GameField {
    private final String[][] cells = new String[12][12];
    private int sunkShips = 0;

    public int getSunkShips() {
        return sunkShips;
    }

    public GameField() {
        for (String[] cell : cells) {
            Arrays.fill(cell, "~");
        }
    }

    public void print() {
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        for (int i = 1; i <= 10; i++) {
            System.out.printf("\n%c ", 65 + i - 1);
            for (int j = 1; j <= 10; j++) {
                System.out.print(cells[i][j] + " ");
            }
        }
        System.out.println();
    }

    public void printShots() {
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        for (int i = 1; i <= 10; i++) {
            System.out.printf("\n%c ", 65 + i - 1);
            for (int j = 1; j <= 10; j++) {
                if (cells[i][j].equals("X") || cells[i][j].equals("M")) {
                    System.out.print(cells[i][j] + " ");
                } else {
                    System.out.print("~ ");
                }
            }
        }
        System.out.println();
        System.out.println("---------------------");
    }

    public void shot() {
        label:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String cell = scanner.next().toUpperCase();
            int row = cell.charAt(0) - 64;
            int col = Integer.parseInt(cell.substring(1));
            if (row >= 1 && row <= 10 && col >= 1 && col <= 10) {
                switch (cells[row][col]) {
                    case "X":
                        System.out.println("You hit a ship!");
                        break;
                    case "~":
                    case "M":
                        cells[row][col] = "M";
                        System.out.println("You missed!");
                        break;
                    case "O":
                        cells[row][col] = "X";
                        if (checkSankShip(row, col)) {
                            sunkShips++;
                            if (sunkShips == 5) {
                                System.out.println("You sank the last ship. You won. Congratulations!");
                            } else {
                                System.out.println("You sank a ship!");
                            }
                            break label;
                        }
                        System.out.println("You hit a ship!");
                        break;
                }
                break;
            } else {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        }
    }

    private boolean checkSankShip(int row, int col) {
        for (int i = 1; i < 5; i++) {
            if (cells[row + i][col].equals("O")) {
                return false;
            }
            if (cells[row + i][col].equals("M") || cells[row + i][col].equals("~")) {
                break;
            }
        }
        for (int i = 1; i < 5; i++) {
            if (cells[row - i][col].equals("O")) {
                return false;
            }
            if (cells[row - i][col].equals("M") || cells[row - i][col].equals("~")) {
                break;
            }
        }
        for (int i = 1; i < 5; i++) {
            if (cells[row][col + 1].equals("O")) {
                return false;
            }
            if (cells[row][col + 1].equals("M") || cells[row][col + 1].equals("~")) {
                break;
            }
        }
        for (int i = 1; i < 5; i++) {
            if (cells[row][col - 1].equals("O")) {
                return false;
            }
            if (cells[row][col - 1].equals("M") || cells[row][col - 1].equals("~")) {
                break;
            }
        }
        return true;
    }

    public void place() {
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        placeShip(5);
        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        placeShip(4);
        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        placeShip(3);
        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        placeShip(3);
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        placeShip(2);
    }

    private void placeShip(int shipLength) {
        int inputLength;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String beginning = scanner.next().toUpperCase();
            String end = scanner.next().toUpperCase();
            int row1 = beginning.charAt(0) - 64;
            int row2 = end.charAt(0) - 64;
            int col1 = Integer.parseInt(beginning.substring(1));
            int col2 = Integer.parseInt(end.substring(1));
            if (row1 < 1 || row1 > 10 || row2 < 1 || row2 > 10 || col1 < 1 || col1 > 10 || col2 < 1 || col2 > 10
                    || (row1 != row2 && col1 != col2)) {
                System.out.println("Error! Wrong ship location! Try again:");
            } else {
                int smallerRow = Math.min(row1, row2);
                int biggerRow = Math.max(row1, row2);
                int smallerCol = Math.min(col1, col2);
                int biggerCol = Math.max(col1, col2);
                if (smallerRow == biggerRow) {
                    inputLength = biggerCol - smallerCol + 1;
                } else {
                    inputLength = biggerRow - smallerRow + 1;
                }
                if (shipLength != inputLength) {
                    System.out.println("Error! Wrong length! Try again:");
                    continue;
                }
                if (checkClose(smallerRow, biggerRow, smallerCol, biggerCol)) {
                    for (int i = smallerRow; i <= biggerRow; i++) {
                        for (int j = smallerCol; j <= biggerCol; j++) {
                            cells[i][j] = "O";
                        }
                    }
                    print();
                    break;
                } else {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                }
            }
        }
    }

    private boolean checkClose(int smallerRow, int biggerRow, int smallerCol, int biggerCol) {
        for (int i = smallerRow - 1; i <= biggerRow + 1; i++) {
            for (int j = smallerCol - 1; j <= biggerCol + 1; j++) {
                if (!cells[i][j].equals("~")) {
                    return false;
                }
            }
        }
        return true;
    }
}