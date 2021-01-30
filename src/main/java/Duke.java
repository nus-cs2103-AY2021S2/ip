public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public static void main(String[] args) {
        Duke duke = new Duke();

    }

    public Duke() {
        ui = new Ui();
        storage = new Storage();

        TaskList tempTasks;
        try {
            tempTasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printErrorMessage(e);
            tempTasks = new TaskList();
        }

        this.tasks = tempTasks;
        run();
    }

    private void run() {
        boolean isRunning = true;
        ui.printGreetings();

        while (isRunning) {
            try {
                String input = ui.readInput();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);

                if (command instanceof CommandBye) {
                    isRunning = false;
                }
            } catch (DukeException e) {
                ui.printErrorMessage(e);

            } finally {
                if (isRunning) {
                    ui.printInputPrompt();
                }
            }
        }
    }
    //    private void shutDown() {
    //        System.out.println("See you soon boss!");
    //        System.exit(0);
    //    }
}
