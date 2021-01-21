public class Todo extends Task{
    private String info;
    public Todo(String[] taskDetails) throws ArrayIndexOutOfBoundsException{
        super(taskDetails);
        checkTask();
        buildInfo();
    }
    private void checkTask() throws  ArrayIndexOutOfBoundsException{
        if (this.taskDetails.length < 2) throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The description of a todo cannot be empty.");
    }
    private void buildInfo(){
        String output = "";
        for(String s: taskDetails ){
            output += s + " ";
        }
        info = output;
    }
    public String printNew(){
        return "[T][ ] " + info;
    }
    @Override
    public String toString(){
        return info;
    }
    @Override
    public String type(){
        return "T";
    }

}
