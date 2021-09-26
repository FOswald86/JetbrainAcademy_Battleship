import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        check(n);

    }

    @FieldDescription
    private static void check(int n){
        System.out.println(n > 0 && n < 10);
    }
}