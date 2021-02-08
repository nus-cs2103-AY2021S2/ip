package lihua.tasks;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import lihua.commons.enums.ListTagCode;
import lihua.commons.comparators.TimeTaskComparatorChronological;
import org.json.simple.JSONArray;

/**
 * Store all the tasks in a list DS
 */
public class Tasks {
    private final List<Task> tasks;

    /**
     * Initializes the task list with an empty ArrayList
     */
    public Tasks() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task object to be added to the list.
     */
    public void addTask(Task t) {
        tasks.add(t);
        Collections.sort(tasks);
    }

    /**
     * Removes the task from the list at a specific index.
     *
     * @param i The index of the task to be deleted.
     * @return The task that gets deleted.
     * @throws IndexOutOfBoundsException If the i specified is invalid for the list.
     */
    public Task removeTask(int i) throws IndexOutOfBoundsException {
        // the sorted  order will be maintained, if an item is
        return tasks.remove(i - 1);
    }

    /**
     * Retrieves the task from the list at a specific index.
     *
     * @param i The index of the task to be retrieved.
     * @return The task at index i.
     * @throws IndexOutOfBoundsException If the i specified is invalid for the list.
     */
    public Task getTaskDone(int i) throws IndexOutOfBoundsException {
        tasks.get(i - 1).setDone(true);
        return tasks.get(i - 1);
    }

    /**
     * Finds tasks by a given keyword.
     *
     * @param keyWord The keyword specified.
     * @return A readable string representation of all tasks whose name contain the keyword as part of the substring.
     */
    public String listTasks(String keyWord) {
        String message = "";
        assert tasks != null;
        for (int i = 1; i <= tasks.size(); i++) {
            boolean doesTaskNameContainKeyword = tasks.get(i - 1).getName()
                    .toLowerCase().contains(keyWord.toLowerCase());
            if (doesTaskNameContainKeyword) {
                message += String.format("%d. %s\n", i, tasks.get(i - 1));
            }
        }
        if (message.equals("")) {
            message = String.format("Tasks containing '%s' do not exist :')", keyWord);
        } else {
            message = String.format("Here are all your tasks containing '%s':\n%sGood luck. :D", keyWord, message);
        }
        assert !message.equals("");
        return message;
    }

    /**
     * Lists down the content of the list in a readable string manner.
     *
     * @param date The date on which the tasks is expected to be finished.
     * @return If the specified date is null, returns the string representation for all tasks;
     * if the specified date is not null, returns the string representation of all the tasks on the date.
     */
    public String listTasks(LocalDate date, ListTagCode code) {
        assert tasks != null;
        List<? extends Task> validTasks = getValidTasks(date); // only sorted by name, by default
        List<? extends Task> sortedTasks = sortTasksAccordingToCodeIfAppicable(validTasks, code);

        String message = constructMessage(sortedTasks);

        return message;
    }

    private String constructMessage(List<? extends Task> sortedTasks) {
        String message = "";
        for (int i = 1; i <= sortedTasks.size(); i++) {
            message += String.format("%d. %s\n", i, sortedTasks.get(i - 1));
        }
        if (message.equals("")) {
            message = "You do not have any task right now. Please add one first. :')";
        } else {
            message = "Here are all your tasks:\n" + message + "Good luck. :D";
        }
        assert !message.equals("");
        return message;
    }

    private List<? extends Task> sortTasksAccordingToCodeIfAppicable(List<? extends Task> validTasks, ListTagCode code) {
        switch (code) {
            case NORMAL:
                return validTasks;
            case TIME_NORMAL:
                // we only consider TimeTask here
                List<TimeTask> timeTasks = getTimeTasks(validTasks);
                timeTasks.sort(new TimeTaskComparatorChronological());
                return timeTasks;
        }
        assert false;
        return null;
    }

    private List<TimeTask> getTimeTasks(List<? extends Task> validTasks) {
        List<TimeTask> timeTasks = validTasks.stream()
                .filter(t -> t instanceof TimeTask).map(t -> (TimeTask) t).collect(Collectors.toList());
        return timeTasks;
    }

    private List<Task> getValidTasks(LocalDate date) {
        assert tasks != null;
        List<Task> validTasks = tasks.stream()
                .filter(t -> {
                    if (date == null) {
                        return true;
                    }
                    boolean isTaskOnTheDaySpecified = date.equals(t.getDate());
                    if (isTaskOnTheDaySpecified) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        return validTasks;
    }

    /**
     * Wraps the task list object inside a json array to be stored in hard disk.
     *
     * @return The json array representing the task list.
     */
    public JSONArray getJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasks) {
            jsonArray.add(t.toJsonObject());
        }
        assert jsonArray != null;
        return jsonArray;
    }

    /**
     * Gets the current size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }
}
