import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class TaskList {
    private List<Task> newStorage;

    public TaskList(List<Task> initialisedStorage) {
        this.newStorage = initialisedStorage;
    }


    /**
     * Prints the task in a list format, includes description, task type and whether task is completed
     * @param num int index of task in the list
     * @param task the task to be printed out
     */
    public void printTask(int num, Task task) {
        if (task.getDone()) {
            System.out.println(num + 1 + "." + "[" + task.type + "]" + "[X] " + this.newStorage.get(num).getDescription());
        } else {
            System.out.println(num + 1 + "." + "[" + task.type + "]" + "[ ] " + this.newStorage.get(num).getDescription());
        }
    }

    /**
     * Prints the task which includes description, task type and whether task is completed
     * @param task the task to be printed out
     */
    public void printTaskWithNoNum(Task task) {
        System.out.println("[" + task.type + "]" + "[ ] " + task.getDescription());
    }

    /**
     * Adds a task to the list of task in storage
     * @param userInput the user input in text string format
     */
    public void addTask(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("Hmm");
        Task task;
        String[] input = userInput.split(" ");
        if (input[0].equals("todo")) {
            String description = userInput.substring(userInput.indexOf("todo") + 5);
            task = new Todo(description);
        } else if (input[0].equals("deadline")) {
            String description = userInput.substring(userInput.indexOf("deadline") + 9, userInput.indexOf("/by") - 1);
            String deadlineDate = userInput.substring(userInput.indexOf("/by") + 4, userInput.indexOf("/by") + 14);
            LocalDate date = LocalDate.parse(deadlineDate, formatter);
            String deadLineTime = userInput.substring(userInput.indexOf("/by") + 15);
            LocalTime time = LocalTime.parse(deadLineTime, timeFormatter);
            task = new Deadline(description, date, time);
        } else {
            String description = userInput.substring(userInput.indexOf("event") + 6, userInput.indexOf("/at") - 1);
            String eventDate = userInput.substring(userInput.indexOf("/at") + 4, userInput.indexOf("/at") + 14);
            LocalDate date = LocalDate.parse(eventDate, formatter);
            String deadLineTime = userInput.substring(userInput.indexOf("/at") + 15);
            LocalTime time = LocalTime.parse(deadLineTime, timeFormatter);
            task = new Event(description, date, time);
        }
        newStorage.add(task);
        System.out.println("Got it. I've added this task: ");
        printTaskWithNoNum(task);
        System.out.println("Now you have " + this.newStorage.size() + " tasks in the list.");
    }

    /**
     * List out of the task that are in the task list storage
     * @param tasks List of tasks
     */
    public void listTask(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            printTask(i, tasks.get(i));
        }
    }

    /**
     * Set a specific task as completed based on the index of task in task list storage
     * @param value int index of the task in list
     */
    public void setTaskAsDone(int value) {
        this.newStorage.get(value).setDone(true);
    }

    /**
     * Deletes a task based on string input of user
     * @param userInput user input in string text
     */
    public void deleteTask(String userInput) {
        String[] inputBreakdown = userInput.split(" ");
        int taskToBeDeleted = Integer.valueOf(inputBreakdown[1]) - 1;
        Task task = this.newStorage.get(taskToBeDeleted);
        this.newStorage.remove(taskToBeDeleted);
        System.out.println("Noted. I've removed this task:");
        printTaskWithNoNum(task);
        System.out.println("Now you have " + this.newStorage.size() + " tasks in the list.");
    }

    public List<Task> getNewStorage() {
        return newStorage;
    }

    public void findTasksWithKeyword(String keyWord) {
        List<Task> tasks = this.getNewStorage();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyWord)) {
                printTask(i, tasks.get(i));
            }
        }
    }

    public void setNewStorage(List<Task> newStorage) {
        this.newStorage = newStorage;
    }
}
