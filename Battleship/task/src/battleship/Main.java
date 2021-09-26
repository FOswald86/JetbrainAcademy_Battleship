package battleship;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BattleField.preSet();
        BattleField.print();

        System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):\n");
        BattleField.place(scanner.nextLine());
    }
}
