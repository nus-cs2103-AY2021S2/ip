import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Represents all the tasks the user has input.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Sets tasks ArrayList to the list of tasks that user has already input previous time Duke was used.
     *
     * @param prevTasks list of tasks that user has already input previous time Duke was used.
     */
    public static void setList(ArrayList<Task> prevTasks) {
        tasks = prevTasks;
    }

    /**
     * Returns an ArrayList of the tasks so far.
     *
     * @return ArrayList of tasks so far.
     */
    public static ArrayList<Task> getList() {
        //prob with this is can change value as pass by ref
        return tasks;
    }

    /**
     * Returns size of list of tasks.
     *
     * @return int size of list of tasks.
     */
    public static int getListSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task task to be added to list of tasks.
     */
    public static void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a String representation of all the tasks in the list of tasks.
     *
     * @return String of all the tasks in the list of tasks.
     */
    public static String printList() {
        return Ui.printList(tasks);
    }

    /**
     * Sets the task at index number to be complete and returns that task.
     *
     * @param number task number in the list that is completed.
     * @return Task task that has been completed.
     * @throws IndexOutOfBoundsException if num is not in the range of 0 to tasks list size or tasks list size is zero.
     */
    public static Task doneTask(int number) {
        if (tasks.size() == 0) {
            throw new IndexOutOfBoundsException(Ui.noTasksToBeDone());
        } else if (number < 0 || number >= tasks.size()) {
            throw new IndexOutOfBoundsException(Ui.doneTaskOutOfRange());
        }
        tasks.get(number).setDone();
        return tasks.get(number);
    }

    /**
     * Deletes a task at index number and returns that task.
     *
     * @param number task number in the list that is deleted.
     * @return Task task that has been deleted.
     * @throws IndexOutOfBoundsException if num is not in the range of 0 to tasks list size or tasks list size is zero.
     */
    public static Task deleteTask(int number) {
        if (tasks.size() == 0) {
            throw new IndexOutOfBoundsException(Ui.noTasksToBeDelete());
        } else if (number < 0 || number >= tasks.size()) {
            throw new IndexOutOfBoundsException(Ui.deleteTaskOutOfRange());
        }
        return tasks.remove(number);
    }

    //try use hmap? so like each time we add a  task, we are mapping each word of the task to the idx of occurence in
    //the list,then in the harddrive can have a section for hmap and like each word and the idxes separated by a comma.
    //can be done, may be good in long term, but is painful to code, may take time, which i dont got too much of
    /**
     * Returns the String of all the tasks that contain the keyword
     *
     * @param keyword word to be used to search the task list
     * @return String of all the tasks that contain the keyword
     */
    public static String findTask(String keyword) {
        keyword = keyword.trim();
        String[] split = keyword.split(" ");
        if (split.length > 1 || keyword.equals("")) {
            throw new IllegalArgumentException(Ui.findFormat());
        }
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            String[] split1 = task.split(" ");
            for (int j = 0; j < split1.length; j++) {
                if (keyword.equals(split1[j])) {
                    int count = i + 1;
                    result = result + " " + count + ". " + task + "\n";
                }
            }
        }
        if (result.equals("")) {
            return Ui.foundNoMatchingTasks();
        } else {
            return Ui.foundMatchingTasks() + result + Ui.lineGetter();
            //can try make this better
        }
    }

    public static String getReminders() {
        String reminders = "";
        LocalDateTime dateTimeNow = LocalDateTime.now();
        LocalDateTime dateTimeOneWeekAfter = dateTimeNow.plusWeeks(1);

        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            if (task instanceof Event) {
                Event event = (Event)task;
                LocalDateTime eventTime = event.getEvent();
                boolean withinRange = eventTime.isAfter(dateTimeNow) &&
                        eventTime.isBefore(dateTimeOneWeekAfter);
                if (withinRange) {
                    reminders = reminders + " " + i + "." + event + "\n";
                }
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDateTime deadlineTime = deadline.getDeadline();
                boolean withinRange = deadlineTime.isAfter(dateTimeNow) &&
                        deadlineTime.isBefore(dateTimeOneWeekAfter);
                if (withinRange) {
                    reminders = reminders + " " + i + "." + deadline + "\n";
                }
            }
        }

        if (reminders.length() == 0) {
            reminders += " No tasks due in a week!\n";
        }

        return reminders;
    }
}
