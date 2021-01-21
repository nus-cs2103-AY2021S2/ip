import java.lang.reflect.Array;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dash = "____________________________________________________________";
        String greeting1 = "Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.";
        String greeting2 = "Pai Kia Bot: What you want?";

        String farewell = "Pai Kia Bot: Leave so soon ah? Limpeh sleep first, if got no issue don't disturb me.";


        System.out.println(dash);
        System.out.println(greeting1);
        System.out.println(greeting2);
        System.out.println(dash);
        System.out.print("Your response: ");
        String input = sc.next();


        System.out.println("");

        while (!input.equals("bye")) {
            System.out.println(dash);
            System.out.println("You: " + input);
            System.out.println("Pai Kia Bot: ok can");
            System.out.println(dash);
            System.out.print("Your response: ");
            input = sc.next();

            System.out.println("");

        }

        System.out.println(dash);
        System.out.println("You: " + input);
        System.out.println(farewell);
        System.out.println(dash);
        sc.close();
    }
}
