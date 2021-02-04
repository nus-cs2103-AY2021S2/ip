package duke;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description, "[T]");
    }

    @Override
    public String toString(){
        return this.typeBox + this.checkBox + " " + this.description;
    }
}