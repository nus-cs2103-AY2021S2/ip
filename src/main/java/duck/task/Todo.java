package duck.task;

public class Todo extends Task {

    /**
     * initialize Todo object
     *
     * @param description the description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * get task information
     *
     * @return a string show information of task
     */
    @Override
    public String getTaskInfo() {
        return "[T]" + super.getTaskInfo();
    }

    /**
     * get the task information showing in the file
     *
     * @return a string of task information which is shown in the datafile
     */
    @Override
    public String getTaskInfoOfFile() {
        return "T | " + (super.isDone ? "1" : "0") + " | " + super.getDescription();
    }

    /**
     * get period from now to starting time
     *
     * @return the string because Todo task doesn't have time
     */
    @Override
    public String getPeriodDays() {
        return "this task hasn't determine the time";

    }
}
