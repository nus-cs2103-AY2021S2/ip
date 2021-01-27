package duke.tasks;

public class ToDo extends Task {
    public ToDo (String info) {
        super(info, taskType.ToDo);

    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}