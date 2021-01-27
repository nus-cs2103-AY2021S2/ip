package main.java;

public class Deadline extends Task{
    protected String deadlineBy;
    public Deadline(String description, String by) {
        super(description);
        this.deadlineBy = by;
    }

    public Deadline(String description, String by, int doneInt){
        super(description, doneInt);
        this.deadlineBy = by;
    }

    @Override
    public String toString() {
        return "[D]" +super.toString() + " (by: " + deadlineBy + ")";
    }

    @Override
    public String toSaveFormat(){
        return "D|" + super.toSaveFormat() + "|" + deadlineBy;
    }
}
