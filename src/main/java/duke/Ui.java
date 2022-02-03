package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Ui helps Danh's Duke interact with user by calling suitable method.
 * Ui has 2 main functions: read input and return output.
 */
class Ui {
    public static final String WELCOME_MESSAGE = "Hello! I'm Park Chaeyoung\nWhat can I do for you, Lalisa Manoban?\n";
    private final Scanner input;

    /**
     * Returns an Ui with integrated Scanner.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Reads a command line entered by user and return it.
     *
     * @return the command line entered by user.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Checks if user still enter command line or not.
     *
     * @return answer in form of boolean
     */
    public boolean stillHaveCommand() {
        return input.hasNextLine();
    }

    /**
     * Says Bye
     */
    public String echoBye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints all the tasks in taskList.
     *
     * @param taskList TaskList related.
     */
    public String echoPrintList(ArrayList<Task> taskList) {
        StringBuilder ans = new StringBuilder("Here are the tasks in your list, Lisa:\n");
        int index = 1;
        for (Task task : taskList) {
            ans.append(String.format(" %d. " + task.printTask() + "\n", index));
            index++;
        }
        return ans.toString();
    }

    /**
     * Prints the results of adding a task to taskList.
     *
     * @param task      The task added
     * @param noOfTasks Number of tasks in taskList after adding.
     */
    public String echoAddToList(Task task, int noOfTasks) {
        String ans = "Got it. I've added this task for you, Lisa: \n" + " ";
        ans += task.printTask() + "\n" + String.format("Now you have %d tasks in the list.\n", noOfTasks);
        return ans;
    }

    /**
     * Prints the results of marking a Task as done.
     *
     * @param task Task done.
     */
    public String echoMarkTaskDone(Task task) {
        return "Nice! I've marked this task as done for you, Lisa: \n" + " " + task.printTask() + "\n";
    }

    /**
     * Prints the results of deleting a Task.
     *
     * @param task Task deleted.
     */
    public String echoDeleteTask(Task task) {
        return "Noted. I've removed this task for you, Lisa: \n" + " " + task.printTask() + "\n";
    }

    /**
     * Prints the error message of a DukeException.
     *
     * @param err DukeException object related.
     */

    public String echoErrMsg(DukeException err) {
        return err.getMessage();
    }

    /**
     * Prints all the tasks of a specific day.
     *
     * @param taskList The tasklist related.
     * @param dateTime The day that we want to search for.
     */
    public String echoTaskThisDay(ArrayList<Task> taskList, LocalDateTime dateTime) {
        StringBuilder ans = new StringBuilder("Here are the tasks on " + dateTime.toString().substring(0, 10) + ":\n");
        int index = 1;
        for (Task task : taskList) {
            boolean isDeadlineThatDay = task instanceof Deadline
                    && sameDay(((Deadline) task).getDeadlineTime(), dateTime);
            boolean isEventThatDay = task instanceof Event && sameDay(((Event) task).getEventTime(), dateTime);
            if (isDeadlineThatDay || isEventThatDay) {
                ans.append(String.format(" %d. " + task.printTask() + "\n", index));
                index++;
            }
        }
        return ans.toString();
    }

    /**
     * Prints all the tasks of Today.
     *
     * @param taskList The tasklist related.
     * @return output of this scho
     */
    public String echoTaskToday(ArrayList<Task> taskList) {
        StringBuilder ans = new StringBuilder("Here are the tasks today, Lisa:\n");
        int index = 1;
        for (Task task : taskList) {
            boolean isDeadlineToday = task instanceof Deadline && sameDay
                    (((Deadline) task).getDeadlineTime(), LocalDateTime.now());
            boolean isEventToday = task instanceof Event && sameDay(((Event) task).getEventTime(), LocalDateTime.now());
            boolean isToDoToday = task instanceof ToDo && !task.isTaskDone();
            if (isDeadlineToday || isEventToday || isToDoToday) {
                ans.append(String.format(" %d. " + task.printTask() + "\n", index));
                index++;
            }
        }
        return ans.toString();
    }

    /**
     * Prints all the tasks that match the pattern of a find command.
     *
     * @param taskList The taskList related.
     * @param pattern  The String pattern given by find command.
     */
    public String echoPrintFindResult(ArrayList<Task> taskList, String pattern) {
        StringBuilder ans = new StringBuilder("Here are the matching tasks, Lisa:\n");
        int index = 1;
        for (Task task : taskList) {
            if (task.getTaskName().contains(pattern)) {
                ans.append(String.format(" %d. " + task.printTask() + "\n", index));
                index++;
            }
        }
        return ans.toString();
    }

    /**
     * Prints all the deadlines coming up.
     *
     * @param taskList The taskList related.
     * @return output of this scho
     */
    public String echoReminder(ArrayList<Task> taskList) {
        StringBuilder ans = new StringBuilder("Here are the upcoming deadlines, Lisa:\n");
        ArrayList<Deadline> deadlineList = new ArrayList<>();
        for (Task task : taskList) {
            boolean isDeadlineAvailable = (task instanceof Deadline)
                    && ((Deadline) task).getDeadlineTime().isAfter(LocalDateTime.now());
            if (isDeadlineAvailable && !task.isTaskDone()) {
                addDeadline(deadlineList, (Deadline) task);
            }
        }
        for (Deadline deadline : deadlineList) {
            ans.append(String.format(" %d. " + deadline.printTask() + "\n", deadlineList.indexOf(deadline) + 1));
        }
        return ans.toString();
    }

    /**
     * (Helper function) Adds a deadline to a deadline list in the correct chronological order.
     *
     * @param deadlineList the deadline list related.
     * @param deadline     the deadline to add.
     */
    private void addDeadline(ArrayList<Deadline> deadlineList, Deadline deadline) {
        int insertIndex = 0;
        for (Deadline dl : deadlineList) {
            if (dl.getDeadlineTime().isBefore(deadline.getDeadlineTime())) {
                insertIndex++;
            } else {
                break;
            }
        }
        deadlineList.add(insertIndex, deadline);
    }

    /**
     * (Helper function) Checks if 2 dateTime refers to the same day or not.
     *
     * @param dateTime1 First dateTime input
     * @param dateTime2 Second dateTime input
     * @return answer in form of boolean.
     */
    public boolean sameDay(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return ((dateTime1.getDayOfYear() == dateTime2.getDayOfYear()) && (dateTime1.getYear() == dateTime2.getYear()));
    }
}
