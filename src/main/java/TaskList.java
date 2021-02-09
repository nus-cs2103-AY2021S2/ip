import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    private int totalTask;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> myTasks, int totalTask) {
        this.taskList = myTasks;
        this.totalTask = totalTask;
    }

    public TaskList(ArrayList<String> myTasks) {
        this.taskList = initialiseList(myTasks);
        this.totalTask = getSize();
    }

    /**
     * Execute the operations on this TaskList as instructed.
     * @param taskAction TaskAction object contains actionType and related task details to instruct the operation.
     * @return TaskResult object contains operation result as feedback to user.
     * @throws DukeException If taskNumber is invalid.
     */
    public TaskResult executeOperation(TaskAction taskAction) throws DukeException {
        Task relatedTask = taskAction.getRelatedTask();
        int relatedTaskNumber = taskAction.getRelatedTaskNumber();
        String keyword = taskAction.getKeyword();
        String actionType = taskAction.getActionType();
        TaskResult taskResult = new TaskResult();

        assert actionType != "";
        switch (actionType) {
        case "add":
            assert relatedTask != null;
            addTask(relatedTask);
            taskResult = new TaskResult(relatedTask, "add");
            break;
        case "complete":
            relatedTask = markTaskAsDone(relatedTaskNumber);
            taskResult = new TaskResult(relatedTask, "complete");
            break;
        case "delete":
            relatedTask = deleteTask(relatedTaskNumber);
            taskResult = new TaskResult(relatedTask, "delete");
            break;
        case "display":
            taskResult = new TaskResult(new TaskList(this.taskList, this.totalTask), "display");
            break;
        case "find":
            assert keyword != null;
            TaskList matchingTaskList = findMatchingTask(keyword);
            taskResult = new TaskResult(matchingTaskList, "find");
            break;
        }
        return taskResult;
    }


    private boolean isDone(String icon) {
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
    private void addTask(Task task) { this.taskList.add(task); }

    /**
     * Delete task by its order number from a TaskList.
     *
     * @param taskNumber Order number of the task to be deleted.
     * @return Task deleted.
     */
    private Task deleteTask(int taskNumber) throws DukeException {
        try {
            Task curTask = this.taskList.remove(taskNumber - 1);
            return curTask;
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("OOPS!! The task number is invalid.");
        }
    }

    /**
     * Mark a task in a TaskList as done.
     *
     * @param taskNumber Order number of the task to be completed.
     * @return Task marked as done.
     */
    private Task markTaskAsDone(int taskNumber) throws DukeException {
        try {
            Task curTask = this.taskList.get(taskNumber - 1);
            curTask.markAsDone();
            return curTask;
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("OOPS!! The task number is invalid.");
        }
    }

    /**
     * Find tasks with description that contains a keyword specified by user.
     *
     * @param keyword Keyword to look for in tasks' description.
     * @return List of tasks that have the keyword in their description.
     */
    private TaskList findMatchingTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<> ();
        // loop through every task in the list
        for (Task t: this.taskList) {
            // add task to list if its description contains the keyword
            if (t.getDescription().contains(keyword)){
                matchingTasks.add(t);
            }
        }
        int totalTask = matchingTasks.size();
        return new TaskList(matchingTasks, totalTask);
    }
}

