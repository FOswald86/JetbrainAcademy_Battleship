import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
        if(n1%2 == 1){
            n1+=1;
        }else {
            n1+=2;
        }
        System.out.println(n1);
    }
}
