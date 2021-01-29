import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    public List<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public void loadTasksFromStorage(String tasks) {
        Scanner sc = new Scanner(tasks);
        while (sc.hasNextLine()) {
            String[] taskParameters = sc.nextLine().split(Task.TASK_DELIMITER);
            switch (taskParameters[0]) {
            case "TODO":
                addTask(new Todo(taskParameters[1]));
            case "DEADLINE":
                addTask(new Deadline(taskParameters[1], taskParameters[2]));
            case "EVENT":
                addTask(new Event(taskParameters[1], taskParameters[2]));
            }
        }
        sc.close();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    public int getNumOfTasks() {
        return taskList.size();
    }

    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public String returnTaskListAsString() {
        StringBuilder outputString = new StringBuilder();
        for (Task task : taskList) {
            outputString.append(task.toString());
            outputString.append("\n");
        }
        return outputString.toString();
    }

    public void printTaskList() {
        int count = 1;
        System.out.print(UserInterface.line);
        for (Task task : taskList) {
            System.out.println("\t" + count++ + ". " + task);
        }
        System.out.println(UserInterface.line);
    }
}
