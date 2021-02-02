import java.io.IOException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Maya {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises a newly created Maya Object
     * so that it represents a Bot.
     *
     * @param filePath a String of the path
     *                 where the TaskList is stored.
     */
    public Maya(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        } catch (UnknownCommandException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Starts the Maya object to accept user commands.
     * Processes user commands by utilising Parser object
     * to make sense of the user command.
     */
    public void run() {
        ui.showWelcome();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String command = sc.next();
                ui.showLine();

                Parser.parse(command, sc, ui, taskList, storage );

                // To exit the program with the command "bye"
                if (command.equals("bye")) {
                    break;
                }
            } catch (UnknownCommandException e) {
                ui.showError(e.getMessage());
            } catch (NoSuchElementException e) {
                ui.showError(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Maya("data/task.txt").run();
    }
}
