package duke.history;

/**
 * Undo history stores user inputted commands that can be undone in the form of Strings.
 *
 * @author  Nicole Ang
 */
public class UndoHistory extends History {
    public UndoHistory() {
        super();
    }

    /**
     * Returns latest undoable command.
     *
     * @return Latest undoable command.
     */
    public String undo() {
        return pop();
    }
}
