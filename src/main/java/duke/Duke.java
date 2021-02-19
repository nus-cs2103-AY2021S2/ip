package duke;

import java.util.List;

/**
 * This class is where the logic related to Duke comes together and gets executed
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of Duke
     */
    public Duke() {

        ui = new Ui();
        storage = new Storage("data/tasks.txt");

        try {
            tasks = new TaskList(storage.load()); // returns a List<Task> of all tasks
        } catch (DukeException e) {
            ui.showLoadingError();
            ui.showLine();
            tasks = new TaskList();
        }
    }

    /**
     * Take user's input and generate corresponding response back to the user
     *
     * @param userInput
     * @return String Response to the user
     */
    public String getResponse(String userInput) {

        ui.clearResponse();

        Parser parser = new Parser();
        String[] parsedInput;

        try {
            parsedInput = parser.processInput(userInput);

            if (parsedInput[0].equals("LST")) {
                handleList();
            } else if (parsedInput[0].equals("BYE")) {
                handleBye();
            } else if (parsedInput[0].equals("DON")) {
                handleDone(parsedInput);
            } else if (parsedInput[0].equals("TDO")) {
                handleTodo(parsedInput);
            } else if (parsedInput[0].equals("DDL")) {
                handleDeadline(parsedInput);
            } else if (parsedInput[0].equals("ENT")) {
                handleEvent(parsedInput);
            } else if (parsedInput[0].equals("DLT")) {
                handleDelete(parsedInput);
            } else if (parsedInput[0].equals("FND")) {
                handleFind(parsedInput);
            } else if (parsedInput[0].equals("UPD")) {
                handleUpdate(parsedInput);
            } else {
                ui.showError("Something went wrong!");
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        System.out.println(ui.getResponse());
        return ui.getResponse();
    }

    /**
     * Outputs to UI when user command is list
     */
    public void handleList() {
        if (tasks.isEmpty()) {
            ui.showEmptyListMsg();
        } else {
            ui.showListMsg();
            ui.printTasks(tasks);
        }
        ui.showLine();
    }

    /**
     * Outputs bye message to UI
     */
    public void handleBye() {
        ui.showByeMsg();
        ui.showLine();
    }

    /**
     * Executes processes relevant to 'done', outputs to UI
     *
     * @param parsedInput parsed user input
     */
    public void handleDone(String[] parsedInput) {
        int taskIndex = Integer.parseInt(parsedInput[1]);

        if (tasks.checkTaskExist(taskIndex)) {
            Task currTask = tasks.getTask(taskIndex);
            if (currTask.isDone) {
                ui.showIsAlrDoneMsg();
                ui.showLine();
            } else {
                currTask.markAsDone();

                ui.showDoneMsg();
                ui.printTask(currTask);
                ui.showLine();

                storage.save(tasks);
            }
        } else {
            ui.showError("Requested task does not exist");
            ui.showLine();
        }
    }

    /**
     * Executes processes relevant to 'todo', outputs to UI
     *
     * @param parsedInput parsed user input
     */
    public void handleTodo(String[] parsedInput) {
        Todo newTodo = new Todo(parsedInput[1]);
        tasks.addTask(newTodo);

        ui.showTaskMsg();
        ui.printTask(newTodo);
        ui.showTaskCount(tasks.getTaskCount());
        ui.showLine();

        storage.save(tasks);
    }

    /**
     * Executes processes relevant to 'deadline', outputs to UI
     *
     * @param parsedInput parsed user input
     */
    public void handleDeadline(String[] parsedInput) {
        Deadline newDl = new Deadline(parsedInput[1], parsedInput[2]);
        tasks.addTask(newDl);

        ui.showTaskMsg();
        ui.printTask(newDl);
        ui.showTaskCount(tasks.getTaskCount());
        ui.showLine();

        storage.save(tasks);
    }

    /**
     * Executes processes relevant to 'event', outputs to UI
     *
     * @param parsedInput parsed user input
     */
    public void handleEvent(String[] parsedInput) {
        Event newEnt = new Event(parsedInput[1], parsedInput[2]);
        tasks.addTask(newEnt);

        ui.showTaskMsg();
        ui.printTask(newEnt);
        ui.showTaskCount(tasks.getTaskCount());
        ui.showLine();

        storage.save(tasks);
    }

    /**
     * Executes processes relevant to 'delete', outputs to UI
     *
     * @param parsedInput parsed user input
     */
    public void handleDelete(String[] parsedInput) {
        int taskIndex = Integer.parseInt(parsedInput[1]);

        if (tasks.checkTaskExist(taskIndex)) {
            Task currTask = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);

            ui.showDeleteMsg();
            ui.printTask(currTask);
            ui.showLine();

            storage.save(tasks);
        } else {
            ui.showError("Requested task does not exist");
            ui.showLine();
        }
    }

    /**
     * Executes processes relevant to 'find', outputs to UI
     *
     * @param parsedInput parsed user input
     */
    public void handleFind(String[] parsedInput) {
        String keyword = parsedInput[1];

        List<Task> foundTasks = tasks.findTask(keyword);

        if (foundTasks.isEmpty()) {
            ui.showCannotFind();
            ui.showLine();
        } else {
            ui.showFoundText();
            int i = 1;
            for (Task t : foundTasks) {
                ui.appendResponse(i + ". " + t.toString() + "\n");
                i++;
            }
            ui.showLine();
        }
    }

    /**
     * Executes processes relevant to 'update', outputs to UI
     *
     * @param parsedInput parsed user input
     */
    public void handleUpdate(String[] parsedInput) throws DukeException {
        tasks.updateTask(parsedInput);

        Task updatedTask = tasks.getTask(Integer.parseInt(parsedInput[1]));
        ui.showTaskUpdated();
        ui.appendResponse(updatedTask.toString());
    }
}
