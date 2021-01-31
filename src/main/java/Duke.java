import java.io.File;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        storage.loadFile(filePath);
        File file = new File(filePath);
        Scanner sc = new Scanner(System.in);
        this.taskList = new TaskList(storage.initialiseStorage(file));
        this.parser = new Parser(this.taskList, storage);
        this.ui = new Ui(sc, parser);

    }

    public void run() {
        this.ui.executeInput();
    }


    public static void main(String[] args) {

        new Duke("./data/tasks.txt").run();


    }


}
