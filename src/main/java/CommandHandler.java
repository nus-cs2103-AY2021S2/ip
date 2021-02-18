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

    String handleByeCommand() {
        return ui.showBye();
    }

    String handleListCommand() {
        return ui.showList(taskList.getList(), false);
    }

    String handleDoneCommand(int index) throws IOException, CommandFormatException {
        try {
            Task doneTask = taskList.markTaskAsDone(index);
            storage.writeToFile(taskList.getList());
            return ui.showDone(doneTask);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandFormatException("Index entered is invalid!");
        }
    }

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

    String handleFindCommand(String searchString) {
        return ui.showList(taskList.searchTask(searchString.trim()), true);
    }

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
