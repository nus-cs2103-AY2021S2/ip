package main.java;

public class Parser {
    public void handleInput(Storage storage, Ui ui, TaskList tasks, String cmd) throws EmptyDescriptionException, InvalidCommandException, WriteTasksException {
        if (cmd.equals("list")) {
            ui.printTasks(tasks.getPrintableTasks());
        } else {
            String[] arr = cmd.split(" ", 2);
            switch (arr[0]) {
            case "done":
                int doneId = Integer.parseInt(arr[1]);
                tasks.markTaskAsDone(doneId);
                ui.displayMarkTaskAsDoneMsg(tasks.getTaskString(doneId));
                break;
            case "delete":
                int deleteId = Integer.parseInt(arr[1]);
                ui.displayDeleteTaskMsg(tasks.getTaskString(deleteId), tasks.getNumOfTasks() - 1);
                tasks.deleteTask(deleteId);
                break;
            case "todo":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "event":
                tasks.addTask(cmd);
                int numOfTasks = tasks.getNumOfTasks();
                ui.displayAddTaskMsg(tasks.getTaskString(numOfTasks), numOfTasks);
                break;
            default:
                throw new InvalidCommandException();
            }
            storage.writeTasksToDisk(tasks);
        }
    }
}
