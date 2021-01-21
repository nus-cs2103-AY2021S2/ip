public class Deadline extends Task{
    protected String by;

    public Deadline(String content,String by){
        super(content);
        this.by = by;
    }

    @Override
    public String toString() {
        if(!this.done){
            return "[D][ ] " + super.toString() + " (by: " + by + ")";
        }else {
            return "[D][X] " + super.toString() + " (by: " + by + ")";
        }
    }
}
