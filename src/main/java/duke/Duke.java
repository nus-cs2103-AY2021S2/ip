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
     *
     * @param nil
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
     * @param String user input
     * @return String Response to the user
     */
    public String getResponse(String userInput) {

        ui.clearResponse();

        Parser parser = new Parser();
        String[] parsedInput;

        try {
            parsedInput = parser.processInput(userInput);

            if (parsedInput[0].equals("LST")) {
                ui.showListMsg();
                ui.printTasks(tasks);
                ui.showLine();
            } else if (parsedInput[0].equals("BYE")) {
                ui.showByeMsg();
                ui.showLine();
            } else if (parsedInput[0].equals("DON")) {
                int taskIndex = Integer.parseInt(parsedInput[1]);

                if (tasks.checkTaskExist(taskIndex)) {
                    Task currTask = tasks.getTask(taskIndex);
                    currTask.markAsDone();

                    ui.showDoneMsg();
                    ui.printTask(currTask);
                    ui.showLine();

                    storage.save(tasks);
                } else {
                    ui.showError("Requested task does not exist");
                    ui.showLine();
                }
            } else if (parsedInput[0].equals("TDO")) {
                Todo newTodo = new Todo(parsedInput[1]);
                tasks.addTask(newTodo);

                ui.showTaskMsg();
                ui.printTask(newTodo);
                ui.showTaskCount(tasks.getTaskCount());
                ui.showLine();

                storage.save(tasks);
            } else if (parsedInput[0].equals("DDL")) {
                Deadline newDl = new Deadline(parsedInput[1], parsedInput[2]);
                tasks.addTask(newDl);

                ui.showTaskMsg();
                ui.printTask(newDl);
                ui.showTaskCount(tasks.getTaskCount());
                ui.showLine();

                storage.save(tasks);
            } else if (parsedInput[0].equals("ENT")) {
                Event newEnt = new Event(parsedInput[1], parsedInput[2]);
                tasks.addTask(newEnt);

                ui.showTaskMsg();
                ui.printTask(newEnt);
                ui.showTaskCount(tasks.getTaskCount());
                ui.showLine();

                storage.save(tasks);
            } else if (parsedInput[0].equals("DLT")) {
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

            } else if (parsedInput[0].equals("FND")) {
                String keyword = parsedInput[1];

                List<Task> foundTasks = tasks.findTask(keyword);

                if (foundTasks.isEmpty()) {
                    ui.showCannotFind();
                    ui.showLine();
                } else {
                    ui.showFoundText();
                    int i = 1;
                    for (Task t : foundTasks) {
                        ui.appendResponse(i + ". " + t.toString());
                        i++;
                    }
                    ui.showLine();
                }

            } else {
                ui.showError("Something went wrong!");
            }
        } catch(DukeException e) {
            ui.showError(e.getMessage());
        }
        System.out.println(ui.getResponse());
        return ui.getResponse();
    }

}
