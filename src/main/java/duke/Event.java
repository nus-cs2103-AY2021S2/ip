package duke;

public class Event extends Task{
    private String info;
    protected Event(String taskLine) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        super(taskLine);
        checkTask(taskLine);
        buildInfo();
    }
    protected void checkTask(String taskLine) throws  ArrayIndexOutOfBoundsException, IllegalArgumentException{
        if (taskLine.length() < 2) {
            throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The description of a Event cannot be empty.");
        } else if (!taskLine.contains("/at")) {
            throw new IllegalArgumentException("☹ OOPS!!! The Event needs an '/at' time.");
        }
    }
    protected void buildInfo(){
        String[] parsedTask = taskLine.split("event");
        parsedTask = parsedTask[1].split("/at");
        this.name = parsedTask[0].strip();
        this.dateTime = parsedTask[1].strip();
        setDateTimeLD(dateTime);
        this.info = name + " at: " + dateTime ;
    }

    protected String printNew(){
        return "[E][ ] " + info;
    }
    public String toString(){
        return info;
    }
    @Override
    protected String type(){
        return "E";
    }
}