import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<String> myTasks) {
        this.taskList = initialiseList(myTasks);
    }

    private boolean isDone(String icon){
        if (icon.equals("\u2713")){
            return true;
        }
        return false;
    }

    private ArrayList<Task> initialiseList(ArrayList<String> myTasks) {
        ArrayList<Task> taskList = new ArrayList<>();

        // loop through every task in the list
        for (String s : myTasks) {
            String[] taskByParts = s.split(" \\| ", 3);
            String type = taskByParts[0];

            // check if task type is ToDo
            if (type.equals("T")) {

                // specify details of ToDo
                boolean isCompleted = isDone(taskByParts[1]);
                String description = taskByParts[2];
                Task newTask = new ToDo(description, isCompleted);

                // add ToDo to list
                taskList.add(newTask);
            }

            // check if task type is Deadline
            if (type.equals("D")) {

                // specify details of Deadline
                boolean isCompleted = isDone(taskByParts[1]);
                String[] details = taskByParts[2].split(" \\| ", 2);
                String description = details[0];
                String time = details[1];

                // convert time from String in "yyyy-M-dd H:mm" format to LocalDateTime in "MMM d yyyy hh:mm a" format
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
                String by = dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-dd H:mm"));

                // create new Deadline
                Task newTask = new Deadline(description, by, isCompleted);
                taskList.add(newTask);
            }

            // check if task type is Event
            if (type.equals("E")) {

                // specify details of Event
                boolean isCompleted = isDone(taskByParts[1]);
                String[] details = taskByParts[2].split(" \\| ", 2);
                String description = details[0];
                String time = details[1];

                // convert time from String in "yyyy-M-dd H:mm" format to LocalDateTime in "MMM d yyyy hh:mm a" format
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
                String by = dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-dd H:mm"));

                // create new Event
                Task newTask = new Event(description, by, isCompleted);
                taskList.add(newTask);
            }
        }
        return taskList;
    }

    /**
     * Return the size of a TaskList.
     *
     * @return Number of tasks in the TaskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Return the task by its order number in a TaskList.
     *
     * @param taskNumber Order number of the task of interest.
     * @return Task of specified order number.
     */
    public Task getTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber);
        return task;
    }

    /**
     * Return the list of tasks in a TaskList.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Add task into a TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Delete task by its order number from a TaskList.
     *
     * @param taskNumber Order number of the task to be deleted.
     * @return Task deleted.
     */
    public Task deleteTask(int taskNumber) {
        Task curTask = this.taskList.remove(taskNumber - 1);
        return curTask;
    }

    /**
     * Mark a task in a TaskList as done.
     *
     * @param taskNumber Order number of the task to be completed.
     * @return Task marked as done.
     */
    public Task markTaskAsDone(int taskNumber) {
        Task curTask = this.taskList.get(taskNumber - 1);
        curTask.markAsDone();
        return curTask;
    }

    /**
     * Find tasks with description that contains a keyword specified by user.
     *
     * @param keyword Keyword to look for in tasks' description.
     * @return List of tasks that have the keyword in their description.
     */
    public ArrayList<Task> findMatchingTask(String keyword){
        ArrayList<Task> matchingTasks = new ArrayList<> ();

        // loop through every task in the list
        for (Task t: this.taskList){

            // add task to list if its description contains the keyword
            if (t.getDescription().contains(keyword)){
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }
}

