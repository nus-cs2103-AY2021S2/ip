import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Haha {

    // Static properties
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Database database = new Database();
    private static final String LINE_BREAK = "____________________________________________________________\n";

    private Storage storage;
    private Ui ui;

    public Haha() {
        this.storage = new Storage();
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        Haha haha = new Haha();
        // Local saved records
        try {
            List<String> list = haha.storage.getTasks();
            database.readTasks(list);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        haha.run();
    }

    public void run() {
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
