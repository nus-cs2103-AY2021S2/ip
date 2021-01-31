package duke.task;

public class ToDo extends Task{

    public ToDo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        String done;
        if (this.getDone()) {
            done = "1";
        } else {
            done = "0";
        }
        String string = "T|"+ done + "|" + this.getDesc();
        return string;
    }
}
