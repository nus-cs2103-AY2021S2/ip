// Task to be added to a list
public class Task {

    // task_details states what the task is
    // done indicates whether the task is done
    private String task_details;
    private boolean done;

    Task(String details){
        task_details = details;
        done = false;
    }

    public String getTask_details() {
        return task_details;
    }
}
