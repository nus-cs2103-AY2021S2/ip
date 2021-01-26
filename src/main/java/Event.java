public class Event extends Task{
    private String info;
    public Event(String taskLine) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        super(taskLine);
        checkTask(taskLine);
        buildInfo();
    }
    private void checkTask(String taskLine) throws  ArrayIndexOutOfBoundsException, IllegalArgumentException{
        if (taskLine.length() < 2) {
            throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The description of a Event cannot be empty.");
        } else if (!taskLine.contains("/at")) {
            throw new IllegalArgumentException("☹ OOPS!!! The Event needs an '/at' time.");
        }
    }
    private void buildInfo(){
        String[] parsedTask = taskLine.split("event");
        parsedTask = parsedTask[1].split("/at");
        this.name = parsedTask[0].strip();
        this.dateTime = parsedTask[0].strip();
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