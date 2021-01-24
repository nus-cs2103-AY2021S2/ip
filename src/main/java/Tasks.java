import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * Store all the tasks in a list DS
 */
public class Tasks {
    private List<Task> tasks;

    /**
     * Initialize the task list with an empty ArrayList
     */
    public Tasks() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Add the given task into the list, with necessary information printed
     * @param userInput User input is assumed to be in the format of adding new tasks
     */
    public void addTask(String userInput) {
        try {
            Task task = null;

            // It is guaranteed that either one of the three blocks will be entered
            // therefore the final value for task can never be null
            if (FormatChecker.likeAddingToDo(userInput)) {
                // may throw exception
                String content = InputInformationExtractor.getToDoContent(userInput);
                task = new ToDo(content);
            } else if (FormatChecker.likeAddingDeadline(userInput)) {
                String content = InputInformationExtractor.getDeadlineContent(userInput);
                LocalDate by = InputInformationExtractor.getDeadlineTime(userInput);
                task = new Deadline(content, by);
            } else if (FormatChecker.likeAddingEvent(userInput)) {
                String content = InputInformationExtractor.getEventContent(userInput);
                String time = InputInformationExtractor.getEventTime(userInput);
                task = new Event(content, time);
            }
            tasks.add(task);

            System.out.println("Got it. I have added this task to your list:");
            System.out.print("---- ");
            System.out.println(task);
            reportTotalNumberOfTasks();
        } catch (ToDoException e) {
            System.out.println("Sorry, the format for adding a todo task is \'todo [task name]\'. " +
                    "Please retry :')");
        } catch (DeadlineException e) {
            System.out.println("Sorry, the format for adding a deadline task is \'deadline [task name] /by time\'. " +
                    "Please retry :')");
        } catch (EventException e) {
            System.out.println("Sorry, the format for adding an event task is \'event [task name] /at time\'. " +
                    "Please retry :')");
        }
    }

    /**
     * Get the given task done, print out the result for reference
     * @param index 1-based index number referring to the specific task in the list
     */
    private void getTaskDone(int index) {
        if (tasks.size() == 0) {
            reportNoTaskRightNow();
            return;
        }
        try {
            tasks.get(index - 1).setDone(true);
            System.out.println("Nice! I have marked this task as done:");
            System.out.print("---- ");
            System.out.println(tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            reportInvalidIndexNumber();
        }
    }

    /**
     * Get the given task done, represented by the user input string
     * @param userInput Assumed to be of the format of trying to get tasks done
     */
    public void getTaskDone(String userInput) {
        try {
            int index = InputInformationExtractor.getIndexArgument(userInput);
            getTaskDone(index);
        } catch (Exception e) {
            // index argument not found
            System.out.println("Sorry, the format for getting task done is \'done [a valid task number]\'. " +
                    "Please retry :')");
        }
    }

    /**
     * Delete the given task, print out the result for reference
     * @param index 1-based index number referring to the specific task in the list
     */
    private void deleteTask(int index) {
        if (tasks.size() == 0) {
            reportNoTaskRightNow();
            return;
        }
        try {
            Task task = tasks.remove(index - 1);
            System.out.println("Noted! The task has successfully been removed.");
            System.out.print("---- ");
            System.out.println(task);
            reportTotalNumberOfTasks();
        } catch (IndexOutOfBoundsException e) {
            reportInvalidIndexNumber();
        }
    }

    /**
     * Get the given task deleted, represented by the user input string
     * @param userInput Assumed to be of the format of trying to delete a task
     */
    public void deleteTask(String userInput) {
        try {
            int index = InputInformationExtractor.getIndexArgument(userInput);
            deleteTask(index);
        } catch (Exception e) {
            // index argument not found
            System.out.println("Sorry, the format for deleting a task is \'delete [a valid task number]\'. " +
                    "Please retry :')");
        }
    }

    /**
     * Print out the list in a user-friendly format
     */
    public void printList() {
        System.out.println("These are the tasks in your list so far:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i, tasks.get(i - 1)));
        }
    }

    /**
     * Get the total number of tasks now
     * @return The total number of tasks now, including done and undone tasks
     */
    public int getTotalNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Print the report for total number of tasks, including done and undone tasks
     */
    public void reportTotalNumberOfTasks() {
        String noun = tasks.size() <= 1? "task": "tasks";
        System.out.println(String.format("Now you have %d %s in total. Good Luck.", tasks.size(), noun));
    }

    /**
     * Print the report for error message: invalid index number of the list
     */
    private void reportInvalidIndexNumber() {
        System.out.println("Sorry, the task number you specified is not valid.\n" +
                "Try enter \'list\' to see the range of task numbers you can enter.");
    }

    /**
     * Print the report for error message:
     * no task right now, no delete/get done operation can be performed
     */
    private void reportNoTaskRightNow() {
        System.out.println("Sorry, there is no task right now, please add one first :')");
    }
}