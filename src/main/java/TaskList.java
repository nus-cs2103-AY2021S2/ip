import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<String> myTasks) {
        this.taskList = initialiseList(myTasks);
    }

    private boolean isDone(String icon){
        if (icon.equals("\u2713")){
            return true;
        }
        return false;
    }

    private ArrayList<Task> initialiseList(ArrayList<String> myTasks) {
        ArrayList<Task> taskList = new ArrayList<>();

        for (String s : myTasks) {
            String[] parts = s.split(" \\| ", 3);
            String type = parts[0];

            if (type.equals("T")) {
                boolean isCompleted = isDone(parts[1]);
                String description = parts[2];
                Task newTask = new ToDo(description, isCompleted);
                taskList.add(newTask);
            }

            if (type.equals("D")) {
                boolean isCompleted = isDone(parts[1]);
                String[] details = parts[2].split(" \\| ", 2);
                String description = details[0];
                String time = details[1];

                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
                String by = dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-dd H:mm"));

                Task newTask = new Deadline(description, by, isCompleted);
                taskList.add(newTask);
            }

            if (type.equals("E")) {
                boolean isCompleted = isDone(parts[1]);
                String[] details = parts[2].split(" \\| ", 2);
                String description = details[0];
                String time = details[1];

                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
                String by = dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-dd H:mm"));

                Task newTask = new Event(description, by, isCompleted);
                taskList.add(newTask);
            }
        }
        return taskList;
    }


    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber);
        return task;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int taskNumber) {
        Task curTask = this.taskList.remove(taskNumber - 1);
        return curTask;
    }

    public Task markTaskAsDone(int taskNumber) {
        Task curTask = this.taskList.get(taskNumber - 1);
        curTask.markAsDone();
        return curTask;
    }

    public ArrayList<Task> findMatchingTask(String keyword){
        ArrayList<Task> matchingTask = new ArrayList<> ();
        for (Task t: this.taskList){
            if (t.getDescription().contains(keyword)){
                matchingTask.add(t);
            }
        }
        return matchingTask;
    }
}

