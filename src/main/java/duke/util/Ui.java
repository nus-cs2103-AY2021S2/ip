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
     * @return Help message.
     */
    public String displayHelp() {
        return echo("Use \"command -h\" to find out more about the command!\n",
                "List of commands:",
                "bye",
                "deadline [description] /by [due date]",
                "delete [int (int int...)]",
                "done [int (int int...)]",
                "event [description] /at [date]",
                "help",
                "list",
                "load",
                "save",
                "search search [keyword | date]",
                "sort",
                "todo [description]");
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
     * Returns list contents.
     *
     * @param lst List to be displayed.
     * @return Content of list.
     */
    public String displayList(List<String> lst) {
        if (lst.size() == 0) {
            return "No task found!";
        } else {
            return "Here are the tasks in your list:\n" + echo(lst.toArray(new String[0]));
        }
    }

    /**
     * Returns message after tasks are marked as done.
     *
     * @param tasks Tasks marked as done.
     * @return Tasks done message.
     */
    public String completeTask(String... tasks) {
        return String.format("Nice! I've marked %s as done:\n%s",
                tasks.length == 1 ? "this task" : "these tasks",
                echo(tasks));
    }

    /**
     * Returns added task and the current number of task in the TaskList.
     *
     * @param task Task added to TaskList.
     * @param size Current size of TaskList.
     * @return Add task message.
     */
    public String addTask(String task, int size) {
        return echo("Got it. I've added this task:",
                task,
                String.format("Now you have %d task%s in the list.", size, size == 1 ? "" : "s"));
    }

    /**
     * Return deleted tasks and the current number of task in the TaskList.
     *
     * @param tasks Tasks deleted from TaskList.
     * @param size Current size of TaskList.
     * @return Delete tasks message.
     */
    public String deleteTask(String[] tasks, int size) {
        return String.format("Noted. I've removed %s:\n", tasks.length == 1 ? "this task" : "these tasks")
                + echo(tasks) + "\n"
                + String.format("Now you have %d task%s in the list.", size, size == 1 ? "" : "s");
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
     * @param isSingle Is a single or multiple task(s).
     * @return Delete tasks confirmation message.
     */
    public String displayDeleteTaskPrompt(boolean isSingle) {
        return String.format("Are you sure you want to delete %s? y/n",
                isSingle ? "this task" : "these tasks");
    }

    /**
     * Returns delete aborted message.
     *
     * @return Cancel delete task message.
     */
    public String abortDelete() {
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
}
