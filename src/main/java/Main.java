import java.util.*;
import static simulator.Design.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(welcomeMsg + logo);
        printBox(greeting);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ChatBot cb = new ChatBot();

        while (!input.equals(("bye"))) {
            cb.process(input);
            input = sc.nextLine();
        }
        printBox(exitMessage);

    }
}
