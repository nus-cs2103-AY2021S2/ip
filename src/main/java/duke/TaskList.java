package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.constants.Priority;
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

            assert arr.length > 1 : "TaskList tasks not loaded properly or has issue";
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i].trim();
            }

            boolean isDeadlineTask = arr[0].equals("D");
            boolean isEventTask = arr[0].equals("E");
            boolean isToDoTask = arr[0].equals("T");
            boolean taskIsDone = arr[1].equals("1");

            String description = arr[2];

            if (isDeadlineTask) {
                String priority = arr[4];
                String reformattedDeadline = reformatDate(arr[3]);

                DeadlineTask deadlineTask = new DeadlineTask(description, reformattedDeadline, priority);

                if (taskIsDone) {
                    deadlineTask.markAsDone();
                }

                inputList.add(deadlineTask);

            } else if (isEventTask) {
                String reformattedDate = reformatDate(arr[3]);
                String priority = arr[4];
                EventTask eventTask = new EventTask(description, reformattedDate, priority);

                if (taskIsDone) {
                    eventTask.markAsDone();
                }

                inputList.add(eventTask);

            } else if (isToDoTask) {

                String priority = arr[3];
                ToDoTask toDoTask = new ToDoTask(description, priority);

                if (taskIsDone) {
                    toDoTask.markAsDone();
                }

                inputList.add(toDoTask);
            }
        }
    }

    /**
     * Function to reformat the date before printing
     *
     * @param date the date in string to be reformatted
     * @return String containing the reformatted date
     */
    public String reformatDate(String date) {
        String[] brokenDate = date.split("-");
        String reformattedDate = brokenDate[2] + "-" + brokenDate[1] + "-" + brokenDate[0];

        return reformattedDate;
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
    public String getListToString() {

        String result = "";

        for (int i = 0; i < inputList.size(); i++) {
            Task task = inputList.get(i);
            char type = task.getType();

            String status = task.getStatusIcon().equals(" ") ? "0" : "1";
            String description = task.getDescription();
            
            String date = "";
            
            String priority = task.getPriority().getValue().toLowerCase();

            if (type == 'D') {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                date = deadlineTask.getUnformattedDeadline();

            } else if (type == 'E') {

                EventTask eventTask = (EventTask) task;
                date = eventTask.getUnformattedTiming();;
            }

            result += type + " | " + status + " | " + description;

            if (!date.equals("")) {
                result += " | " + date;
            }
            result += " | " + priority + "\n";
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
    
    /**
     * Returns a Task who priority has been changed
     *
     * @param number the task number in the list
     * @return the priority of the task that one wishes to set
     */
    public Task handleSetPriority(int number, String priority) {
        Task task = this.inputList.get(number - 1);
        Priority priorityToBeSet = Priority.UNASSIGNED;
        
        if (priority.equals("high")) {
            priorityToBeSet = Priority.HIGH;
        } else if (priority.equals("medium")) {
            priorityToBeSet = Priority.MEDIUM;
        } else if (priority.equals("low")) {
            priorityToBeSet = Priority.LOW;
        }
        
        task.setPriority(priorityToBeSet);
        
        return task;
    }
}
