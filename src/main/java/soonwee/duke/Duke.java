package soonwee.duke;

import java.time.LocalDateTime;

/**
 * Represents a Duke instance.
 */
public class Duke {

    static final int DONE_INDEX = 5; //Read from index 5 of string.
    static final int DELETE_INDEX = 7; //Read from index 7 of string.

    /**
     * Initializes the input by checking the front command and performing
     * their required actions.
     *
     * @param input string input
     * @return a string output based on the result computed
     */
    public static String initialize(String input) {
        assert input != null : "Input is null.";
        String result;
        Storage storage = new Storage("data\\tasks.txt");
        boolean isValidForDoneOrDelete = new Parser().isValidDeleteAndDoneInput(input);
        if (input.equals("list")) {
            result = storage.getTaskList().displayTasks();
        } else if (input.startsWith("done") && isValidForDoneOrDelete) {
            int index = Integer.parseInt(input.substring(DONE_INDEX));
            result = storage.getTaskList().setTaskDone(index);
            storage.writeFile();
        } else if (input.startsWith("delete") && isValidForDoneOrDelete) {
            int index = Integer.parseInt(input.substring(DELETE_INDEX));
            result = storage.getTaskList().removeTask(index);
            storage.writeFile();
        } else if (input.startsWith("find")) {
            String text = new Parser().getSearchWord(input);
            if (text.startsWith("Invalid:")) {
                return text;
            }
            result = storage.getTaskList().searchRelatedText(text) + "\n";
        } else if (input.equals("sort")) { //Sort by date
            result = storage.getTaskList().sortTaskListByDateTime();
        } else {
            result = performTaskCheck(storage.getTaskList(), input);
            storage.writeFile();
        }
        return result;
    }

    /**
     * Performs the checking of Tasks to determine and validate what they are, and
     * create them accordingly.
     *
     * @param taskList a Task List
     * @param cmd      command input
     * @return result output
     */
    public static String performTaskCheck(TaskList taskList, String cmd) {
        assert cmd != null : "Input command is null";
        String result = new String();
        Parser checker = new Parser();
        TaskType taskType = checker.checkTaskType(cmd);
        String task = checker.checkFrontInput(cmd, taskType);
        if (!task.isEmpty()) {
            try {
                if (task.contains("OOPS!!!")) {
                    throw new DukeException(task);
                }
                if (taskType == TaskType.UNKNOWN) {
                    throw new
                            DukeException("OOPS!!! I'm sorry, " + "but I don't know what that means :-(");
                }
                if (task.trim().length() == 0) {
                    throw new DukeException("Description cannot be empty.");
                }
                result = performTaskAllocation(taskType, task, cmd, taskList);
            } catch (DukeException de) {
                result = de.getMessage();
            }
        }
        return result;
    }

    /**
     * Performs adding of respective task into TaskList.
     *
     * @param taskType      type of task
     * @param filteredInput filtered input done from the previous method
     * @param fullInput     unfiltered input
     * @param taskList      a TaskList instance
     * @return output result once the addition for respective task is performed
     */
    public static String performTaskAllocation(
            TaskType taskType, String filteredInput, String fullInput, TaskList taskList) {
        assert taskType != TaskType.UNKNOWN : "An unknown task is being allocated.";
        String result;
        Parser checker = new Parser();
        if (taskType == TaskType.TODO) {
            ToDo newToDo = new ToDo(filteredInput);
            result = performAddTask(taskList, newToDo);
        } else {
            LocalDateTime date = checker.dateFormatter(fullInput);
            if (date == null) {
                return ("Invalid Input. Please check input again");
            } else if (taskType == TaskType.DEADLINE) {
                Deadline newDeadLine = new Deadline(filteredInput, date);
                result = performAddTask(taskList, newDeadLine);
            } else {
                Event newEvent = new Event(filteredInput, date);
                result = performAddTask(taskList, newEvent);
            }
        }
        return result + taskList.printTotalTasks();
    }

    /**
     * Performs addition of task into TaskList.
     *
     * @param taskList a TaskList instance
     * @param task     a certain task
     * @return output result from the addition action
     */
    public static String performAddTask(TaskList taskList, Task task) {
        taskList.addTask(task);
        String result = "Got it. I've added this task: \n" + task.toString() + "\n";
        return result;
    }

    /**
     * Sends in the input and gets the response from the performed action.
     *
     * @param input command input
     * @return output result from the input
     */
    public String getResponse(String input) {
        return initialize(input);
    }
}
