public class Deadline extends Task {
    protected String afterBy;
    public Deadline (String info, String afterBy) {
        super(info);
        this.afterBy = afterBy;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by:" + this.afterBy + ")";
    }
}

