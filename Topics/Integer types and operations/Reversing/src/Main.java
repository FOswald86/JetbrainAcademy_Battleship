import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int n1 = n % 10;
        int n10 = n % 100 / 10;
        int n100 = n / 100;
        int nr = n1 * 100 + n10 * 10 + n100;
        System.out.println(nr);
    }
}
