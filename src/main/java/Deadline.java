public class Deadline extends Task{
    String deadline;
    Deadline(String name, int id,Boolean isDone, String deadline){
        super(name,id,isDone);
        this.deadline = deadline;
    }
    public String getDeadline(){
        return this.deadline;
    }
    @Override
    Deadline finish(){
        return new Deadline(this.getName(),this.getId(), true, this.getDeadline()) ;
    }

    @Override
    public String toString(){
        if(this.getIsDone()){
            return "[D][X] "+ this.getId() + "." + this.getName() + "(" + this.getDeadline() + ")";
        }
        else{
            return "[D][ ] "+ this.getId() + "." + this.getName() + "(" + this.getDeadline() + ")";
        }
    }
}
