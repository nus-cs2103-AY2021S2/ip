package duke.task;

import duke.task.Task;

public class ToDos extends Task {

    public ToDos(String title, Boolean b) {
        super(title, b);
    }

    public ToDos(String title) {
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
