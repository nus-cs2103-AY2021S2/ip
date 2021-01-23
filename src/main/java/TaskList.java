import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.ToDoTask;
import java.time.LocalDate;

public class TaskList {
    public List<Task> inputList = new ArrayList<>();

    public TaskList() {
        this.inputList = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tasks) {
        for (String taskStr: tasks) {
            String[] arr = taskStr.split("\\|");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i].trim();
            }

            if (arr[0].equals("D")) {
                String[] deadline = arr[3].split("-");
                String reformattedDeadline = deadline[2] + "-" + deadline[1] + "-" + deadline[0];
                DeadlineTask deadlineTask = new DeadlineTask(arr[2], reformattedDeadline);

                if (arr[1].equals("1"))
                    deadlineTask.markAsDone();

                inputList.add(deadlineTask);

            } else if (arr[0].equals("E")) {
                String[] timing = arr[3].split("-");
                String reformattedTiming = timing[2] + "-" + timing[1] + "-" + timing[0];

                EventTask eventTask = new EventTask(arr[2], reformattedTiming);

                if (arr[1].equals("1"))
                    eventTask.markAsDone();

                inputList.add(eventTask);

            } else {
                ToDoTask toDoTask = new ToDoTask(arr[2]);

                if (arr[1].equals("1"))
                    toDoTask.markAsDone();

                inputList.add(toDoTask);
            }
        }
    }

    public List<Task> getList() {
        return this.inputList;
    }

    public void add(Task task) {
        this.inputList.add(task);
    }

    public Task get(int index) {
        return this.inputList.get(index);
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
        Task markDone = this.inputList.get(index - 1);

        markDone.markAsDone();

        return markDone;
    }

    public Task handleDelete(int index) {
        Task task = this.inputList.remove(index - 1);

        return task;

    }

    public String getListToWrite() {
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

    public String findOnDateTasks(String date) {
        LocalDate toSearch = LocalDate.parse(date);
        String result = "";

        for (Task task: inputList) {
            if (task.getType() == 'D') {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (deadlineTask.getDeadlineAsLocalDate().equals(toSearch))
                    result += deadlineTask + "\n";

            } else if (task.getType() == 'E') {
                EventTask eventTask = (EventTask) task;
                if (eventTask.getTimingAsLocalDate().equals(toSearch))
                    result += eventTask + "\n";
            }
        }

        return result;
    }
}
