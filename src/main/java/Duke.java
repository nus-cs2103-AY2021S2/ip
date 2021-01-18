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
        String[] tasks = new String[100];
        int index = 0;
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")){
                break;
            }
            System.out.println(lineAfterCommand);
            if (command.equals("list")){
                for (int i = 0; i < index; i++){
                    System.out.println(String.valueOf(i + 1) + ". " + tasks[i]);
                }
                //System.out.println("\n");
                System.out.println(lineAfterCommand + "\n");
            }
            else {
                tasks[index] = command;
                index++;
                System.out.println("added: " + command);
                System.out.println(lineAfterCommand + "\n");
            }
        }
        System.out.println(lineAfterCommand + "\nBye. Hope to see you again soon!\n" + lineAfterCommand + "\n");
    }
}
