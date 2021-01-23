import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList;
    public static ArrayList<String> storedList;

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
     * adds task into the task list
     * @param task to be added into the task list
     */
    private static void addTaskToList(Task task) {
        taskList.add(task);
    }

    /**
     * creates an event task and adds it to the task list
     * @param eventTask supplied from user's command-line input to add an event task
     * @param date
     */
    public static void createEventTask(String eventTask, String date) {
        Task task = new Event(eventTask, date);
        addTaskToList(task);
    }

    /**
     * creates a deadline task and adds it to the task list
     * @param deadline supplied from user's command-line input to add a deadline task
     */
    public static void createDeadlineTask(String deadlineTask, LocalDate deadline) {
        Task task = new Deadline(deadlineTask, deadline);
        addTaskToList(task);
    }

    /**
     * creates a todo task and adds it to the task list
     * @param todoTask supplied from user's command-line input to add a todo task
     */
    public static void createTodoTask(String todoTask) {
        Task task = new Todo(todoTask);
        addTaskToList(task);
    }

    /**
     * Mark task as done and notify user of that change
     * @param task supplied from the user's command-line input to mark a task as done
     */
    public static void markTaskDone(Task task) {
        task.isDone = true;
    }

    /**
     * Chatbot deletes the task from the task list
     * @param taskNumber referring to the delete command-line input supplied by the user
     */
    public static void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

}


