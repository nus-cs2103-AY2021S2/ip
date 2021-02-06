package duke.util;

import java.util.List;

/**
 * Ui interacts with users to receive input and display output.
 */
public class Ui {

    private String echo(String... msgs) {
        String msg = "";
        for (String s : msgs) {
            msg += s + "\n";
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
     * Lists out commands and their functions.
     *
     * @return Help message.
     */
    public String displayHelp() {
        return echo("List of commands:",
                "bye",
                "- Close Duke",
                "list",
                "  - List out all task",
                "done [number]",
                "  - Mark selected task as done",
                "todo [description]",
                "  - Add a todo task",
                "deadline [description] /by [due date]",
                "  - Add a deadline task with a due date (YYYY-MM-DD)",
                "event [description] /at [date]",
                "  - Add a event task with a date (YYYY-MM-DD)",
                "delete [number]",
                "  - Delete a task",
                "save",
                "  - save checklist to \"data/dukeData.txt\"",
                "load",
                "  - Load previously saved checklist",
                "help",
                "  - Display list of commands",
                "search [keyword]",
                "  - Display all task containing the following keyword.",
                "  - If keyword is in a valid date format(YYYY-MM-DD), display all task on that date.");
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
     * Returns message after task is marked as done.
     *
     * @param task Task marked as done.
     * @return Task done message.
     */
    public String completeTask(String task) {
        return echo("Nice! I've marked this task as done:", task);
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
                "Now you have " + size + " tasks in the list.");
    }

    /**
     * Returns deleted task and the current number of task in the TaskList.
     *
     * @param task Task deleted from TaskList.
     * @param size Current size of TaskList.
     * @return Delete task message.
     */
    public String deleteTask(String task, int size) {
        return echo("Noted. I've removed this task:",
                task,
                "Now you have " + size + " tasks in the list.");
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
     * Confirms if user want to delete this task.
     *
     * @return Delete task confirmation message.
     */
    public String displayDeleteTaskPrompt() {
        return "Are you sure you want to delete this task? y/n";
    }

    /**
     * Returns delete aborted message.
     *
     * @return Cancel delete task message.
     */
    public String abortDelete() {
        return "Deletion cancelled.";
    }
}
