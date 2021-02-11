package chat.command;

import chat.*;
import chat.task.Task;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A type of command that finds tasks based on keywords given by user
 */
public class FindCommand extends Command {
    
    String inputStr;

    /**
     * Initialises FindCommand object.
     * 
     * @param inputStr Inputted command string from user to Chat the Cat.
     */
    public FindCommand(String inputStr) {
        assert inputStr != "";
        this.inputStr = inputStr;
    }

    /**
     * Method that finds tasks from list of tasks from TaskList object.
     * 
     * @param taskList TaskList object that contains a list of tasks.
     * @param ui Ui object that gives responses to user.
     * @param storage Storage object that interacts with task data in hard disk.
     * @throws ChatException If no tasks match keyword given by user.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatException {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        
        String keyword = this.inputStr.replace("find","").strip();
        TaskList foundTaskList = getFoundTaskList(keyword, taskList);
        if (foundTaskList.getTasks().isEmpty()) {
            throw new ChatException("Sorry! No results found.");
        } 
        ui.list(foundTaskList);
    }

    /**
     * Given a particular keyword, this function returns a TaskList object containing list of tasks 
     * that have that keyword in it's name.
     * <p>Keyword should follow format: taskType,name,isDone,dates.</p>
     * <pParameters in keyword can be left black if user wishes to search by only a few parameters.</p>
     * <p>Task type can be indicated by "T" (todo), "D" (deadline) or "E" (event).</p>
     * <p>Dates should be inputted with format dd/MM/YYYY or dd/MM or MM/YYYY.</p>
     * <p>Dates should be separated with a space.</p>
     * <p></p>
     * <p>Some examples include:</p>
     * <p>find T --> lists all todo tasks</p>
     * <p>find ,return,T --> lists all tasks whose name includes the term 'return' and are not done</p>
     * <p>find ,,,11/02 12/02 --> lists all tasks with start date 11/02 and end date 12/02</p>
     * 
     * @param keyword Find tasks based on this keyword.
     * @param taskList TaskList object containing all tasks.
     * @return TaskList object containing list of tasks that fit the keyword.
     */
    public TaskList getFoundTaskList(String keyword, TaskList taskList) throws ChatException {
        assert taskList != null;
        assert keyword != "";

        String formatStr = "Please find with format:\n" +
                "find taskType,name,isDone,dates\n\n" +
                "Parameters can be left blank if you wish to only search by a few parameters.\n" +
                "Dates should also be inputted with format dd/MM/YYYY or dd/MM or MM/YYYY.\n" +
                "Dates should be separated with a space.\n" +
                "* i.e. find T --> lists all todo tasks\n" +
                "* i.e. find ,return,T --> lists all tasks whose name includes the term 'return'" +
                "and are not done\n" +
                "* i.e. find ,,,11/02 12/02 --> lists all tasks with start date 11/02 and end date 12/02\n";

        try {
            String keyWord = this.inputStr.replace("find", "").strip();
            String[] keys = keyWord.split(",");

            TaskList foundTaskList = new TaskList(new ArrayList<Task>());
            for (Task task : taskList.getTasks()) {
                if (isFound(task, keys)) {
                    foundTaskList.getTasks().add(task);
                }
            }
            return foundTaskList;
        } catch (ChatException e) {
            throw new ChatException(e.getMessage() + formatStr);
        }
    }

    /**
     * A method that checks if matching task is found.
     * 
     * @param task Task being checked.
     * @param keys Array of parameters derived from keyword given by user.
     * @return Boolean indicating if matching task is found.
     * @throws ChatException Error is thrown if there are too many search parameters given by keys.
     */
    public boolean isFound(Task task, String ...keys) throws ChatException {
        if (keys.length > 4) {
            throw new ChatException("Too many search parameters.\n");
        }
        
        for (int i = 0; i < keys.length; i++) {
            switch (i) {
            case 0:
                if (!isMatchingTaskType(task, keys[0])) {
                    return false;
                }
                break;
            case 1:
                if (!isMatchingName(task, keys[1])) {
                    return false;
                }
                break;
            case 2:
                if (!isMatchingDoneStatus(task, keys[2])) {
                    return false;
                }
                break;
            case 3:
                if (!isMatchingDates(task, keys[3])) {
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    private static boolean isMatchingTaskType(Task task, String taskType) {
        if (!taskType.isEmpty()) {
            if (!Character.toString(task.allParameterStr().charAt(0)).equals(taskType)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isMatchingName(Task task, String name) {
        if (!task.getName().contains(name)) {
            return false;
        }
        return true;
    }
    
    private static boolean isMatchingDoneStatus(Task task, String doneStatusStr) {
        if (doneStatusStr != "") {
            if (doneStatusStr.equals("T") && !task.getIsDone()) {
                return false;
            } else if (doneStatusStr.equals("F") && task.getIsDone()) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isMatchingDates(Task task, String dateStr) {
        String[] dates = dateStr.split(" ");
        for (String date : dates) {
            if (!task.allParameterStr().contains(date)) {
                return false;
            }
        }
        return true;
    }
    
}
