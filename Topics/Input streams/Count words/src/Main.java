import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine().trim();
        if (str.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(str.split("\\s+").length);
        }
        reader.close();
    }
}