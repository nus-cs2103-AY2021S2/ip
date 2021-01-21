public class Deadline extends Task{
    private String info;
    public Deadline(String[] taskDetails) {
        super(taskDetails);
        buildInfo();
    }

    private void buildInfo(){
        String output = "";
        int i = 1;
        while(!taskDetails[i].equals("/by")) {
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