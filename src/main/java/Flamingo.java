import java.io.FileNotFoundException;

/**
 * Represents the chat bot.
 */
public class Flamingo {

    public Flamingo() {
        Storage storage = new Storage();
        TaskList tasks;

        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }

        Ui.sayHello();
        Parser.run(storage, tasks);
    }

    public static void main(String[] args) {
        new Flamingo();
    }
}
