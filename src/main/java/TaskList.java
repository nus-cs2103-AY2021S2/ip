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

        // loop through every task in the list
        for (String s : myTasks) {
            String[] taskByParts = s.split(" \\| ", 3);
            String type = taskByParts[0];

            // check if task type is ToDo
            if (type.equals("T")) {

                // specify details of ToDo
                boolean isCompleted = isDone(taskByParts[1]);
                String description = taskByParts[2];
                Task newTask = new ToDo(description, isCompleted);

                // add ToDo to list
                taskList.add(newTask);
            }

            // check if task type is Deadline
            if (type.equals("D")) {

                // specify details of Deadline
                boolean isCompleted = isDone(taskByParts[1]);
                String[] details = taskByParts[2].split(" \\| ", 2);
                String description = details[0];
                String time = details[1];

                // convert time from String in "yyyy-M-dd H:mm" format to LocalDateTime in "MMM d yyyy hh:mm a" format
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
                String by = dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-dd H:mm"));

                // create new Deadline
                Task newTask = new Deadline(description, by, isCompleted);
                taskList.add(newTask);
            }

            // check if task type is Event
            if (type.equals("E")) {

                // specify details of Event
                boolean isCompleted = isDone(taskByParts[1]);
                String[] details = taskByParts[2].split(" \\| ", 2);
                String description = details[0];
                String time = details[1];

                // convert time from String in "yyyy-M-dd H:mm" format to LocalDateTime in "MMM d yyyy hh:mm a" format
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
                String by = dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-dd H:mm"));

                // create new Event
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
        ArrayList<Task> matchingTasks = new ArrayList<> ();

        // loop through every task in the list
        for (Task t: this.taskList){

            // add task to list if its description contains the keyword
            if (t.getDescription().contains(keyword)){
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }
}

