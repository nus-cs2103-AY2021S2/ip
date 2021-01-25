import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./src/main/java/tasks.txt";
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;

    private static void run() {
        ui = new Ui();
        ui.introduction();
        storage = new Storage(FILE_PATH);

        try {
            List<String> txtInput = storage.loadFromFile();
            if (txtInput.size() == 0) {
                ui.showMsg("You have no existing tasks!");
            } else {
                try {
                    ui.showMsg("You have existing tasks!");
                    List<Task> converted = FileTaskStringConverter.allStringToAllTask(txtInput);
                    taskList = new TaskList(converted);
                    taskList.printList();
                } catch (InvalidTaskTypeException e) {
                    ui.showError("Erroneous task type in file. Please check your file again!");
                }
            }

            Scanner scannerInput = new Scanner(System.in);
            ui.showMsg("What can I do for you?");
            boolean isExit = false;
            while (!isExit) {
                try {
                    String input = scannerInput.nextLine();
                    Parser p = new Parser(taskList, ui, storage);
                    Command c = p.parse(input);
                    c.execute();
                    isExit = c.isExit();
                } catch (DukeException e) {
                    ui.showError(e.getMessage());
                }
            }
            scannerInput.close();
        } catch (FileNotFoundException e) {
            ui.showError("Please create a 'tasks.txt' file in the current directory to store your tasks before "
                    + "running the program again!");
        }
    }

    public static void main(String[] args) {
        run();
    }
}