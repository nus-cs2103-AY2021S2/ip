public class Event extends Task{
    private String info;
    public Event(String[] taskDetails) {
        super(taskDetails);
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
