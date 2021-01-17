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
     * Add the given todo item into the list, with necessary information printed
     * @param userInput User input is assumed to be in todo format
     */
    public void addTodo(String userInput) {
        System.out.println("Got it. I have added this task to your list: ");
        String content = InputInformationExtractor.getToDoContent(userInput);
        ToDo todo = new ToDo(content);
        tasks.add(todo);
        System.out.print("---- ");
        System.out.println(todo);
        reportTotalNumberOfTasks();
    }

    /**
     * Add the given deadline item into the list, with necessary information printed
     * @param userInput User input is assumed to be in deadline format
     */
    public void addDeadLine(String userInput) {
        System.out.println("Got it. I have added this task to your list: ");
        String content = InputInformationExtractor.getDeadlineContent(userInput);
        String by = InputInformationExtractor.getDeadlineTime(userInput);
        Deadline deadline = new Deadline(content, by);
        tasks.add(deadline);
        System.out.print("---- ");
        System.out.println(deadline);
        reportTotalNumberOfTasks();
    }

    /**
     * Add the given event item into the list, with necessary information printed
     * @param userInput User input is assumed to be in event format
     */
    public void addEvent(String userInput) {
        System.out.println("Got it. I have added this task to your list: ");
        String content = InputInformationExtractor.getEventContent(userInput);
        String time = InputInformationExtractor.getEventTime(userInput);
        Event event = new Event(content, time);
        tasks.add(event);
        System.out.print("---- ");
        System.out.println(event);
        reportTotalNumberOfTasks();
    }

    /**
     * Get the given task done, print out the result for reference
     * @param index 1-based index number referring to the specific task in the list
     */
    public void getTaskDone(int index) {
        try {
            tasks.get(index - 1).setDone(true);
            System.out.println("Nice! I have marked this task as done: ");
            System.out.print("---- ");
            System.out.println(tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, the task number you specified is not valid. " +
                    "Try enter \'list\' to see the range of task numbers you can enter.");
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