package soonwee.duke;

import java.time.LocalDateTime;

/**
 * Represents a Duke instance.
 */
public class Duke {

    /**
     * Initializes the input by checking the front command and performing
     * their required actions.
     *
     * @return updated Task List
     */
    public static String initialize(String input) {
        assert input != null : "Input is null.";
        String result = new String();
        Storage storage = new Storage("data\\tasks.txt");
        if (input.equals("list")) {
            result = storage.taskList.displayTasks();
        } else if (input.contains("done")) {
            int index = Integer.parseInt(input.substring(5));
            result = storage.taskList.setTaskDone(index);
            storage.writeFile();
        } else if (input.contains("delete")) {
            int index = Integer.parseInt(input.substring(7));
            result = storage.taskList.removeTask(index);
            storage.writeFile();
        } else if (input.contains("find")) {
            String text = new Parser().getSearchWord(input);
            if(text.contains("Invalid:")) {
                return text;
            }
            result = result + storage.taskList.searchRelatedText(text) + "\n";
        } else {
            result = performTaskCheck(storage.taskList, input);
            storage.writeFile();
        }
        return result;
    }

    /**
     * Performs the checking of Tasks to determine and validate what they are, and
     * create them accordingly.
     *
     * @return updated Task List
     */
    public static String performTaskCheck(TaskList taskList, String cmd) {
        assert cmd != null: "Input command is null";
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
                } else {
                    if (taskType == TaskType.TODO) {
                        ToDo newToDo = new ToDo(task);
                        result = result + performAddTask(taskList, newToDo);
                    } else {
                        LocalDateTime date = checker.dateFormatter(cmd);
                        if (taskType == TaskType.DEADLINE) {
                            Deadline newDeadLine = new Deadline(task, date);
                            result = result + performAddTask(taskList, newDeadLine);
                        } else {
                            Event newEvent = new Event(task, date);
                            result = result + performAddTask(taskList, newEvent);
                        }
                    }
                    result = result + taskList.printTotalTasks();
                }
            } catch (DukeException de) {
                result = result + de.getMessage();
            }
        }
        return result;
    }

    /**
     * Performs addition of task into TaskList.
     */
    public static String performAddTask(TaskList taskList, Task task) {
        taskList.addTask(task);
        String result = new String();
        result = result + "Got it. I've added this task: \n" + task.toString() + "\n";
        return result;
    }

    /**
     * Sends in the input and gets the response from the performed action.
     *
     * @param input command input
     * @return output result from the input
     */
    public String getResponse(String input) {
        String result = new String();
        result = result + initialize(input);
        return result;
    }
}