import java.io.IOException;

public class CommandHandler {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public CommandHandler(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Gets Maya's response to bye command.
     *
     * @return a String representing Maya's response to bye command.
     */
    String handleByeCommand() {
        return ui.showBye();
    }

    /**
     * Gets Maya's response to list command.
     *
     * @return a String representing Maya's response to list command.
     */
    String handleListCommand() {
        return ui.showList(taskList.getList(), false);
    }

    /**
     * Gets Maya's response to done command.
     *
     * @param index an integer represents the index of the task to be marked as done.
     * @return a String representing Maya's response to done command.
     */
    String handleDoneCommand(int index) throws IOException, CommandFormatException {
        try {
            Task doneTask = taskList.markTaskAsDone(index);
            storage.writeToFile(taskList.getList());
            return ui.showDone(doneTask);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandFormatException("Index entered is invalid!");
        }
    }

    /**
     * Gets Maya's response to the task command. Task commands consist of todo, deadline and event.
     *
     * @param taskType a String representing the type of Task.
     * @param name a String representing the name of the Task.
     * @param priority a String representing the level of priority of the Task.
     * @param time a String representing when the task is to be finished.
     * @return a String representing Maya's response to task commands.
     * @throws IOException if Storage cannot find file.
     */
    String handleTaskCommand(String taskType, String name,
                             String priority, String time) throws IOException {
        Task task;
        if (taskType.equals("todo")) {
            task = new Todo(name, priority);
        } else if (taskType.equals("deadline")){
            task = new Deadline(name, time, priority);
        } else {
            task = new Event(name, time, priority);
        }

        Task newTask = taskList.addTask(task);
        storage.appendToFile(task);
        return ui.showAddTask(newTask, taskList.getListSize());
    }

    /**
     * Gets Maya's response to find command.
     *
     * @param searchString a String representing the keyword to search for a task.
     * @return a String representing Maya's response to find command.
     */
    String handleFindCommand(String searchString) {
        return ui.showList(taskList.searchTask(searchString.trim()), true);
    }

    /**
     * Gets Maya's response to delete command.
     *
     * @param index an integer represents the index of the task to be deleted.
     * @return a String representing Maya's response to delete command.
     */
    String handleDeleteCommand(int index) throws IOException, CommandFormatException {
        try {
            Task removedTask = taskList.removeTask(index);
            storage.writeToFile(taskList.getList());
            return ui.showRemoveTask(removedTask, taskList.getListSize());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandFormatException("Index entered is invalid!");
        }
    }
}
