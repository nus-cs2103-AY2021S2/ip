public class Event extends Task{
    private String info;
    public Event(String[] taskDetails) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        super(taskDetails);
        checkTask();
        buildInfo();
    }
    private void buildInfo(){
        String output = "";
        int i = 1;
        while(!taskDetails[i].equals("/at")) {
            output += taskDetails[i] + " ";
            i++;
        }
        i++;
        output =  output + "(at: " ;
        while(i < taskDetails.length){
            output += taskDetails[i] + " ";
            i++;
        }
        info = output + ")";
    }
    private void checkTask() throws  ArrayIndexOutOfBoundsException, IllegalArgumentException{
        if (taskDetails.length < 2) throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The description of a Event cannot be empty.");
        else{
            for(String s: taskDetails){
                if (s.equals("/at")) return;
            }
            throw new IllegalArgumentException("☹ OOPS!!! The Event needs an '/at' time.");
        }
    }
    public String printNew(){
        return "[E][ ] " + info;
    }
    public String toString(){
        return info;
    }
    @Override
    public String type(){
        return "E";
    }
}
