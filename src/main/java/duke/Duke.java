package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;



/**
 * The Duke program is a program that can help you
 * with the schedule management such as adding events, deadlines and todos
 * to it and it can also help you manage the tasks such as deleting tasks.
 *
 * Hope you like it!
 *
 * @author skinnychenpi
 * @since 2021-01-18
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;


    /**
     * A constructor for Duke class
     *
     * @param filePath The file path that stores the task list.
     * @param dirPath The directory path that stores the directory of the task list file.
     */
    public Duke(String filePath, String dirPath) {
        storage = new Storage(filePath, dirPath);
        taskList = dukeReadFromStorage();
    }

    public TaskList getTaskList() {
        return taskList;
    }

    private TaskList dukeReadFromStorage() {
        try {
            TaskList taskListFromStorage = storage.readTasks(new TaskList());
            return taskListFromStorage;
        } catch (Exception e) {
            System.out.println("Errors occur when duke try to read tasks from txt files:" + e.getMessage());
            return new TaskList();
        }
    }

    public void dukeSaveToStorage() {
        try {
            storage.saveTasks(taskList);
        } catch (Exception e) {
            System.out.println("Errors occur when duke try to save tasks to txt files:" + e.getMessage());
        }
    }


    public String getResponse(String input) {
        TaskList taskList = this.getTaskList();
        try {
            Command c = Parser.parse(input);
            String botMessage = c.execute(taskList);
            return "Duke says: " + botMessage;
        } catch (Exception e) {
            String botMessage = e.getMessage();
            return "Duke says: " + botMessage;
        }
    }


}
