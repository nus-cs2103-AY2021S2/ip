package duke.gui;

import java.util.ArrayList;
import java.util.List;

/**
 * History records and retrieves user's GUI input history.
 */
public class History {
    private List<String> lst = new ArrayList<>();
    private int current = 0;

    /**
     * Add new input to history if it is different from the previous input.
     *
     * @param s User input.
     */
    public void add(String s) {
        if (lst.isEmpty() || isDifferentFromLast(s)) {
            lst.add(s);
        }

        current = lst.size();
    }

    private boolean isDifferentFromLast(String s) {
        return !s.equals(lst.get(lst.size() - 1));
    }

    /**
     * Retrieve the previous input in history.
     *
     * @return Input from history.
     */
    public String getPrevious() {
        if (lst.isEmpty()) {
            return "";
        } else if (current == 0) {
            return lst.get(current);
        } else {
            return lst.get(--current);
        }
    }

    /**
     * Retrieve the next input in history.
     *
     * @return Input from history.
     */
    public String getNext() {
        if (lst.isEmpty()) {
            return "";
        } else if (current == lst.size()) {
            return "";
        } else if (current == lst.size() - 1) {
            current++;
            return "";
        } else {
            return lst.get(++current);
        }
    }
}
