package duke.tasks;

public class ToDo extends Task {
    public ToDo (String info) {
        super(info, taskType.ToDo);

    }

    public ToDo(String info, boolean isDone) {
        super(info, taskType.ToDo, isDone);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}