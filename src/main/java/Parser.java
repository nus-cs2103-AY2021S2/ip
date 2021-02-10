import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class that can create a Parser object that can interpret the user
 * commands.
 */
public class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done ";
    private static final String COMMAND_DELETE = "delete ";
    private static final String COMMAND_FIND = "find ";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    /**
     * Method that interprets and executes the user command accordingly.
     *
     * @param listOfTasks the list of tasks for the user.
     * @param textInput the command that was given by the user.
     */
    public String executeCommand(TaskList listOfTasks, String textInput) {
        String output = "";
        if (textInput.toLowerCase().equals(COMMAND_LIST)) {
            output = executeCommandList(listOfTasks, textInput);
        } else if (textInput.startsWith(COMMAND_DONE)) {
            output = executeCommandDone(listOfTasks, textInput);
        } else if (textInput.startsWith(COMMAND_DELETE)) {
            output = executeCommandDelete(listOfTasks, textInput);
        } else if (textInput.startsWith(COMMAND_FIND)) {
            output = executeCommandFind(listOfTasks, textInput);
        } else if (textInput.startsWith(COMMAND_TODO)) {
            output = executeCommandToDo(listOfTasks, textInput);
        } else if (textInput.startsWith(COMMAND_DEADLINE)) {
            output = executeCommandDeadline(listOfTasks, textInput);
        } else if (textInput.startsWith(COMMAND_EVENT)) {
            output = executeCommandEvent(listOfTasks, textInput);
        } else {
            output += "\t OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
        assert !output.equals("");
		return output;
    }

    /**
     * Method that executes the list command.
     *
     * @param listOfTasks the list of tasks to manage using commands.
     * @param textInput the full command that needs to be executed.
     * @return the output to be displayed on the user interface.
     */
    public String executeCommandList(TaskList listOfTasks, String textInput) {
        String output = "";
        int i = 1;
        output += "\t Here are the tasks in your list:\n";
        for (Task task : listOfTasks) {
            output += "\t " + i + "." + task + "\n";
            i++;
        }
        return output;
    }

    /**
     * Method that executes the done command.
     *
     * @param listOfTasks the list of tasks to manage using commands.
     * @param textInput the full command that needs to be executed.
     * @return the output to be displayed on the user interface.
     */
    public String executeCommandDone(TaskList listOfTasks, String textInput) {
        String output = "";
        try {
            int i = Integer.parseInt(textInput.substring(5));
            Task task = listOfTasks.get(i - 1);
            task.completeTask();
            output += "\t Nice! I've marked this task as done:\n";
            output += "\t   " + task + "\n";
        } catch (NumberFormatException e) {
            output += "INVALID COMMAND!\n";
        } catch (StringIndexOutOfBoundsException e) {
            output += "INVALID COMMAND!\n";
        } catch (IndexOutOfBoundsException e) {
            output += "INVALID COMMAND!\n";
        }
        return output;
    }

    /**
     * Method that executes the delete command.
     *
     * @param listOfTasks the list of tasks to manage using commands.
     * @param textInput the full command that needs to be executed.
     * @return the output to be displayed on the user interface.
     */
    public String executeCommandDelete(TaskList listOfTasks, String textInput) {
        String output = "";
        try {
            int i = Integer.parseInt(textInput.substring(7));
            Task task = listOfTasks.get(i - 1);
            listOfTasks.remove(i - 1);
            output += "\t Noted. I've removed this task:\n";
            output += "\t   " + task + "\n";
            output += "\t Now you have " + listOfTasks.size() + " tasks in the list.\n";
        } catch (NumberFormatException e) {
            output += "INVALID COMMAND!\n";
        } catch (StringIndexOutOfBoundsException e) {
            output += "INVALID COMMAND!\n";
        } catch (IndexOutOfBoundsException e) {
            output += "INVALID COMMAND!\n";
        }
        return output;
    }

    /**
     * Method that executes the find command.
     *
     * @param listOfTasks the list of tasks to manage using commands.
     * @param textInput the full command that needs to be executed.
     * @return the output to be displayed on the user interface.
     */
    public String executeCommandFind(TaskList listOfTasks, String textInput) {
        String output = "";
        String textToFind = textInput.substring(5);
        if (!textToFind.isBlank()) {
            output += "\t Here are the matching tasks in your list:\n";
            int i = 1;
            for (Task task : listOfTasks) {
                if (isPartialMatch(task, textToFind)) {
                    output += "\t " + i + ". " + task + "\n";
                }
                i++;
            }
        } else {
            output += "\t OOPS!!! The description of find cannot be empty.\n";
        }
        return output;
    }

    /**
     * Method that finds if a task contains any substring from a given keyword.
     *
     * @param task the task to find the substring in.
     * @param keyword the keyword with substrings to find in the task.
     * @return true if the task matches any part of the keyword.
     */
    public boolean isPartialMatch(Task task, String keyword) {
        for (int i = 3; i <= keyword.length(); i++) {
            for (int j = 0; j <= keyword.length() - i; j++) {
                String subString = keyword.substring(j, j + i);
                if (task.contains(subString)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method that executes the todo command.
     *
     * @param listOfTasks the list of tasks to manage using commands.
     * @param textInput the full command that needs to be executed.
     * @return the output to be displayed on the user interface.
     */
    public String executeCommandToDo(TaskList listOfTasks, String textInput) {
        String output = "";
        try {
            textInput = textInput.substring(5);
            Task task = new ToDo(textInput);
            listOfTasks.add(task);
            output += "\t Got it. I've added this task:\n";
            output += "\t   " + task + "\n";
            output += "\t Now you have " + listOfTasks.size() + " tasks in the list.\n";
        } catch (StringIndexOutOfBoundsException e) {
            output += "\t OOPS!!! The description of a todo cannot be empty.\n";
        }
        return output;
    }

    /**
     * Method that executes the deadline command.
     *
     * @param listOfTasks the list of tasks to manage using commands.
     * @param textInput the full command that needs to be executed.
     * @return the output to be displayed on the user interface.
     */
    public String executeCommandDeadline(TaskList listOfTasks, String textInput) {
        String output = "";
        try {
            textInput = textInput.substring(9);
            int index = textInput.indexOf("/by ");
            if (index != -1) {
                String dateInfo = textInput.substring(index + 4);
                LocalDate localDate = LocalDate.parse(dateInfo);
                Task task = new Deadline(textInput.substring(0, index - 1), localDate);
                listOfTasks.add(task);
                output += "\t Got it. I've added this task:\n";
                output += "\t   " + task + "\n";
                output += "\t Now you have " + listOfTasks.size() + " tasks in the list.\n";
            } else {
                output += "INVALID COMMAND!\n";
            }
        } catch (StringIndexOutOfBoundsException e) {
            output += "\t OOPS!!! The description of a deadline cannot be empty.\n";
        } catch (DateTimeParseException e) {
            output += "INVALID COMMAND!\n";
        }
        return output;
    }

    /**
     * Method that executes the event command.
     *
     * @param listOfTasks the list of tasks to manage using commands.
     * @param textInput the full command that needs to be executed.
     * @return the output to be displayed on the user interface.
     */
    public String executeCommandEvent(TaskList listOfTasks, String textInput) {
        String output = "";
        try {
            textInput = textInput.substring(6);
            int index = textInput.indexOf("/from ");
            if (index != -1) {
                String dateInfo = textInput.substring(index + 6);
                int index2 = dateInfo.indexOf("to ");
                if (index2 != -1) {
                    LocalDate startDate = LocalDate.parse(dateInfo.substring(0, index2 - 1));
                    LocalDate endDate = LocalDate.parse(dateInfo.substring(index2 + 3));
                    Task task = new Event(textInput.substring(0, index - 1), startDate, endDate);
                    listOfTasks.add(task);
                    output += "\t Got it. I've added this task:\n";
                    output += "\t   " + task + "\n";
                    output += "\t Now you have " + listOfTasks.size() + " tasks in the list.\n";
                } else {
                    output += "INVALID COMMAND!\n";
                }
            } else {
                output += "INVALID COMMAND!\n";
            }
        } catch (StringIndexOutOfBoundsException e) {
            output += "\t OOPS!!! The description of a event cannot be empty.\n";
        } catch (DateTimeParseException e) {
            output += "INVALID COMMAND!\n";
        }
        return output;
    }
}
