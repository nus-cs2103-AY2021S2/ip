import java.util.Scanner;

enum CommandType {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    DONE,
    BYE,
    OTHER
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
        CommandType type = Command.getType(command);
        while (type != CommandType.BYE) {
            System.out.print(horizontalLine);
            switch (type) {
                case LIST:
                    message = list.toString();
                    break;
                case DONE:
                    int index;
                    try {
                        index = Integer.parseInt(command.substring(5)) - 1;
                        currTask = list.getJob(index);
                        currTask.markAsDone();
                        message = "This task is marked as done: \n"
                                + StringParser.newLiner(currTask.toString(), 60);
                        list.replaceJob(index, currTask);
                    } catch (NumberFormatException e) {
                        message = "Invalid command\n";
                    } catch (IndexOutOfBoundsException e) {
                        message = "Do not have such task\n";
                    }
                    break;
                default:
                    try {
                        Task t = Command.convertToTask(command, type);
                        list.addJob(t);
                        message = "Task added:\n" + StringParser.newLiner(t.toString(), 60)
                                + "Now you have " + list.getSize()
                                + (list.getSize() == 1 ? " task in the list\n" : " tasks in the list\n");
                    } catch (DukeException e) {
                        message = e.getMessage() + "\n";
                    }

            }

            System.out.print(message);
            System.out.println(horizontalLine);
            command = sc.nextLine();
            type = Command.getType(command);
        }
        System.out.println(bye);
    }
}
