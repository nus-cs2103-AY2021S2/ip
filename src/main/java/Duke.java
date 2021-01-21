import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello from Gambit, how may I assist you today?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while(sc.hasNext()) {
            String word = sc.next();
            if(!word.equals("bye") && !word.equals("list")) {
                list.add(word);
                System.out.println("added: " + word);
            } else if (word.equals("list")) {
                int size = list.size();
                for(int i = 1; i <= size; i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else {
                System.out.println("Bye, hope to see you again!");
                break;
            }
        }
    }
}
