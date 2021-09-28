import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        long sum = 1;

        while (a < b) {
            sum *= a;
            a++;
        }

        System.out.println(sum);
    }
}