import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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

    public static void main(String[] args) {
        // Pre input setup
        System.out.println(STARTER);

        // Local saved records
        try {
            String currentDir = System.getProperty("user.dir");
            Path folder = Paths.get(currentDir, "data");
            Path file = Paths.get(currentDir, "data", "database.txt");
            if (Files.notExists(folder)) {
                Files.createDirectories(folder);
            }
            if (Files.notExists(file)) {
                Files.createFile(file);
            }
            List<String> list = Files.readAllLines(file);
            database.readTasks(list);

        } catch (IOException e) {
            e.printStackTrace();
        }

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

    private static boolean executeCommand(LegitCommand command) {
        switch (command) {
        case BYE:
            System.out.println("Bye now!");
            return true;
        case TODO:
            database.addToDB(new Todo(false, LegitCommand.TODO.getDetail()));
            database.updateFile();
            break;
        case EVENT:
            database.addToDB(new Event(false, LegitCommand.EVENT.getDetail()));
            database.updateFile();
            break;
        case DEADLINE:
            database.addToDB(new Deadline(false, LegitCommand.DEADLINE.getDetail()));
            database.updateFile();
            break;
        case LIST:
            database.listFromDB();
            break;
        case DONE:
            database.markDoneToDB(LegitCommand.DONE.getDetail());
            database.updateFile();
            break;
        case DELETE:
            database.deleteFromDB(LegitCommand.DELETE.getDetail());
            database.updateFile();
            break;
        }
        return false;
    }


}
