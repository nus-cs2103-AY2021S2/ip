import java.util.Locale;
import java.util.Scanner;

public class SwitchBlade {

    private static void echo(String input) {
        System.out.println("Added to list: " + input + "\n");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm SwitchBlade and I aim to do everything you want me to do!");
        myList taskList = new myList();

        Scanner sc = new Scanner(System.in);
        String input = "bye";

        if (sc.hasNext())
            input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            String command = input.split("\\s+")[0];

            switch (command.toLowerCase(Locale.ROOT)) {
                case "list":
                    System.out.println(taskList.toString());
                    break;
                case "done":
                    if (input.split("\\s+").length < 3) {
                        int index = Integer.parseInt(input.split("\\s+")[1]);
                        taskList.markCompleted(index - 1);
                    } else {
                        System.out.println("Too many arguments, please give me just 1 task to mark as completed");
                    }
                    break;

                default:
                    taskList.addTask(input);
                    break;

            }

            if (sc.hasNext())
                input = sc.nextLine();
        }

        System.out.println("See you soon!");
    }
}
