public class Deadline extends Task{
    String deadline;
    Deadline(String name,Boolean isDone, String deadline){
        super(name,isDone);
        this.deadline = deadline;
    }
    public String getDeadline(){
        return this.deadline;
    }
    @Override
    Deadline finish(){
        return new Deadline(this.getName(), true, this.getDeadline()) ;
    }

    @Override
    public String toString(){
        if(this.getIsDone()){
            return "[D][X] " + this.getName() + "(" + this.getDeadline() + " )";
        }
        else{
            return "[D][ ] "  + this.getName() + "(" + this.getDeadline() + " )";
        }
    }
}
