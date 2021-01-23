package chatbot.tasks;

public class EventTask extends Task{
    private String type;
    private String time;

    public EventTask(String taskName, String time) {
        super(taskName);
        this.type = "[E]";
        this.time = time;
    }

    public String writeToFileFormat() {
        return String.format("%s|%s|%s|%s",
                "T",
                isDone == true ? "1" : "0",
                taskName,
                time);
    }

    @Override
    public String toString() {
        return this.type + super.toString() + " (" + this.time + ")";
    }
}
