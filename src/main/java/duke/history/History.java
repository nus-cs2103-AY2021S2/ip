package duke.history;

import java.util.Stack;

/**
 * History stores commands in the form of Strings.
 *
 * @author  Nicole Ang
 */
public abstract class History extends Stack<String> {
    public History() {
        super();
    }
}
