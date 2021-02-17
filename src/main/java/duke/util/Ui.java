package duke.util;

import java.util.List;

/**
 * Ui interacts with users to receive input and display output.
 */
public class Ui {

    private String echo(String... msgs) {
        assert msgs.length != 0;

        String msg = msgs[0];
        for (int i = 1; i < msgs.length; i++) {
            msg += "\n" + msgs[i];
        }
        return msg;
    }

    /**
     * Returns greeting messages when Duke starts.
     *
     * @return Greeting message.
     */
    public String displayGreetings() {
        return echo("Hello! I'm Duke",
                "What can I do for you?",
                "Enter \"help\" to see list of commands.");
    }

    /**
     * Lists out commands.
     *
     * @return List of all commands.
     */
    public String displayHelp() {
        return echo("Use \"command -h\" to find out more about the command!\n",
                "List of commands:",
                "bye",
                "clear",
                "deadline DESCRIPTION /by DATE",
                "delete INDEX [INDEX...]",
                "done INDEX [INDEX...]",
                "event DESCRIPTION /at DATE",
                "help",
                "highpriority INDEX",
                "list",
                "load",
                "lowpriority INDEX",
                "sample",
                "save",
                "search {KEYWORD | DATE}",
                "sort",
                "todo DESCRIPTION");
    }

    /**
     * Returns error messages.
     *
     * @param e Error to be displayed.
     * @return Error message.
     */
    public String displayError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Returns goodbye message.
     *
     * @return Goodbye message.
     */
    public String displayExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays list of tasks.
     *
     * @param lst List to be displayed.
     * @return List of tasks.
     */
    public String displayList(List<String> lst) {
        if (lst.isEmpty()) {
            return "No task found!";
        } else {
            return "Here are the tasks in your list:\n" + echo(lst.toArray(String[]::new));
        }
    }

    /**
     * Returns message after tasks are marked as done.
     *
     * @param tasks Tasks marked as done.
     * @return Tasks completed message.
     */
    public String displayCompletedTask(String[] tasks) {
        return String.format("Nice! I've marked %s as done:\n%s",
                isSingleTask(tasks) ? "this task" : "these tasks",
                echo(tasks));
    }

    /**
     * Returns added task and the current number of task in the TaskList.
     *
     * @param task Task added to TaskList.
     * @param size Current size of TaskList.
     * @return Task added message.
     */
    public String displayAddedTask(String task, int size) {
        return echo("Got it. I've added this task:",
                task,
                String.format("Now you have %d task%s in the list.", size, isSingleTask(size) ? "" : "s"));
    }

    /**
     * Return deleted tasks and the current number of task in the TaskList.
     *
     * @param tasks Tasks deleted from TaskList.
     * @param size Current size of TaskList.
     * @return Tasks deleted message.
     */
    public String displayDeletedTask(String[] tasks, int size) {
        return String.format("Noted. I've removed %s:\n", isSingleTask(tasks) ? "this task" : "these tasks")
                + echo(tasks) + "\n"
                + String.format("Now you have %d task%s in the list.", size, isSingleTask(size) ? "" : "s");
    }

    private boolean isSingleTask(String[] arr) {
        return arr.length == 1;
    }

    private boolean isSingleTask(int i) {
        return i == 1;
    }

    /**
     * Returns messages after TaskList has been saved.
     *
     * @return TaskList saved message.
     */
    public String displaySaveMessage() {
        return "TaskList have been saved!";
    }

    /**
     * Returns messages after TaskList has been loaded.
     *
     * @return TaskList loaded message.
     */
    public String displayLoadMessage() {
        return "TaskList loaded successfully!";
    }

    /**
     * Prompts user to save file.
     *
     * @return Save file message.
     */
    public String displaySaveFilePrompt() {
        return "Do you want to save the current tasklist? y/n";
    }

    /**
     * Confirms if user want to delete these tasks.
     *
     * @param numOfTasks Number of delete tasks.
     * @return Delete tasks confirmation message.
     */
    public String displayDeleteTaskPrompt(int numOfTasks) {
        return String.format("Are you sure you want to delete %s? y/n",
                isSingleTask(numOfTasks) ? "this task" : "these tasks");
    }

    /**
     * Returns delete aborted message.
     *
     * @return Cancel delete task message.
     */
    public String displayDeleteAborted() {
        return "Deletion cancelled.";
    }

    /**
     * Returns sort tasklist message.
     *
     * @return Sort tasklist message.
     */
    public String displaySortMessage() {
        return "TaskList have been sorted!";
    }

    /**
     * Display change task priority message.
     *
     * @param isHigh True if high priority, else false.
     * @param task Changed task.
     * @return Change task priority message.
     */
    public String displaySetPriority(boolean isHigh, String task) {
        return echo(String.format("Got it! I've set this task as a %s PRIORITY", isHigh ? "HIGH" : "LOW"), task);
    }

    /**
     * Displays sample data loaded message.
     *
     * @return Sample data loaded message.
     */
    public String displayLoadSampleMessage() {
        return "Sample data have been loaded!";
    }

    /**
     * Prompt for confirmation to delete all tasks.
     *
     * @return Delete all tasks prompt.
     */
    public String displayDeleteAllPrompt() {
        return "This command will delete ALL tasks. Are you sure? y/n";
    }

    /**
     * Display clear all message.
     *
     * @return Clear all message.
     */
    public String displayTasksClearedMessage() {
        return "All tasks cleared";
    }
}
