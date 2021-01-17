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
        System.out.println("Got it. I have added this task to your list:");
        Task task = null;

        // It is guaranteed that either one of the three blocks will be entered
        // therefore the final value for task can never be null
        if (FormatChecker.isToDoFormat(userInput)) {
            String content = InputInformationExtractor.getToDoContent(userInput);
            task = new ToDo(content);
        } else if (FormatChecker.isDeadLineFormat(userInput)) {
            String content = InputInformationExtractor.getDeadlineContent(userInput);
            String by = InputInformationExtractor.getDeadlineTime(userInput);
            task = new Deadline(content, by);
        } else if (FormatChecker.isEventFormat(userInput)) {
            String content = InputInformationExtractor.getEventContent(userInput);
            String time = InputInformationExtractor.getEventTime(userInput);
            task = new Event(content, time);
        }
        tasks.add(task);
        System.out.print("---- ");
        System.out.println(task);
        reportTotalNumberOfTasks();
    }

    /**
     * Get the given task done, print out the result for reference
     * @param index 1-based index number referring to the specific task in the list
     */
    private void getTaskDone(int index) {
        try {
            tasks.get(index - 1).setDone(true);
            System.out.println("Nice! I have marked this task as done:");
            System.out.print("---- ");
            System.out.println(tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, the task number you specified is not valid.\n" +
                    "Try enter \'list\' to see the range of task numbers you can enter.");
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
}