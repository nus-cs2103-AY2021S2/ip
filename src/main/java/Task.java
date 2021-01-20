public class Task {
    boolean done = false;
    String taskName;
    public Task(String taskName){
        this.taskName = taskName;
    }
    public String status(){
        String status = done ?  "X":  " ";
        return status;
    }
}
