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

    public void add(String type, String task, boolean isCompleted, boolean isOldData) throws DukeException {
        if (type.equals(TODO)) {
            list.add(new ToDo(task, isCompleted));
        } else {
            String description = task.split("/")[0];
            String deadline = task.split("/", 2)[1].split(" ", 2)[1];
            if (type.equals(DEADLINE)) {
                list.add(new Deadline(description, isCompleted, deadline));
            } else if (type.equals(EVENT)) {
                list.add(new Event(description, isCompleted, deadline));
            }
        }
        if (!isOldData) {
            System.out.println("Got it. I've added this task:\n" + list.get(list.size() - 1) +
                    "\nNow you have " + list.size() + " tasks in the list.");
            System.out.println("");
        }
    }

    public void delete(int taskId) throws DukeException {
        if (taskId <= list.size() && taskId >= 1) {
            Task deletedTask = list.get(taskId - 1);
            list.remove(taskId - 1);
            System.out.println("Noted. I've removed this task:\n" + deletedTask +
                    "\nNow you have " + list.size() + " tasks in the list.");
            System.out.println("");
        } else {
            throw new DukeException("Invalid task number. You only have " + list.size() + " tasks in the list.");
        }
    }

    public void done(int taskId) throws DukeException {
        if (taskId <= list.size() && taskId >= 1) {
            Task completedTask = list.get(taskId - 1);
            completedTask.markComplete();
            System.out.println("Nice! I've marked this task as done:\n" + completedTask);
            System.out.println("");
        } else {
            throw new DukeException("Invalid task number. You only have " + list.size() + " tasks in the list.");
        }
    }

    public void printList() {
        if (list.size() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i));
            }
        }
        System.out.println("");
    }

    public void getTasksOn(String date) {
        LocalDate currentDate = LocalDate.parse(date);
        System.out.println("Here are the tasks due on " + date + ": " );
        int counter = 1;
        for(Task task : list) {
            if(task.getTaskDate().equals(currentDate)) {
                System.out.println(counter + "." + task);
            }
        }
    }

    public void manage(String[] parsedAction) throws DukeException {
        String command = parsedAction[0];
        if (command.equals(DEADLINE) || command.equals(EVENT) || command.equals(TODO)) {
            add(command, parsedAction[1], false, false);
        } else if (command.equals(LIST)) {
            printList();
        } else if (command.equals(DONE)) {
            done(Integer.parseInt(parsedAction[1]));
        } else if (command.equals(DUE)) {
            getTasksOn(parsedAction[1]);
        } else {
            delete(Integer.parseInt(parsedAction[1]));
        }
    }

    public void upload(ArrayList<String> storedData) throws DukeException{
        for (String task : storedData) {
            String[] arr = task.split(" ", 3);
            String type = arr[0];
            boolean isCompleted = arr[1].equals("1");
            add(arr[0], arr[2], isCompleted, true);
        }
    }

    public ArrayList<String> retrieveTasksforStorage() {
        ArrayList<String> taskList = new ArrayList<>();
        for (Task task : list) {
            taskList.add(task.getFormattedData());
        }
        return taskList;
    }
}
