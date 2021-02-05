package duke;

/** Encapsulates information of a possible command from user. */
public abstract class Command {
    protected String message;

    /** Initialises each command with a standard message. */
    public Command(String msg) {
        this.message = msg;
    }

    /** 
     * Execute each command to achieve respective effect.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public abstract void execute(TaskManager manager, Ui ui, Storage storage);
}
