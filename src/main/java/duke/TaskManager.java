package duke;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskManager{
    private ArrayList<Task> list = new ArrayList<>(100);
    private final static String TODO = "todo";
    private final static String DELETE = "delete";
    private final static String DONE = "done";
    private final static String EVENT = "event";
    private final static String DEADLINE = "deadline";
    private final static String LIST = "list";
    private final static String DUE = "due";

    public Task add(String type, String task, boolean isCompleted)  {
        if (type.equals(TODO)) {
            Task newTask = new ToDo(task, isCompleted);
            list.add(newTask);
            return newTask;
        } else {
            String description = task.split("/")[0];
            String deadline = task.split("/", 2)[1].split(" ", 2)[1];
            if (type.equals(DEADLINE)) {
                Task newTask = new Deadline(description, isCompleted, deadline);
                list.add(newTask);
                return newTask;
            } else {
                Task newTask = new Event(description, isCompleted, deadline);
                list.add(newTask);
                return newTask;
            }
        }
    }

    public Task delete(int taskId) throws DukeException {
        if (taskId <= list.size() && taskId >= 1) {
            Task deletedTask = list.get(taskId - 1);
            list.remove(taskId - 1);
            return deletedTask;
        } else {
            throw new DukeException("Invalid task number. You only have " + list.size() + " tasks in the list.");
        }
    }

    public Task done(int taskId) throws DukeException {
        if (taskId <= list.size() && taskId >= 1) {
            Task completedTask = list.get(taskId - 1);
            completedTask.markComplete();
            return completedTask;
        } else {
            throw new DukeException("Invalid task number. You only have " + list.size() + " tasks in the list.");
        }
    }

    public ArrayList<Task> getTasksOn(String date) {
        LocalDate currentDate = LocalDate.parse(date);
        System.out.println("Here are the tasks due on " + date + ": " );
        int counter = 1;
        ArrayList<Task> dueList = new ArrayList<>();
        for(Task task : list) {
            if(task.getTaskDate().equals(currentDate)) {
                dueList.add(task);
            }
        }
        return dueList;
    }

    public void upload(ArrayList<String> storedData) {
        for (String task : storedData) {
            String[] arr = task.split(" ", 3);
            String type = arr[0];
            boolean isCompleted = arr[1].equals("1");
            add(type, arr[2], isCompleted);
        }
    }

    public ArrayList<String> retrieveTasksforStorage() {
        ArrayList<String> taskList = new ArrayList<>();
        for (Task task : list) {
            taskList.add(task.getFormattedData());
        }
        return taskList;
    }

    public int getNumOfTasks() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}