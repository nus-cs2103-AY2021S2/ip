package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * The StatsCommand class encapsulates information and methods about a StatsCommand.
 */
public class StatsCommand implements Command {
    private String fullCmd;
    private String[] fullCmdStrArray;
    private Ui ui;

    /**
     * Create and initialize a Stats Command.
     *
     * @param fullCmd The full user input in String form.
     * @param ui The ui object responsible for displaying Stats messages to the CLI.
     */
    public StatsCommand(String fullCmd, Ui ui) {
        this.fullCmd = fullCmd;
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    /**
     * Processes the Stats command by collecting statistics from the Tasklist.
     *
     * @param storage The storage object that writes data to the saved data file of tasks.
     * @param taskList The list of tasks.
     * @throws DukeException if the format of the Stats command is invalid.
     */
    @Override
    public String run(Storage storage, TaskList taskList) throws DukeException {
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Tasklist cannot be null";

        if (fullCmdStrArray.length != 1) { // handle stats with unnecessary parameters
            throw new DukeException(ui.statsCmdError());
        }

        int numTotalTasks = taskList.getSize();
        int numDoneTasks = taskList.getNumDoneTasks();
        int numDoneTasksWithinWeek = taskList.getNumDoneTasksWithinWeek();
        int numUpcomingTasksWithinWeek = taskList.getNumUpcomingTasksWithinWeek();

        return (ui.returnStatsMessage(numTotalTasks, numDoneTasks, numDoneTasksWithinWeek, numUpcomingTasksWithinWeek));
    }




}
