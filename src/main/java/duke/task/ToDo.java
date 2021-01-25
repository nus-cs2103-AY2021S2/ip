package duke.task;

import duke.task.Task;

public class ToDo extends Task {

    public ToDo(String title, Boolean b) {
        super(title, b);
    }

    public ToDo(String title) {
        super(title);
    }

    @Override
    public String changeFormat(){
        return "T" + super.changeFormat();
    }

    @Override
    public String toString() {
        return  "[T]" + super.toString();
    }
}
