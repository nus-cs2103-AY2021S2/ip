public class Todo extends Task{
    private String info;
    public Todo(String[] taskDetails) {
        super(taskDetails);
        buildInfo();
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
