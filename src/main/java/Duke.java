import java.util.Scanner;

public class Duke {
    static final int LENGTH_OF_LINE = 80;

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

    static String horizontalLine = StringParser.underlineGenerator(LENGTH_OF_LINE);



    public static void main(String[] args) {
        boolean loop = true;
        String message = "";
        String command;
        Task currTask;
        CommandType type;
        JobList list = new JobList(LENGTH_OF_LINE);
        Scanner sc = new Scanner(System.in);

        System.out.println(welcome);

        while (true) {
            command = sc.nextLine();
            try {
                type = Command.getType(command);
            } catch (DukeException e) {
                message = e.getMessage() + "\n";
                System.out.print(horizontalLine);
                System.out.print(message);
                System.out.println(horizontalLine);
                continue;
            }
            if (type == CommandType.BYE) {
                loop = false;
            } else if (type == CommandType.LIST) {
                message = list.toString();
            } else if (type == CommandType.DONE) {
                int index;
                index = Integer.parseInt(command.substring(5)) - 1;
                if (index < list.getSize() && index >= 0) {
                    currTask = list.getJob(index);
                    currTask.markAsDone();
                    message = "This task is marked as done: \n"
                            + StringParser.newLiner(currTask.toString(), LENGTH_OF_LINE);
                    list.replaceJob(index, currTask);
                } else {
                    message = "No such task in the list\n";
                }
            } else if (type == CommandType.DELETE) {
                int index;
                index = Integer.parseInt(command.substring(7)) - 1;
                if (index < list.getSize() && index >= 0) {
                    currTask = list.getJob(index);
                    list.deleteJob(index);
                    message = "This task is deleted: \n"
                            + StringParser.newLiner(currTask.toString(), LENGTH_OF_LINE)
                            + "Now you have " + list.getSize()
                            + (list.getSize() == 1 ? " task in the list\n" : " tasks in the list\n");;
                } else {
                    message = "No such task in the list\n";
                }
            } else if (type == CommandType.OTHER) {
                message = "Invalid command\n";
            } else {
                try {
                    Task t = Command.convertToTask(command, type);
                    list.addJob(t);
                    message = "Task added:\n" + StringParser.newLiner(t.toString(), LENGTH_OF_LINE)
                            + "Now you have " + list.getSize()
                            + (list.getSize() == 1 ? " task in the list\n" : " tasks in the list\n");
                } catch (DukeException e) {
                    message = e.getMessage() + "\n";
                }
            }

            if (!loop) {
                break;
            }

            System.out.print(horizontalLine);
            System.out.print(message);
            System.out.println(horizontalLine);

        }
        System.out.println(bye);
    }
}
