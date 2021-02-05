package duke;

/** Event command is used when user wants to add an event task. */
public class EventCommand extends AddCommand {

    /** Initialises event command with its description. */
    public EventCommand(String description) {
        super(description);
    }

    /**
     * Executes event command to add event to tasklist.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */ 
    public void execute(TaskManager manager, Ui ui, Storage storage) {
        String trimmed = this.description.replaceAll("event ", "");
        Task t = new Event(trimmed.split(" at ")[0], trimmed.split(" at ")[1]);
        manager.addTask(t);
        this.message += t.toString() + "\n" 
            + String.format("Now you have %s tasks in the list.", manager.taskVolume());
        storage.writeToDisk(manager.getStore());
        ui.replyWith(this.message);
    } 
}
