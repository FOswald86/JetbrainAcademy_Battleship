import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        StringBuilder stringBuilder = new StringBuilder(input);
        System.out.println(stringBuilder.reverse());
        reader.close();
    }
}