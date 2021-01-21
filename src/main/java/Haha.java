import java.util.Scanner;

public class Haha {

    // Static properties
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Database database = new Database();
    private static final String LINE_BREAK = "____________________________________________________________\n";
    private static final String LOGO = " _    _          _    _\n" +
            "| |  | |   /\\   | |  | |   /\\\n" +
            "| |__| |  /  \\  | |__| |  /  \\\n" +
            "|  __  | / /\\ \\ |  __  | / /\\ \\\n" +
            "| |  | |/ ____ \\| |  | |/ ____ \\\n" +
            "|_|  |_/_/    \\_\\_|  |_/_/    \\_\\\n";
    private static final String STARTER = "Hello from\n" + LOGO
            + LINE_BREAK
            + "Dude, I'm HAHA.\n"
            + "What can I do for you?\n"
            + "(Oh when you are done, say bye)\n"
            + LINE_BREAK;


    private static boolean executeCommand(LegitCommand command) {
        switch (command) {
            case BYE:
                System.out.println("Bye now!");
                return true;
            case TODO:
                database.addToDB(new Todo(false, LegitCommand.TODO.getDetail()));
                break;
            case EVENT:
                database.addToDB(new Event(false, LegitCommand.EVENT.getDetail()));
                break;
            case DEADLINE:
                database.addToDB(new Deadline(false, LegitCommand.DEADLINE.getDetail()));
                break;
            case LIST:
                database.listFromDB();
                break;
            case DONE:
                database.markDoneToDB(LegitCommand.DONE.getDetail());
                break;
            case DELETE:
                database.deleteFromDB(LegitCommand.DELETE.getDetail());
                break;
        }
        return false;
    }


    public static void main(String[] args) {
        // Pre input setup
        System.out.println(STARTER);

        // Start while loop
        while (SCANNER.hasNext()) {
            try {
                // Parse input
                LegitCommand command = Parser.parseInput(SCANNER.nextLine());
                // Execute command
                boolean end = executeCommand(command);
                // Close current loop
                System.out.println(LINE_BREAK);
                if (end) {
                    break;
                }
            } catch (HahaException ex) {
                System.out.println(ex);
                System.out.println(LINE_BREAK);
            }

        }
    }

}
