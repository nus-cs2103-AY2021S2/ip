package duke.util;

import java.util.List;

/**
 * Ui interacts with users to receive input and display output.
 */
public class Ui {

    private String echo(List<String> msgs) {
        String msg = "";
        for (String s : msgs) {
            msg += s + "\n";
        }
        return msg;
    }

    private String echoSingle(String msg) {
        return msg;
    }

    /**
     * Displays greeting messages when Duke starts.
     */
    public String displayGreetings() {
        return echo(List.of("Hello! I'm Duke",
                "What can I do for you?",
                "Enter \"help\" to see list of commands."));
    }

    /**
     * Lists out commands and their functions.
     */
    public String help() {
        return echo(List.of("List of commands:",
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
                "  - If keyword is in a valid date format(YYYY-MM-DD), display all task on that date."));
    }

    /**
     * Displays error messages.
     * 
     * @param e Error to be displayed.
     */
    public String displayError(DukeException e) {
        return echoSingle(e.getMessage());
    }

    /**
     * Displays goodbye message.
     */
    public String exit() {
        return echoSingle("Bye. Hope to see you again soon!");
    }

    /**
     * Displays list contents.
     * 
     * @param lst List to be displayed.
     */
    public String displayList(List<String> lst) {
        if (lst.size() == 0) {
            return echoSingle("No task found!");
        } else {
            return "Here are the tasks in your list:\n" + echo(lst);
        }
    }

    /**
     * Displays message after task is marked as done.
     * 
     * @param task Task  marked as done.
     */
    public String completeTask(String task) {
        return echo(List.of("Nice! I've marked this task as done:", task));
    }

    /**
     * Displays added task and the current number of task in the TaskList.
     * 
     * @param task Task added to TaskList.
     * @param size Current size of TaskList.
     */
    public String addTask(String task, int size) {
        return echo(List.of("Got it. I've added this task:",
                task,
                "Now you have " + size + " tasks in the list."));
    }

    /**
     * Displays deleted task and the current number of task in the TaskList.
     * 
     * @param task Task deleted from TaskList.
     * @param size Current size of TaskList.
     */
    public String deleteTask(String task, int size) {
        return echo(List.of("Noted. I've removed this task:",
                task,
                "Now you have " + size + " tasks in the list."));
    }

    /**
     * Displays messages after TaskList has been saved.
     */
    public String displaySaveMessage() {
        return echoSingle("TaskList have been saved!");
    }

    /**
     * Displays messages after TaskList has been loaded.
     */
    public String displayLoadMessage() {
        return echoSingle("TaskList loaded successfully!");
    }

    /**
     * Prompts user to save file.
     * 
     * @return Response from user.
     */
    public String saveFilePrompt() {
        return echoSingle("Do you want to save the current tasklist? y/n");
    }

    /**
     * Confirms if user want to delete this task.
     * 
     * @return Response from user.
     */
    public String deleteTaskPrompt() {
        return echoSingle("Are you sure you want to delete this task? y/n");
    }

    /**
     * Displays delete aborted message.
     */
    public String abortDelete() {
        return echoSingle("Deletion cancelled.");
    }
}
