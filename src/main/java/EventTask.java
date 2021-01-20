public class EventTask extends Task{
    EventTask(String task) {
        super(task);
    }

    String[] divideCommand = task.split(" ");

    @Override
    public String toString(){
        String taskRepresent = divideCommand[1] + " " + divideCommand[2]
                + " (" + divideCommand[3].substring(1) + ": " + divideCommand[4]
                + " " + divideCommand[5] + ")";
        if(this.getStatus()){
            return "[D][X] " + taskRepresent;
        }else{
            return "[D][ ] " + taskRepresent;
        }
    }
}
