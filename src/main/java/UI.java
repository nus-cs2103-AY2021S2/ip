import java.io.IOException;
import java.util.Scanner;

public class UI {
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

    static Scanner sc = new Scanner(System.in);
    static TaskList list = new TaskList();

    static void printError(DukeException e) {
        System.out.print(horizontalLine);
        System.out.print(e.getMessage() + "\n");
        System.out.println(horizontalLine);
    }

    static void printTask(String command, CommandType type) {
        System.out.print(horizontalLine);
        try {
            Task t = CommandParser.convertToTask(command, type);
            list.addJob(t);
            System.out.print("Task added:\n" + StringParser.newLiner(t.toString(), LENGTH_OF_LINE)
                    + "Now you have " + list.getSize()
                    + (list.getSize() == 1 ? " task in the list\n" : " tasks in the list\n"));
        } catch (DukeException e) {
            System.out.print(e.getMessage() + "\n");
        }
        System.out.print(horizontalLine);
    }

    static void doneTask(String command) {
        System.out.print(horizontalLine);
        int index;
        index = Integer.parseInt(command.substring(5)) - 1;
        if (index < list.getSize() && index >= 0) {
            Task currTask = list.getJob(index);
            currTask.markAsDone();
            list.replaceJob(index, currTask);
            System.out.print("This task is marked as done: \n"
                    + StringParser.newLiner(currTask.toString(), LENGTH_OF_LINE));
        } else {
            System.out.print("No such task in the list\n");
        }
        System.out.print(horizontalLine);
    }

    static void deleteTask(String command) {
        System.out.print(horizontalLine);
        int index;
        index = Integer.parseInt(command.substring(7)) - 1;
        if (index < list.getSize() && index >= 0) {
            Task currTask = list.getJob(index);
            list.deleteJob(index);
            System.out.print("This task is deleted: \n"
                    + StringParser.newLiner(currTask.toString(), LENGTH_OF_LINE)
                    + "Now you have " + list.getSize()
                    + (list.getSize() == 1 ? " task in the list\n" : " tasks in the list\n"));
        } else {
            System.out.print("No such task in the list\n");
        }
        System.out.print(horizontalLine);
    }

    static void printList() {
        System.out.print(horizontalLine);
        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i < list.getSize(); i++) {
            resultStr.append(StringParser.newLiner((i + 1) + "."
                    + list.getJob(i).toString(), LENGTH_OF_LINE));
        }
        if (list.getSize() == 0) {
            System.out.print("List is empty\n");
        } else {
            System.out.print(resultStr.toString());
        }
        System.out.print(horizontalLine);
    }

    static void printInvalid() {
        System.out.print(horizontalLine);
        System.out.print("Invalid command\n");
        System.out.print(horizontalLine);
    }

    static void loadAndSayHello() {
        list = Storage.loadToList();
        System.out.println(welcome);
    }

    static void saveAndGoodBye() {
        Storage.writeToData(list);
        System.out.println(bye);
    }

    public static void mainLoop() {
        boolean loop = true;
        String commandStr;
        CommandType command;

        try {
            Storage.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadAndSayHello();

        while (loop) {
            commandStr = sc.nextLine();

            try {
                command = CommandParser.getType(commandStr);
            } catch (DukeException e) {
                printError(e);
                continue;
            }



            switch (command) {
            case TODO:
            case DEADLINE:
            case EVENT:
                printTask(commandStr, command);
                break;
            case LIST:
                printList();
                break;
            case DONE:
                doneTask(commandStr);
                break;
            case DELETE:
                deleteTask(commandStr);
                break;
            case BYE:
                loop = false;
                saveAndGoodBye();
                break;
            case OTHER:
                printInvalid();
            }
        }
    }

}
