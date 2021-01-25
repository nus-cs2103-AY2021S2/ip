import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    // tasks in schedule
    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<Task> ();
    }

    public TaskList(ArrayList<String> myTasks){

        for (String s: myTasks){
            String[] parts = s.split(" | ", 2);
            String type = parts[0];
            if (type.equals("T")){
                String description = parts[1];
                // addTodo
                Task newTask = new ToDo(description);
                addTask(newTask);
            }

            if (type.equals("D")){
                String[] details = parts[1].split(" | ");
                String description = details[0];
                String time = details[1];

                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
                String by = dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-dd H:mm"));

                Task newTask = new Deadline(description, by);
                addTask(newTask);
            }

            if (type.equals("E")){
                String[] details = parts[1].split(" | ");
                String description = details[0];
                String time = details[1];

                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
                String by = dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-dd H:mm"));

                Task newTask = new Event(description, by);
                addTask(newTask);
            }
        }
    }

    public int getSize(){
        return this.taskList.size();
    }

    public Task getTask(int taskNumber){
        Task task = this.taskList.get(taskNumber);
        return task;
    }

    private void addTask(Task task){
        this.taskList.add(task);
    }

    public void removeTask(int taskNumber){
        this.taskList.get(taskNumber);
    }
}
