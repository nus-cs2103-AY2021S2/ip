package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public TaskList(List<Task> lst) {
        super(lst);
    }

    public void setDone(int index) {
        this.get(index).setCompleted();
    }

}
