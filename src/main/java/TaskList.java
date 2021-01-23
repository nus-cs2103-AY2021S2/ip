import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Contains the task list and takes charge of operations involving tasks and the task list
 */
public class TaskList {
    public static ArrayList<Task> taskList;
    public static ArrayList<String> storedList;

    /**
     * Initalizes a TaskList object in the situation where the specified
     * file from which tasks are supposed to be loaded from is not found
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }


    public TaskList(ArrayList<String> storedList) throws DukeException {
        taskList = new ArrayList<>();
        TaskList.storedList = storedList;
        loadInTaskList();
    }

    public static void loadInTaskList() throws DukeException {
        for (int i = 0; i < storedList.size(); i++) {
            String storedTaskString = storedList.get(i);
            if (storedTaskString.startsWith("[T]")) {
                String task = storedTaskString.substring(16);
                createTodoTask(task);
                checkIfTaskDone(storedTaskString, new Todo(task));
            } else if (storedTaskString.startsWith("[D]")) {
                int dateIndex = storedTaskString.indexOf("(by: ");
                String task = storedTaskString.substring(16, dateIndex - 1);
                String date = storedTaskString.substring(dateIndex + 5, dateIndex + 15);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
                LocalDate by = LocalDate.parse(date, format);
                createDeadlineTask(task, by);
                checkIfTaskDone(storedTaskString, new Deadline(task, by));
            } else if (storedTaskString.startsWith("[E]")) {
                int dateIndex = storedTaskString.indexOf("(at: ");
                String task = storedTaskString.substring(16, dateIndex - 1);
                String at = storedTaskString.substring(dateIndex + 5, storedTaskString.length() - 2);
                createEventTask(task, at);
                checkIfTaskDone(storedTaskString, new Event(task, at));
            } else {
                throw new DukeException("Invalid task.");
            }
        }
    }

    private static void checkIfTaskDone(String storedTaskString, Task task) {
        if (storedTaskString.substring(4).startsWith("C")) {
            markTaskDone(task);
        }
    }

    /**
     * Adds task into the task list
     * @param task to be added into the task list
     */
    private static void addTaskToList(Task task) {
        taskList.add(task);
    }

    /**
     * Creates an event task and adds it to the task list
     * @param eventTask event task description
     * @param date of which the event occurs
     */
    public static void createEventTask(String eventTask, String date) {
        Task task = new Event(eventTask, date);
        addTaskToList(task);
    }


    /**
     * Creates an deadline task and adds it to the task list
     * @param deadlineTask deadline task description
     * @param deadline of which the task is supposed to be completed by
     */
    public static void createDeadlineTask(String deadlineTask, LocalDate deadline) {
        Task task = new Deadline(deadlineTask, deadline);
        addTaskToList(task);
    }

    /**
     * Creates a todo task and adds it to the task list
     * @param todoTask todo task description
     */
    public static void createTodoTask(String todoTask) {
        Task task = new Todo(todoTask);
        addTaskToList(task);
    }

    /**
     * Mark task as done
     * @param task to be marked as done
     */
    public static void markTaskDone(Task task) {
        task.isDone = true;
    }

    /**
     * Deletes the task from the task list
     * @param taskNumber of the task (in the task list) that is to be deleted
     */
    public static void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

}


