import java.util.Scanner;

class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] na = new int[3];
    int n = scanner.nextInt();
    na[0] = n / 100;
    na[1] = (n % 100) / 10;
    na[2] = n % 10;
    System.out.println(na[0]+na[1]+na[2]);
    }
}
