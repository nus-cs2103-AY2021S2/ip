import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Marvin and I will assist you with your tasks.\n"
                + "What can I do for you today?\n";
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        boolean isDone = false;

        while (!isDone) {
            String command = in.nextLine();
            if (command.equals("bye"))
                isDone = true;
            else
                System.out.println(command + "\n");
        }

        System.out.println("Bye. Hope to see you again soon!\n");
        in.close();
    }
}
