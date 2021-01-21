public class Deadline extends Task{
    private String info;
    public Deadline(String[] taskDetails) throws ArrayIndexOutOfBoundsException,IllegalArgumentException{
        super(taskDetails);
        checkTask();
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
    private void checkTask() throws  ArrayIndexOutOfBoundsException, IllegalArgumentException{
        if (taskDetails.length < 2) throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The description of a Deadline cannot be empty.");
        else{
            for(String s: taskDetails){
                if (s.equals("/by")) return;
            }
            throw new IllegalArgumentException("☹ OOPS!!! The Deadline needs an '/by' time.");
        }
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