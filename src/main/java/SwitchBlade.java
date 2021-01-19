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
            if (!input.equalsIgnoreCase("list"))
                taskList.addTask(input);
            else
                System.out.println(taskList.toString());

            if (sc.hasNext())
                input = sc.nextLine();
        }

        System.out.println("See you soon!");
    }
}
