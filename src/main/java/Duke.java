import java.lang.reflect.Array;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dash = "____________________________________________________________";
        String greeting1 = "Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.";
        String greeting2 = "Pai Kia Bot: What you want?";
        String greeting3 = "Pai Kia Bot: I will keep a list of your responses ah";
        String farewell = "Pai Kia Bot: Leave so soon ah? Limpeh sleep first, if got no issue don't disturb me.";

        ArrayList<String> inputList = new ArrayList<>();

        System.out.println(dash);
        System.out.println(greeting1);
        System.out.println(greeting2);
        System.out.println(dash);
        System.out.print("Your response: ");
        String input = sc.next();
        if (!input.equals("list")) {
            inputList.add(input);
        }
        System.out.println("");

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int counter = 1;
                System.out.println(dash);
                System.out.println("Pai Kia Bot: i send u your input list ah");

                for (String s : inputList) {
                    System.out.println(counter + ": " + s);
                    counter++;
                }
                System.out.println(dash);
            } else {
                System.out.println(dash);
                System.out.println("You: " + input);
                System.out.println("Pai Kia Bot: ok can, added ur response chop chop fast game");
                System.out.println(dash);
            }

            System.out.print("Your response: ");
            input = sc.next();
            if (!input.equals("list")) {
                inputList.add(input);
            }
            System.out.println("");

        }

        System.out.println(dash);
        System.out.println("You: " + input);
        System.out.println(farewell);
        System.out.println(dash);
        sc.close();
    }
}
