import java.util.Scanner;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loaded = storage.loadFile();
        if (loaded == null) { // No files exist or file is corrupted
            tasks = new TaskList();
            storage.createDirectoryAndFile();
        } else {
            tasks = loaded;
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        final Scanner scan = new Scanner(System.in);
        while (!isExit && scan.hasNextLine()) {
            final String input = scan.nextLine().strip();
            ui.showLine();

            if (input.equals("")) {
                System.out.println("\t...");
            } else {
                try {
                    final Command command = Parser.parseCommand(input);
                    command.execute(tasks, ui, storage);
                    isExit = command.isExit();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            ui.showLine();
        }
        scan.close();

    }

    public static void main(final String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
