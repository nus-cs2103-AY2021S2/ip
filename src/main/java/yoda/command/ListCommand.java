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

    /**
     * Filters tasks in the TaskList matching the keyword given and puts them in another TaskList.
     * @param keyword Keyword used to filter tasks.
     * @param taskList TaskList from which tasks are filtered.
     * @return TaskList containing the tasks filtered by keyword.
     * @throws InvalidKeywordException If keyword given by user is too short or more than one word.
     */
    public TaskList findByKeyword(String keyword, TaskList taskList) throws InvalidKeywordException {
        assert taskList != null;
        if (keyword.length() <= 2) {
            throw new InvalidKeywordException("A valid keyword containing more than 2 characters, "
                    + "you must enter!");
        }
        if (details.length != 2) {
            throw new InvalidKeywordException("Only one KEYWORD, you must enter!");
        }
        return taskList.filterByTask(keyword);
    }

    /**
     * Lists tasks based on the information given by user.
     * @param taskList TaskList associated with the command being executed.
     * @param ui Ui associated with the command being executed.
     * @param storage Storage associated with the command being executed.
     * @return Message containing the list of tasks requested by user.
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
            }
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
