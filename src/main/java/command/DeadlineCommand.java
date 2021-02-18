package command;

import task.Deadline;
import task.TaskManager;
import util.DukeException;
import util.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;

/**
 * Command to add a Deadline Task to a TaskManager.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_STRING = Deadline.COMMAND_STRING;
    public static final CommandType COMMAND_TYPE = CommandType.DEADLINE;

    private final String description;
    private final LocalDate date;

    /**
     * Creates a DeadlineCommand that would add a Deadline with the supplied
     * description and date to a TaskManager when executed.
     *
     * @param description Description of the Deadline to be added.
     * @param date Date of the Deadlien to be added.
     */
    private DeadlineCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Constructs a DeadlineCommand from a commandMap.
     *
     * @param commandMap CommandMap representing the instruction.
     * @return DeadlineCommand object based on the commandMap.
     * @throws DukeException When the user inputs an illegal instruction for the
     * Deadline command.
     */
    public static DeadlineCommand fromCommandMap(HashMap<String, List<String>> commandMap)
            throws DukeException {
        String description;
        LocalDate date;

        assert Parser.extractCommandString(commandMap).equals(COMMAND_STRING)
                : COMMAND_STRING + "CommandFlag does not match";

        // Validate description
        List<String> descriptionStrings = commandMap.get(COMMAND_STRING);
        if (descriptionStrings.isEmpty()) {
            throw new DukeException("Deadline description cannot be empty");
        }
        description = String.join(" ", descriptionStrings);

        // Validate date
        try {
            List<String> dateStrings = commandMap.get(Deadline.END_DATE_STRING);
            String dateString = dateStrings.get(0);
            date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (NullPointerException e) {
            throw new DukeException("Please provide an end date");
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input date in the form YYYY-MM-DD");
        }

        return new DeadlineCommand(description, date);
    }

    /**
     * Performs the specified action on the supplied TaskManager.
     *
     * @param taskManager TaskManager object to perform the action on.
     * @return String response of the action that was performed.
     */
    @Override
    public String execute(TaskManager taskManager) {
        return taskManager.addTask(new Deadline(description, date));
    }
}
