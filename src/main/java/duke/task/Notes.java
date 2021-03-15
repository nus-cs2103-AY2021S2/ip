package duke.task;

/**
 * Notes class to store notes
 * This class extends Task so that they can be added to a taskList
 */
public class Notes extends Task {
    /**
     * Constructor for Notes
     *
     * @param task
     */
    public Notes(String task) {
        super(task);
        divideCommand = task.split(" ");

    }

    /**
     * Returns the note Name
     *
     * @return
     */
    public String getNote() {
        String note = "";
        for (int i = 1; i < divideCommand.length; i++) {
            note += i == divideCommand.length - 1
                    ? divideCommand[i] : divideCommand[i] + " ";
        }
        return note;
    }

    @Override
    public String toString() {
        return getNote();
    }
}
