public class Task {
    public String type = "";
    boolean done = false;
    String[] taskDetails;
    public Task(String[] taskDetails){
        this.taskDetails = taskDetails;
    }
    public String status(){
        String status = done ?  "X":  " ";
        return status;
    }
    public String printNew(){
        return toString();
    }

    public String type(){
        return "task";
    }
}
