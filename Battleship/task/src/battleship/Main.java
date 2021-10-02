/*
tried a lot of the solutions of the Projects but most of then don`t work very well
So in the end I reworked my Project with implementing parts of others solutions too
to get atleast one proper working solution...
 */

package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GameField gameField1 = new GameField();
        GameField gameField2 = new GameField();

        System.out.println("Player 1, place your ships on the game field");
        gameField1.print();
        gameField1.place();
        passMove();

        System.out.println("Player 2, place your ships on the game field");
        gameField2.print();
        gameField2.place();
        passMove();

        while (true) {
            gameField2.printShots();
            gameField1.print();
            System.out.println("Player 1, it's your turn:");
            gameField2.shot();
            if (gameField2.getSunkShips() == 5) {
                break;
            }
            passMove();

            gameField1.printShots();
            gameField2.print();
            System.out.println("Player 2, it's your turn:");
            gameField1.shot();
            if (gameField1.getSunkShips() == 5) {
                break;
            }
            passMove();
        }
    }

    static void passMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}