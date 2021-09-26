package battleship;

public class BattleField {

    static String[][] battleField = new String[11][11];

    protected static void preSet() {
        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField.length; j++) {
                battleField[i][j] = " ~";
            }
        }
        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField.length; j++) {
                battleField[0][j] = String.format(String.valueOf(j)) + " ";
            }
        }
        battleField[0][0] = "   ";
        battleField[1][0] = "A ";
        battleField[2][0] = "B ";
        battleField[3][0] = "C ";
        battleField[4][0] = "D ";
        battleField[5][0] = "E ";
        battleField[6][0] = "F ";
        battleField[7][0] = "G ";
        battleField[8][0] = "H ";
        battleField[9][0] = "I ";
        battleField[10][0] = "J ";

    }

    protected static void print() {
        for (int i = 0; i < battleField.length; i++) {
            for (int j = 0; j < battleField.length; j++) {
                System.out.print(battleField[i][j]);
            }
            System.out.println();
        }
    }

    protected static void place(String s) {

    }
}
