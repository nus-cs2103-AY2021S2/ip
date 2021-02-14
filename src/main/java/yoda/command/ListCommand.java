package yoda.command;

import yoda.storage.Storage;
import yoda.task.TaskList;
import yoda.ui.Ui;

/**
 * ListCommand class that handles listing out the tasks in the TaskList
 * and a child of the Command class.
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand object.
     * @param details Details of ListCommand object.
     */
    public ListCommand(String[] details) {
        super(details);
    }

    public TaskList findByKeyword(String keyword, TaskList taskList) throws InvalidKeywordException {
        assert taskList != null;
        if (keyword.length() <= 2) {
            throw new InvalidKeywordException("A valid keyword containing more than 2 characters, "
                    + "you must enter!");
        }
        return taskList.filterByTask(keyword);
    }
    /**
     * Lists tasks based on the information given by the user.
     * @param taskList TaskList associated with the ListCommand being executed.
     * @param ui Ui associated with the ListCommand being executed.
     * @param storage Storage associated with the ListCommand being executed.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (details[0].equals("FIND")) {
            try {
                TaskList keywordList = findByKeyword(details[1], taskList);
                return ui.printTasks("find", keywordList.toString(), keywordList.getTaskListSize());
            } catch (InvalidKeywordException e) {
                return e.getMessage();
            }
        } else {
            if (details.length == 1) {
                return ui.printTasks("all", taskList.toString(), taskList.getTaskListSize());
            } else {
                switch (details[1]) {
                case "-d":
                    TaskList deadlineList = taskList.filterByTask("Deadline");
                    return ui.printTasks("deadline", deadlineList.toString(), deadlineList.getTaskListSize());
                case "-e":
                    TaskList eventList = taskList.filterByTask("Event");
                    return ui.printTasks("event", eventList.toString(), eventList.getTaskListSize());
                case "-t":
                    TaskList todoList = taskList.filterByTask("ToDo");
                    return ui.printTasks("todo", todoList.toString(), todoList.getTaskListSize());
                default:
                    return ui.printTasks("bad", "", 0);
                }
            }
        }
    }
}
