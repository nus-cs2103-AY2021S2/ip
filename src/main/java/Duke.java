
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        String input = ui.getInput();
        try {
            while (!input.equals("bye")) {
                if (input.equals("list")) {
                    if (taskList.getSize() == 0) {
                        ui.printEmptyList();
                    } else {
                        ui.printList();
                    }
                } else {
                    taskList = Parser.parseInput(input, taskList);
                }
                input = ui.getInput();
            }
            ui.end();
        } catch (DukeException e) {
            ui.showError(e);
        }

        try {
            storage.writeToFile(taskList, "./data/duke.txt");
        } catch (DukeException e) {
            ui.showWritingError();
        }
    }
}