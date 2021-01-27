import java.util.Scanner;

import weiliang.bot.UI;
import weiliang.bot.task.TaskList;
import weiliang.bot.DukeException;
import weiliang.bot.Storage;

public class Duke implements Runnable {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke() {
        this.storage = new Storage("memory.txt");
        this.ui = new UI("SimpleBot");
    }

    public void init() throws DukeException {
        this.tasks = storage.loadTasks();
        this.ui.loadTasks(tasks);
    }
    
    @Override
    public void run() {
        System.out.println(ui.getInitMessage());

        // Perform main logic loop
        try (Scanner scanner = new Scanner(System.in)) {
            while (ui.isActive()) {
                System.out.print("You: ");
                String reply;
                try {
                    reply = ui.respond(scanner.nextLine());
                } catch (DukeException e) {
                    reply = ui.formatMessage(e.getMessage());
                }
                // Additional line break
                System.out.println();
                System.out.println(reply);
            }

            // Update after stopping
            storage.storeFile(tasks);
        }    
    }

    public static void main(String[] args) {
        try {
            Duke duke = new Duke();
            duke.init();
            duke.run();
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}
