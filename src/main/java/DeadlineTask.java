public class DeadlineTask extends Task{
    DeadlineTask(String task) {
        super(task);
    }

    String[] divideCommand = task.split(" ");

    @Override
    public String toString(){
        String taskRepresent = divideCommand[1] + " " + divideCommand[2]
                + " (" + divideCommand[3].substring(1) + " " + divideCommand[4] + ")";
        if(this.getStatus()){
            return "[D][X] " + taskRepresent;
        }else{
            return "[D][ ] " + taskRepresent;
        }
    }
}
