package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;



/**
 * TaskList class contains a list in which stores all the tasks that the user has inputted and not deltted
 */
public class TaskList {

    /** The list of tasks */
    private List<Task> inputList = new ArrayList<>();
    /**
     * TaskList constructor to intialize a new empty TaskList
     */
    public TaskList() {
        this.inputList = new ArrayList<>();
    }

    /**
     * TaskList constructor to intialize a new TaskList with a given ArrayList of tasks
     * the tasks are all stored as string format which is processed before being
     * added to the current task list
     */
    public TaskList(ArrayList<String> tasks) {

        for (String taskStr: tasks) {
            String[] arr = taskStr.split("\\|");

            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i].trim();
            }

            if (arr[0].equals("D")) {
                String[] deadline = arr[3].split("-");
                String reformattedDeadline = deadline[2] + "-" + deadline[1] + "-" + deadline[0];

                DeadlineTask deadlineTask = new DeadlineTask(arr[2], reformattedDeadline);

                if (arr[1].equals("1")) {
                    deadlineTask.markAsDone();
                }

                inputList.add(deadlineTask);

            } else if (arr[0].equals("E")) {
                String[] timing = arr[3].split("-");
                String reformattedTiming = timing[2] + "-" + timing[1] + "-" + timing[0];

                EventTask eventTask = new EventTask(arr[2], reformattedTiming);

                if (arr[1].equals("1")) {
                    eventTask.markAsDone();
                }

                inputList.add(eventTask);

            } else {
                ToDoTask toDoTask = new ToDoTask(arr[2]);

                if (arr[1].equals("1")) {
                    toDoTask.markAsDone();
                }

                inputList.add(toDoTask);
            }
        }
    }

    /**
     * Returns The list of tasks.
     *
     * @return The task list
     */
    public List<Task> getList() {
        return this.inputList;
    }

    /**
     * Adds a task to the task list
     *
     * @param task A task
     */
    public void add(Task task) {
        this.inputList.add(task);
    }

    /**
     * Returns The task inside the task list at the index specified
     *
     * @param index index of task inside the tasklist
     * @return A task
     */
    public Task get(int index) {
        return this.inputList.get(index);
    }

    /**
     * Returns a ToDoTask based on the user input and adds it to the list
     *
     * @param action  The string action input by the user
     * @return A ToDoTask
     */
    public ToDoTask handleToDoTask(String action) {

        int index = action.indexOf(" ");
        String description = action.substring(index + 1);

        ToDoTask toDoTask = new ToDoTask(description);

        this.add(toDoTask);

        return toDoTask;
    }
    /**
     * Returns a EventTask based on the user input and adds it to the list
     *
     * @param action  The string action input by the user
     * @return A EventTask
     */
    public EventTask handleEventTask(String action) {

        int actionIndex = action.indexOf(" ");
        int descriptionIndex = action.indexOf("/");

        String description = action.substring(actionIndex + 1, descriptionIndex - 1);
        String event = action.substring(descriptionIndex + 4);

        EventTask eventTask = new EventTask(description, event);

        this.add(eventTask);

        return eventTask;
    }

    /**
     * Returns a DeadLineTask based on the user input and adds it to the list
     *
     * @param action  The string action input by the user
     * @return A DeadLineTask
     */
    public DeadlineTask handleDeadlineTask(String action) {
        int actionIndex = action.indexOf(" ");
        int descriptionIndex = action.indexOf("/");
        String description = action.substring(actionIndex + 1, descriptionIndex - 1);
        String deadline = action.substring(descriptionIndex + 4);

        DeadlineTask deadlineTask = new DeadlineTask(description, deadline);

        this.add(deadlineTask);

        return deadlineTask;
    }

    /**
     * Returns A task that is mark as done
     * The task at the specified index is
     * marked as done before being returned
     *
     * @param index index of task in list
     * @return Task that is marked
     */
    public Task handleDone(int index) {
        Task markDone = this.inputList.get(index - 1);

        markDone.markAsDone();

        return markDone;
    }

    /**
     * Returns the task that was at specified index is now removed.
     *
     * @param index index of Task in list
     * @return The task that is removed
     */
    public Task handleDelete(int index) {
        Task task = this.inputList.remove(index - 1);

        return task;
    }

    /**
     * Returns The String that will be used to write into the file.
     *
     * @return A string to be written into the file.
     */
    public String getListToWrite() {

        String result = "";

        for (int i = 0; i < inputList.size(); i++) {
            Task task = inputList.get(i);
            char type = task.getType();

            String status = task.getStatusIcon().equals(" ") ? "0" : "1";
            String description = task.getDescription();
            String date = "";

            if (type == 'D') {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                date = deadlineTask.getUnformattedDeadline();

            } else if (type == 'E') {

                EventTask eventTask = (EventTask) task;
                date = eventTask.getUnformattedTiming();;
            }

            result += type + " | " + status + " | " + description;

            if (date.equals("")) {
                result += "\n";
            } else {
                result += " | " + date + "\n";
            }
        }

        return result;
    }

    /**
     * Returns a String containing the tasks that have this date
     *
     * @param date the date to search for
     * @return A string of tasks to be printed
     */
    public String findOnDateTasks(String date) {

        LocalDate toSearch = LocalDate.parse(date);
        String result = "Here are the tasks due on the date: \n";

        for (Task task: inputList) {
            if (task.getType() == 'D') {

                DeadlineTask deadlineTask = (DeadlineTask) task;

                if (deadlineTask.getDeadlineAsLocalDate().equals(toSearch)) {
                    result += deadlineTask + "\n";
                }

            } else if (task.getType() == 'E') {

                EventTask eventTask = (EventTask) task;

                if (eventTask.getTimingAsLocalDate().equals(toSearch)) {
                    result += eventTask + "\n";
                }
            }
        }

        return result;
    }
    /**
     * Returns a List of tasks that match the keyword inputted
     *
     * @param keyword the keyword to search for in the tasks
     * @return the list of tasks which contain that keyword
     */
    public List<Task> getMatch(String keyword) {
        List<Task> matchList = new ArrayList<>();

        for (Task task: inputList) {
            if (task.getDescription().contains(keyword)) {
                matchList.add(task);
            }
        }

        return matchList;
    }
}
