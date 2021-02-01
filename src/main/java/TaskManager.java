import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskManager {
    public List<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public void loadTasksFromStorage(String tasks) {
        Scanner sc = new Scanner(tasks);
        while (sc.hasNextLine()) {
//            System.out.println(sc.nextLine());
            String[] taskParameters = sc.nextLine().split(Task.TASK_DELIMITER_REGEX);
            boolean isDone = taskParameters[1].equals("done");
//            System.out.println(Arrays.toString(taskParameters));
//            System.out.println(taskParameters.length);

            switch (taskParameters[0]) {
            case "TODO":
                addTask(new Todo(taskParameters[2], isDone));
                break;
            case "DEADLINE": {
                String[] deadlineDateAndTime = taskParameters[3].split(", ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
                addTask(new Deadline(taskParameters[2], isDone, LocalDate.parse(deadlineDateAndTime[0], formatter).toString(), deadlineDateAndTime[1]));
                break;
            }
            case "EVENT": {
                String[] eventDateAndTime = taskParameters[3].split(", ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
                addTask(new Event(taskParameters[2], isDone, LocalDate.parse(eventDateAndTime[0], formatter).toString(), eventDateAndTime[1]));
                break;
            }
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
