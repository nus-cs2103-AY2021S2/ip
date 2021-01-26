public class Deadline extends Task{
    private String info;
    public Deadline(String taskLine) throws ArrayIndexOutOfBoundsException,IllegalArgumentException{
        super(taskLine);
        checkTask(taskLine);
        buildInfo();
    }

    private void checkTask(String taskLine) throws  ArrayIndexOutOfBoundsException, IllegalArgumentException{
        if (taskLine.length() < 2) {
            throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The description of a Deadline cannot be empty.");
        } else if (!taskLine.contains("/by")) {
            throw new IllegalArgumentException("☹ OOPS!!! The Deadline needs an '/by' time.");
        }
    }

    private void buildInfo() {
        String[] parsedTask = taskLine.split("deadline");
        parsedTask = parsedTask[1].split("/by");
        this.name = parsedTask[0].strip();
        this.dateTime = parsedTask[1].strip();
        setDateTimeLD(dateTime);
        this.info = name + " by: " + dateTime;
    }

    protected String printNew(){
        return "[D][ ] " + info;
    }
    @Override
    public String toString(){
        return info;
    }
    @Override
    public String type(){
        return "D";
    }
}
