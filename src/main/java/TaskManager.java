import java.util.ArrayList;

public class TaskManager {

    private String operator;
    private String[] taskDetail;

    public TaskManager(String operator, String... taskDetail){
        this.operator = operator;
        this.taskDetail = taskDetail;
    }

    public TaskAction execute(){
        TaskAction action = new TaskAction();

        switch (this.operator){
        case "bye":
            action = new TaskAction("bye");
            break;

        case "done":
            int taskNumberToComplete = Integer.valueOf(taskDetail[0]);
            action = completeTask(taskNumberToComplete);
            break;

        case "delete":
            int taskNumberToDelete = Integer.valueOf(taskDetail[0]);
            action = deleteTask(taskNumberToDelete);
            break;

        case "todo":
            String description = taskDetail[0];
            action = addToDo(description);
            break;

        case "deadline":
            String[] detailsDeadline = taskDetail;
            action = addDeadline(detailsDeadline);
            break;

        case "event":
            String[] detailsEvent = taskDetail;
            action = addEvent(detailsEvent);
            break;

        case "list":
            action = displayList();
            break;

        case "find":
            String keyword = taskDetail[0];
            action = findTask(keyword);
            break;
        }
        return action;
    }
//    public boolean isExit(){
//        return this.operator.equals("bye");
//    }

    private TaskAction addEvent(String[] details) {
        // create a new Event
        String description = details[0];
        String time = details[1];
        Event newTask = new Event(description, time);

        return new TaskAction(newTask, "add");
    }

    private TaskAction addDeadline(String[] details) {
        // create a new Deadline
        String description = details[0];
        String time = details[1];
        Deadline newTask = new Deadline(description, time);

        return new TaskAction(newTask, "add");
    }

    private TaskAction addToDo(String description) {
        // create a new ToDo
        ToDo newTask = new ToDo(description);

        return new TaskAction(newTask, "add");
    }

    private TaskAction completeTask(int taskNumber) {
        return new TaskAction(taskNumber, "complete");
    }

    private TaskAction deleteTask(int taskNumber) {
        return new TaskAction(taskNumber, "delete");
    }

    private TaskAction displayList(){
        return new TaskAction("display");
    }

    private TaskAction findTask(String keyword) {
        return new TaskAction(keyword, "find");
    }
}
