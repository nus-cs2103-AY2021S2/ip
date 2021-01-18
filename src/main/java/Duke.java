import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lineAfterCommand = "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println(lineAfterCommand + "\nHello! I'm  Duke");
        System.out.println("What can I do for you?\n" + lineAfterCommand + "\n");
        Task[] tasks = new Task[100];
        int index = 0;
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")){
                break;
            }
            System.out.println(lineAfterCommand);
            String[] temp = command.split(" ");
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println(String.valueOf(i + 1) + "." + tasks[i]);
                }
                //System.out.println("\n");
                System.out.println(lineAfterCommand + "\n");
            }
            else if (temp[0].equals("done")) {
                tasks[Integer.parseInt(command.split(" ")[1]) - 1] = tasks[Integer.parseInt(command.split(" ")[1]) - 1].finishTask();
                System.out.println("  " + tasks[Integer.parseInt(command.split(" ")[1]) - 1]);
                System.out.println(lineAfterCommand + "\n");
            }
            else {
                tasks[index] = new Task(command);
                index++;
                System.out.println("added: " + command);
                System.out.println(lineAfterCommand + "\n");
            }
        }
        System.out.println(lineAfterCommand + "\nBye. Hope to see you again soon!\n" + lineAfterCommand + "\n");
    }
}
