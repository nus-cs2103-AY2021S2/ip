import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        Boolean endOfCycle = false;

        while(!endOfCycle) {
            String nextCommand = sc.nextLine();
            if (nextCommand.equals("bye")) {
                bye();
                endOfCycle = true;
            } else {
                echo(nextCommand);
            }
        }

        sc.close();
    }

    public static void greet() {
        System.out.println("Hello! I'm Jay \n" + "What can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo(String command) {
        System.out.println(command);
    }
}

