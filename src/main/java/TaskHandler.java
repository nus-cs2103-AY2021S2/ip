import java.util.List;
import java.util.ArrayList;

import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.ToDoTask;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public static void printRemoved() {
        System.out.println("     Noted. I've removed this task: ");
    }

    public ToDoTask handleToDoTask(String action) {
        int index = action.indexOf(" ");
        String description = action.substring(index + 1);

        ToDoTask toDoTask = new ToDoTask(description);

        this.add(toDoTask);

        return toDoTask;
    }

    public EventTask handleEventTask(String action) {
        int actionIndex = action.indexOf(" ");
        int descriptionIndex = action.indexOf("/");

        String description = action.substring(actionIndex + 1, descriptionIndex - 1);
        String event =  action.substring(descriptionIndex + 4);
        EventTask eventTask= new EventTask(description, event);

        this.add(eventTask);

        return eventTask;
    }

    public DeadlineTask handleDeadlineTask(String action) {
        int actionIndex = action.indexOf(" ");
        int descriptionIndex = action.indexOf("/");

        String description = action.substring(actionIndex + 1, descriptionIndex - 1);
        String deadline =  action.substring(descriptionIndex + 4);
        DeadlineTask deadlineTask= new DeadlineTask(description, deadline);

        this.add(deadlineTask);

        return deadlineTask;
    }


    public Task handleDone(int index) {
        printMarked();
        Task markDone = this.inputList.get(index - 1);

        markDone.markAsDone();

        return markDone;
    }

    public Task handleDelete(int index) {
        printMarked();
        Task task = this.inputList.remove(index - 1);

        return task;

    }

    public String getList() {
        String result = "";
        for (int i = 0; i < inputList.size(); i++) {
            Task task = inputList.get(i);
            char type = task.getType();
            String status = task.getStatusIcon().equals(" ") ? "0" : "1";
            String description = task.getDescription();
            String date = "";

            if (type == 'D') {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                date = deadlineTask.getUnformattedDeadline();
            } else if (type == 'E') {
                EventTask eventTask = (EventTask) task;
                date = eventTask.getUnformattedTiming();;
            }

            result += type + " | " + status +  " | " + description;
            if (date.equals("")) {
                result += "\n";
            } else {
                result += " | " + date + "\n";
            }
        }

        return result;
    }

    public void printOnDateTasks(String date) {
        LocalDate toSearch = LocalDate.parse(date);

        for (Task task: inputList) {
            if (task.getType() == 'D') {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (deadlineTask.getDeadlineAsLocalDate().equals(toSearch))
                    System.out.println(deadlineTask);

            } else if (task.getType() == 'E') {
                EventTask eventTask = (EventTask) task;
                if (eventTask.getTimingAsLocalDate().equals(toSearch))
                    System.out.println(eventTask);
            }

        }
    }

    public void countTasks() {
        System.out.println("     Now you have " + inputList.size() + " tasks in the list.");
    }
}
