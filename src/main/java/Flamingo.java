import java.io.FileNotFoundException;

public class Flamingo {

    private Storage storage = new Storage();
    private TaskList tasks;

    public Flamingo() {
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
