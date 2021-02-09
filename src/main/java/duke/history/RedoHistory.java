package duke.history;

public class RedoHistory extends History {
    public RedoHistory() {
        super();
    }

    public String redo() {
        return pop();
    }
}
