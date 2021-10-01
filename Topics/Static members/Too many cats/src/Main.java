import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.InputMismatchException;
import java.util.Scanner;

class Cat {

    static String name;
    static int age;
    static int counter = 0;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        counter++;
        if (counter > 5) {
            System.out.println("You have too many cats");
        }
        Reader reader = null;

    }

    public static int getNumberOfCats() {
        return counter;
    }
}