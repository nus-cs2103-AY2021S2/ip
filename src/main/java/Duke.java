import java.util.Scanner;

enum CommandType {
    ADD,
    LIST,
    DONE,
    BYE
}

public class Duke {
    static String welcome = " __________________________ \n"
            + "|  HI! THIS IS             |\n"
            + "|   ____        _          |\n"
            + "|  |  _ \\ _   _| | _____   |\n"
            + "|  | | | | | | | |/ / _ \\  |\n"
            + "|  | |_| | |_| |   <  __/  |\n"
            + "|  |____/ \\__,_|_|\\_\\___|  |\n"
            + "|                          |\n"
            + "|  What can I do for you?  |\n"
            + "|__________________________|\n";

    static String bye = " __________________________ \n"
            + "|  GOOD BYE!               |\n"
            + "|   ____        _          |\n"
            + "|  |  _ \\ _   _| | _____   |\n"
            + "|  | | | | | | | |/ / _ \\  |\n"
            + "|  | |_| | |_| |   <  __/  |\n"
            + "|  |____/ \\__,_|_|\\_\\___|  |\n"
            + "|                          |\n"
            + "|  Always be with you.     |\n"
            + "|__________________________|\n";

    static String horizontalLine = "____________________________________________________________\n";



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = "";
        Task currTask;
        JobList list = new JobList();

        System.out.println(welcome);
        String command = sc.nextLine();
        while (Command.getType(command) != CommandType.BYE) {
            System.out.print(horizontalLine);
            switch (Command.getType(command)) {
                case ADD:
                    currTask = new Task(command);
                    list.addJob(currTask);
                    message = StringParser.newLiner("Added: " + currTask.getDescription(), 60);
                    break;
                case LIST:
                    message = list.formatList();
                    break;
                case DONE:
                    int index;
                    try {
                        index = Integer.parseInt(command.substring(5)) - 1;
                        currTask = list.getJob(index);
                        currTask.markAsDone();
                        message = "This task is marked as done: \n"
                                + StringParser.newLiner(currTask.getDescription(), 60);
                        list.replaceJob(index, currTask);
                    } catch (NumberFormatException e) {
                        message = "Invalid command\n";
                    } catch (IndexOutOfBoundsException e) {
                        message = "Do not have such task\n";
                    }
                    break;
            }

            System.out.print(message);
            System.out.println(horizontalLine);
            command = sc.nextLine();
        }
        System.out.println(bye);
    }
}
