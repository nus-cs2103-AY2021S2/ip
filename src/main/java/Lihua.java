// Design idea adapted from https://github.com/se-edu/addressbook-level2
public class Lihua {
    private Tasks tasks;
    private Storage storage;
    private Ui ui;

    public static void main(String[] args) {
        new Lihua().run(args);
    }

    public void run(String[] args) {
        start(args);
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void runCommandLoopUntilExitCommand() {
        String userInput = ui.getUserInput();
        while (!userInput.equals("bye")) {
            actionUpon(userInput);
            userInput = ui.getUserInput();
        }
    }

    private void start(String[] args) {
        try {
            ui = new Ui();
            storage = new Storage();
            tasks = storage.load();
            ui.printHello();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void exit() {
        ui.printGoodbye();
        System.exit(0);
    }

    /**
     * Action upon the user input.
     * E.g. Add a new task to the list, show the tasks in the list
     * @param userInput user input string, which is not the exit signal
     */
    private void actionUpon(String userInput) {
        ui.printHorizontalLine();
        if (FormatChecker.isPrintingList(userInput)) {
            tasks.printList();
        } else if (FormatChecker.isTryingToGetTaskDone(userInput)) {
            tasks.getTaskDone(userInput);
            storage.saveTasks(tasks);
        } else if (FormatChecker.isTryingToAddTask(userInput)) {
            tasks.addTask(userInput);
            storage.saveTasks(tasks);
        } else if (FormatChecker.isTryingToDeleteTask(userInput)) {
            tasks.deleteTask(userInput);
            storage.saveTasks(tasks);
        } else if (FormatChecker.isTryingToGetHelp(userInput)) {
            OperationTypes.printInstructions();
        } else if (FormatChecker.isTryingToPrintTasksOnOneDay(userInput)) {
            tasks.printTasksOnDate(userInput);
        } else {
            ui.printGetHelpMessage();
        }
        ui.printHorizontalLine();
    }
}
