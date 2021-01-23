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
        System.out.print(e.getMessage() + "\n");
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
        boolean isExit = false;
        String commandStr;
        Command command;

        try {
            Storage.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadAndSayHello();

        while (!isExit) {
            commandStr = sc.nextLine();

            System.out.print(horizontalLine);
            try {
                command = StringParser.parseCommand(commandStr, list);
                command.executeAndPrint(list, LENGTH_OF_LINE);
                isExit = command.isExit();
                if (!isExit) {
                    System.out.print(horizontalLine);
                }
            } catch (DukeException e) {
                printError(e);
                System.out.print(horizontalLine);
            }
        }
        saveAndGoodBye();
    }

}
