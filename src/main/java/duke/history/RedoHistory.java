package duke.history;

/**
 * Redo history stores commands that were undone in the form of Strings.
 *
 * @author  Nicole Ang
 */
public class RedoHistory extends History {
    public RedoHistory() {
        super();
    }

    /**
     * Returns latest redo-able command.
     *
     * @return Latest redo-able command.
     */
    public String redo() {
        return pop();
    }
}
