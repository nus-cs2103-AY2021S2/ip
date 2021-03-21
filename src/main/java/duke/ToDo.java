package duke;

public class ToDo extends Task {
    protected String tag;
    /**
     * Constructor method.
     * @param description User input description of ToDo
     * @param tag User input tag for the Event
     */
    public ToDo(String description, String tag) {
        super(description);
        this.tag = tag;
    }

    public void setDescription(String des) {
        this.description = des;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    /**
     * Method to return a ToDo object in the specified format
     * @return Formatted String of ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " #" + this.tag;
    }
}

