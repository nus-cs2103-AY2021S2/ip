import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class that can create a Parser object that can interpret the user
 * commands.
 */
public class Parser {
    /**
     * Method that interprets and executes the user command accordingly.
     *
     * @param listOfTasks the list of tasks for the user.
     * @param textInput the command that was given by the user.
     */
    public String executeCommand(TaskList listOfTasks, String textInput) {
        String output = "";
        if (textInput.toLowerCase().equals("list")) {
            int i = 1;
            output += "\t Here are the tasks in your list:\n";
            for (Task task : listOfTasks) {
                output += "\t " + i + "." + task + "\n";
                i++;
            }
        } else if (textInput.startsWith("done ")) {
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
        } else if (textInput.startsWith("delete ")) {
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
        } else if (textInput.startsWith("find ")) {
            String textToFind = textInput.substring(5);
            if (!textToFind.isBlank()) {
                output += "\t Here are the matching tasks in your list:\n";
                int i = 1;
                for (Task task : listOfTasks) {
                    if (task.contains(textToFind)) {
                        output += "\t " + i + ". " + task + "\n";
                    }
                    i++;
                }
            } else {
                output += "\t OOPS!!! The description of find cannot be empty.\n";
            }
        } else if (textInput.startsWith("todo")) {
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
        } else if (textInput.startsWith("deadline")) {
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
        } else if (textInput.startsWith("event")) {
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
        } else {
            output += "\t OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }

        return output;
    }
}
