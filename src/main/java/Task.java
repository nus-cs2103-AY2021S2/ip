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

    // private constructor to maintain Immutability
    protected Task(String details, boolean indicator){
        task_details = details;
        done = true;
    }

    // returns task details
    public String getTask_details() {
        return task_details;
    }

    // checks if task is done
    public boolean isDone() {
        return done;
    }

    // completes the task
    public Task completeTask() {
        return new Task(task_details, true);
    }

    // states if task is completed or not
    public String taskStatus() {
        if (done) {
           return "1 " + task_details;
        } else {
            return "0 " + task_details;
        }
    }
}
