import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            n = scanner.nextInt();
            System.out.println(n % 2 == 0 ? "even" : "odd");
        } while (n != 0);
    }
}