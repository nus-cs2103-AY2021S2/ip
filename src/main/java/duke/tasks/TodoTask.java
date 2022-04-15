package duke.tasks;

/**
 *  Represents a TodoTask.
 */
public class TodoTask extends Task {
    private String type;

    /**
     *  EventTask constructor.
     *
     *  @param taskName represents the name of the event task.
     */
    public TodoTask(String taskName) {
        super(taskName, "[T]");
        this.type = "[T]";
    }

    public String getType() {
        return this.type;
    }

    /**
     *  Formats the task to file output format in the data file.
     *
     *  @return String that is in the correct format.
     */
    public String writeToFileFormat() {
        return String.format("%s|%s|%s",
                "T",
                isDone == true ? "1" : "0",
                taskName);
    }

    @Override
    public String toString() {
        return this.type + super.toString();
    }
}
