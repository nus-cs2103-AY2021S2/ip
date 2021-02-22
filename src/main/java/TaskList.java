import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;
    private int size;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }

    public TaskList(List<Task> list) {
        this.tasks = list;
        this.size = list.size();
    }

    /**
     * Returns a task with the specified task number.
     * @param taskNo the task number
     * @return the task in the task list with the specified task number
     */
    public Task get(int taskNo) {
        return tasks.get(taskNo);
    }

    /**
     * Generates a string consisting of a list of all the tasks in the task list.
     * @return a list of the tasks in the task list
     */
    public String listAllTasks() {
        String output = "";
        for (int i = 1; i < tasks.size() + 1; i++) {
            output = output + i + "." + tasks.get(i - 1).toString() + "\n";
        }
        return output;
    }

    /**
     * Gets the task list.
     * @return the task list
     */
    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Marks a task within the task list as done.
     * @param taskNo the number of the task within the task list that needs to be marked as done
     * @return a String of the task that was marked as done
     */
    public String makeDone(int taskNo) {
        assert taskNo > 0 : "Task number cannot be negative";
        tasks.get(taskNo).markAsDone();
        return tasks.get(taskNo).toString();
    }

    /**
     * Returns the number of tasks in the task list.
     * @return the number of tasks in the task list
     */
    public int size() {
        return this.size;
    }

    /**
     * Adds a task into the task list provided a <code>fullCommand</code> and a <code>typeOfCommand</code>.
     * @param fullCommand the full user input
     * @param typeOfCommand the type of command to be executed
     * @return the description of the task added
     */
    public String addByCommand(String fullCommand, String typeOfCommand) {
        if (typeOfCommand.equals("todo")) {
            return addToDo(fullCommand);
        } else if (typeOfCommand.equals("deadline")) {
            return addDeadline(fullCommand);
        } else {
            return addEvent(fullCommand);
        }
    }

    /**
     * Adds a <code>ToDo</code> task into the task list.
     * @param fullCommand the full user input
     * @return the description of the task added
     */
    public String addToDo(String fullCommand) {
        ToDo newToDo = new ToDo(fullCommand.substring(5));
        tasks.add(newToDo);
        this.size++;
        return newToDo.toString();
    }

    /**
     * Adds a <code>Deadline</code> task into the task list.
     * @param fullCommand the full user input
     * @return the description of the task added
     */
    public String addDeadline(String fullCommand) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        int indexOfDate = fullCommand.indexOf("/");
        String dateAndTime = fullCommand.substring(indexOfDate + 4);
        String date = dateAndTime.split(" ")[0];
        String time = dateAndTime.split(" ")[1];
        Deadline newDeadline = new Deadline(fullCommand.substring(9, indexOfDate),
                LocalDate.parse(date),
                LocalTime.parse(time, formatter));
        tasks.add(newDeadline);
        this.size++;
        return newDeadline.toString();
    }

    /**
     * Adds an <code>Event</code> task into the task list.
     * @param fullCommand the full user input
     * @return the description of the task added
     */
    public String addEvent(String fullCommand) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        int indexOfDate = fullCommand.indexOf("/");
        String dateAndTime = fullCommand.substring(indexOfDate + 4);
        String date = dateAndTime.split(" ")[0];
        String timeRange = dateAndTime.split(" ")[1];
        String startTime = timeRange.split("-")[0];
        String endTime = timeRange.split("-")[1];
        Event newEvent = new Event(fullCommand.substring(6, indexOfDate), LocalDate.parse(date),
                LocalTime.parse(startTime, formatter), LocalTime.parse(endTime, formatter));
        tasks.add(newEvent);
        this.size++;
        return newEvent.toString();
    }

    /**
     * Adds a task into the task list provided a <code>Task</code>.
     * @param newTask the task to be added into the list
     * @return the list of tasks after adding the task
     */
    public TaskList addByTask(Task newTask) {
        this.tasks.add(newTask);
        this.size++;
        return this;
    }

    /**
     * Removes a task from the task list given the number of the task.
     * @param taskNo the number of the task that is to be removed from the task list
     * @return the description of the task that was removed
     */
    public String remove(int taskNo) {
        assert taskNo > 0 : "Task number cannot be negative";
        Task removed = tasks.remove(taskNo);
        this.size--;
        return removed.toString();
    }

    /**
     * Returns a list of tasks that falls on a certain date
     * @param parsedDate the user specified date
     * @return a list of tasks that falls on the specified date
     */
    public List<Task> listTasksOnDate(LocalDate parsedDate) {
        List<Task> toPrint = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDate() != null && t.getDate().equals(parsedDate)) {
                toPrint.add(t);
            }
        }
        return toPrint;
    }

    /**
     * Returns a list of tasks that contain a certain keyword.
     * @param keyword the user specified keyword
     * @return a list of tasks that contains the user specified keyword
     */
    public List<Task> findKeyword(String keyword) {
        List<Task> tasksWithKeywords = new ArrayList<>();
        for (Task t : tasks) {
            String[] wordsOfDescription = t.getDescription().split(" ");
            for (int i = 0; i < wordsOfDescription.length; i++) {
                if (wordsOfDescription[i].equals(keyword)) {
                    tasksWithKeywords.add(t);
                    break;
                }
            }
        }
        return tasksWithKeywords;
    }

    public Task rescheduleTask(TaskList tasks, int taskNo, String newDateAndTime) throws DukeException {
        return tasks.get(taskNo).reschedule(newDateAndTime);
    }
}
