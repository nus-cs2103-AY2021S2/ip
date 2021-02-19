package duke.task;

/**
 * Notes class to store notes
 * This class extends Task so that they can be added to a taskList
 */
public class Notes extends Task {
    String note;

    /**
     * Constructor for Notes
     *
     * @param task
     */
    public Notes(String task) {
        super(task);
        note = "";
        for (int i = 1; i < super.divideCommand.length; i++) {
            note += i == super.divideCommand.length - 1
                    ? super.divideCommand[i] : super.divideCommand[i] + " ";
        }
    }

    /**
     * Returns the note Name
     *
     * @return
     */
    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return note;
    }
}
