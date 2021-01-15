import java.util.List;
import java.util.ArrayList;

import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.ToDoTask;

public class TaskHandler {
    public List<Task> inputList;

    public TaskHandler() {
        inputList = new ArrayList<>();
    }

    public void add(Task task) {
        this.inputList.add(task);
    }

    public Task get(int index) {
        return this.inputList.get(index);
    }

    public void printStored() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < inputList.size(); i++) {
            Task task = inputList.get(i);
            System.out.println("      " + (i + 1) + "." + task.toString().trim());
        }
    }

    public void addPrint() {
        System.out.println("     Got it. I've added this task: ");
    }

    public static void printMarked() {
        System.out.println("     " + "Nice! I've marked this task as done:");
    }

    public void handleToDoTask(String action) {
        int index = action.indexOf(" ");
        String description = action.substring(index + 1);
        ToDoTask toDoTask = new ToDoTask(description);
        this.add(toDoTask);
        this.addPrint();
        System.out.println(toDoTask);
        this.countTasks();
    }

    public void handleEventTask(String action) {
        int actionIndex = action.indexOf(" ");
        int descriptionIndex = action.indexOf("/");
        String description = action.substring(actionIndex + 1, descriptionIndex - 1);
        String event =  action.substring(descriptionIndex + 1);
        EventTask eventTask= new EventTask(description, event);
        this.add(eventTask);
        addPrint();
        System.out.println(eventTask);
        this.countTasks();
    }

    public void handleDeadlineTask(String action) {
        int actionIndex = action.indexOf(" ");
        int descriptionIndex = action.indexOf("/");
        String description = action.substring(actionIndex + 1, descriptionIndex - 1);
        String event =  action.substring(descriptionIndex + 1);
        EventTask eventTask= new EventTask(description, event);
        this.add(eventTask);
        addPrint();
        System.out.println(eventTask);
        this.countTasks();
    }

    public void handleDone(int index) {
        printMarked();
        Task markDone = this.inputList.get(index - 1);
        markDone.markAsDone();
        markDone.getStatusAndTask();
    }

    public void countTasks() {
        System.out.println("     Now you have " + inputList.size() + " tasks in the list.");
    }
}
