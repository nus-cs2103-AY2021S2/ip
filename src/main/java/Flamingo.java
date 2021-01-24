import java.io.FileNotFoundException;

public class Flamingo {

    private Storage storage;
    private TaskList tasks;

    public Flamingo() throws FileNotFoundException {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }

        Ui.sayHello();
        Parser.run(storage, tasks);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Flamingo();
    }
}
