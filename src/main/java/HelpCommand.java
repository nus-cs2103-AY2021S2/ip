package duke;

/** Help command is used when user wants to find all tasks that contain a certain keyword. */
public class HelpCommand extends Command {

    /** Initialises find command with keyword. */
    public HelpCommand() {
        super("here are some commands you can try!\n" + 
        	/*"to add a new task: todo DESCRIPTION / deadline DESCRIPTION DUEDATE / event DESCRIPTION DATETIME\n" + 
        	"to see all tasks you have: list\n" + 
        	"to remove a task: delete INDEX\n" + 
        	"to mark a task as done: done INDEX\n" + 
        	"to find all tasks containing a word: find KEYWORD\n" + 
        	"to exit: bye\n" + 
        	"datetime to be in YYYY-MM-DD format"*/
        	"todo DESCRIPTION / deadline DESCRIPTION by DUEDATE / event DESCRIPTION at DATETIME" + 
        	"/ delete INDEX / done INDEX / list / find KEYWORD / bye " + "(datetime format: yyyy-mm-dd)");
    }

    /** Executes find command to present all tasks containing keyword.
     * @param manager Helper that manages the stored tasks.
     * @param ui Interface helper that decides what user sees.
     * @param storage Storage that interacts with information stored on harddrive.
     */
    public String execute(TaskManager manager, Ui ui, Storage storage) {
        return this.message;
    }
}
