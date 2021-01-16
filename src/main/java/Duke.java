import java.util.Scanner;

public class Duke {

    private static Task[] taskArray;
    private static int count = 0;
    private static Scanner sc = new Scanner(System.in);
    static final String lines = "----------------------------------------";

    public static void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi There! Greetings from \n" + logo);
        System.out.println(lines + "\n" + " Good day! I'm Duke" + "\n" + " How can I help you today? " + "\n" + lines);

    }

    public static void displayEndMessage() {
        sc.close();
        System.out.println(lines + "\n" + " Bye. Hope to see you again!" + "\n" + lines);
    }

    public static void executeCommand(String command) {

        String commandArray[] = command.split("\\s+");

        switch (commandArray[0]) {
            case ("list"):
                
                System.out.println(lines);
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + taskArray[i].toString());
                }
                System.out.println(lines);
                break;

            case ("done"):
                int index = Integer.parseInt(commandArray[1])-1;
                taskArray[index].setCompleted();

                System.out.println(lines + " \n" +"Nice! I'll make this task as done:" + "\n"
                        + taskArray[index].toString() + "\n" + lines);
                break;

            default:
                taskArray[count] = new Task(command);
                count++;
                System.out.println("added: " + command + "\n" + lines);
                break;
        }
    }

    public static void main(String[] args) {

        displayWelcomeMessage();

        String userInput = sc.nextLine();
        taskArray = new Task[100];

        while (!userInput.equals("bye")) {
            executeCommand(userInput);
            userInput = sc.nextLine();
        }

        displayEndMessage();
    }
}
