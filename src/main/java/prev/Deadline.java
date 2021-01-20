package prev;
//prev here refers to codes for level 1,2,3

public class Deadline extends Task {
    protected String by;

    public Deadline (String decs, String by){
        super(decs);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

}
