/**
 * Simulates a task to be completed.
 */
public class Task {

    /** states what the task is */
    private String task_details;

    /** indicates whether task is completed */
    private boolean isDone;

    /** Creates a task.
     *
     * @param details details of the task.
     */
    Task(String details){
        task_details = details;
        isDone = false;
    }

    // private constructor to maintain Immutability
    protected Task(String details, boolean indicator){
        task_details = details;
        isDone = true;
    }

    /** returns task details.
     *
     * @return task details.
     */
    public String getTask_details() {
        return task_details;
    }

    /** checks if task is completed.
     *
     * @return completion status.
     */
    public boolean isDone() {
        return isDone;
    }

    /** completes the task.
     *
     * @return new completed Task with the same details.
     */
    public Task completeTask() {
        return new Task(task_details, true);
    }

    /** returns completed status and task details as a String.
     *
     * @return String stating completion status and task details.
     */
    public String taskStatus() {
        if (isDone) {
           return "1 " + task_details;
        } else {
            return "0 " + task_details;
        }
    }
}
