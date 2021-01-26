public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private boolean isActive;
    
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.isActive = true;

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            this.tasks = new TaskList();
        }
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke("savedata.txt");
        duke.run();
    }

    public void run() {
        // display welcome sequence
        ui.welcome();

        String userInput;
        // loop until the user exits
        while (isActive) {
            // get user input
            userInput = ui.readCommand();
            try {
                // display list
                if (userInput.toLowerCase().equals("list")) {
                    ui.borderPrint(tasks.display());
                // finish a task
                } else if (userInput.toLowerCase().matches("^(do(ne)?|finish(ed)?|completed?) \\d+$")) {
                    String[] bits = userInput.split(" ");
                    int idx = Integer.parseInt(bits[1]);
                    if (idx < 1 || idx > tasks.size()) {
                        throw new DukeException("Oops! That doesn't appear to be a valid task number.");
                    } else {
                        finishTask(tasks.get(idx));
                    }
                // manually remove task
                } else if (userInput.toLowerCase().matches("^(delete|remove) \\d+$")) {
                    String[] bits = userInput.split(" ");
                    int idx = Integer.parseInt(bits[1]);
                    if (idx < 1 || idx > tasks.size()) {
                        throw new DukeException("Oops! That doesn't appear to be a valid task number.");
                    } else {
                        deleteTask(idx);
                    }
                // add task to list
                } else if (userInput.toLowerCase().matches("^(todo|deadline|event)( .+)?$")) {
                    addTask(Parser.parseTask(userInput), true);
                } else if (userInput.toLowerCase().equals("bye")) {
                    endSession();
                } else {
                    throw new DukeException("I don't understand that command!");
                }
            } catch (Exception e) {
                ui.borderPrint(e.getMessage());
            }
        }
        
        // exit sequence
        ui.quit();
    }

    private void addTask(Task task, boolean isVerbose) throws DukeException {
        tasks.add(task);
        if (isVerbose) {
            String msg = String.format("I've added this task: %s\nYou now have %d items on your todo list.",
                    task.toString(),
                    tasks.size());
            ui.borderPrint(msg);
        }
        storage.saveTasks(tasks);
    }
    
    private void finishTask(Task task) throws DukeException {
        if (task.isDone()) {
            throw new DukeException("That task's already done!");
        } else {
            task.finish();
            String msg = String.format("Congrats! The following task has been marked as done:\n  %s",
                    task.toString());
            ui.borderPrint(msg);
        }
        storage.saveTasks(tasks);
    }
    
    private void deleteTask(int idx) throws DukeException {
        String msg = String.format("Removed task: %s\nYou now have %d items on your todo list.",
                tasks.remove(idx).toString(),
                tasks.size());
        ui.borderPrint(msg);
        storage.saveTasks(tasks);
    }

    private void endSession() {
        isActive = false;
    }
}