import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int h = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int d = 0;
        while ((a-b) * d < h - b) {
            d++;
        }
        System.out.println(d);
    }
}