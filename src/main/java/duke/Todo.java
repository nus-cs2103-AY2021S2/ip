package duke;

public class Todo extends Task{
    public Todo(String taskLine) throws ArrayIndexOutOfBoundsException{
        super(taskLine);
        checkTask();
        buildInfo();
    }
    private void checkTask() throws  ArrayIndexOutOfBoundsException{
        if (this.taskLine.length() < 2) {
            throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
    private void buildInfo() {

        String[] taskParsed = taskLine.split("todo");
        this.dateTime ="";
        this.name = taskParsed[1].strip(); // taskParsed[0] == ""
    }
    protected String printNew(){
        return "[T][ ] " + name;
    }
    @Override
    public String toString(){
        return name;
    }
    @Override
    protected String type(){
        return "T";
    }

}
