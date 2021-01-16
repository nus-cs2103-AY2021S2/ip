import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "";
        for (int i = 0; i < 50; i++) {
            line = line.concat("_");
        }
        System.out.println(line);
        System.out.println("Hello! I'm Benny");
        System.out.println("What can I do for you?");
        System.out.println(line);

        List<String> newStorage = new ArrayList<>();

        while(sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                indentInput("Hope to see you again!");
                break;
            }

            else if (userInput.equals("list")) {
                for (int i = 0; i < newStorage.size(); i++) {
                    System.out.println(i+1 + "." + newStorage.get(i));
                }
            }
            else {
                indentInput(userInput);
                newStorage.add(userInput);
            }
        }

    }

    public static void indentInput(String input) {
        String line = "";
        for (int i = 0; i < 50; i++) {
            line = line.concat("_");
        }
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }
}
