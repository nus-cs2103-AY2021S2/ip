import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lines = "    ____________________________________________________________";
        String indentation = "     ";
        System.out.println("Hello from\n" + logo);
        System.out.println(lines);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(lines);

        Tasks tasks = new Tasks();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(lines);
            if (input.equals("list")) {
                for (int i = 1; i < tasks.index(); i++) {
                    String output = tasks.checkDone(i);
                    System.out.println(indentation + output);
                }
            } else if (input.contains("done")){
                int number = Integer.parseInt(input.substring(5));
                tasks.markDone(number);
                System.out.println(indentation + "Nice! I've marked this task as done:");
                System.out.println(indentation + "  [X] " + tasks.get(number));
            } else {
                System.out.println(indentation + "added: " + input);
                tasks.add(input);
            }
            System.out.println(lines);
            input = sc.nextLine();
        }
        System.out.println(lines);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(lines);
    }
}
