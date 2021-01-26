public class Task {
    protected String type = "";
    boolean isDone = false;
    String taskLine;
    String name;
    String dateTime;

    public Task(String taskLine) {
        this.taskLine = taskLine;
        this.name = "";
        this.dateTime = "";
    }
    protected String status(){
        String status = isDone ?  "X":  "O";
        return status;
    }
    protected String printNew(){
        return toString();
    }

    protected String type(){
        return "task";
    }
}
