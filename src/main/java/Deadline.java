public class Deadline extends Task{
    String deadline;
    Deadline(String name, int id, String deadline){
        super(name,id);
        this.deadline = deadline;
    }
    @Override
    public String toString(){
        if(this.isDone){
            return "[D][X] "+ this.id + "." + this.name + "(" + this.deadline + ")";
        }
        else{
            return "[D][ ] "+ this.id + "." + this.name + "(" + this.deadline + ")";
        }
    }
}
