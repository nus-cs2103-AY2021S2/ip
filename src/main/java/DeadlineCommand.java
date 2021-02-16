import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DeadlineCommand extends Command {

    static private final String DATE_FORMAT = "dd/MM/yyyy";
    static private final String TIME_FORMAT = "HHmm";
    static private final String ERROR_MESSAGE =
            "Oops! I didn't catch the details of your Deadline, could you please try again?\n" +
                    "(Note: Date format is 'dd/MM/yyyy', Time format is 'HHmm')";

    public DeadlineCommand(Parser parser, Ui ui, Storage storage) {
        super(parser, ui, storage);
    }
    public String execute() throws DukeException {

        try {
            String taskDesc = parser.getTaskDescription();
            LocalDate date = LocalDate.parse(parser.getDate(),
                    DateTimeFormatter.ofPattern(DATE_FORMAT));
            LocalTime time = LocalTime.parse(parser.getTime(),
                    DateTimeFormatter.ofPattern(TIME_FORMAT));

            Deadline newTask = new Deadline(taskDesc, date, time);
            taskList = taskList.addTask(newTask);

            String output = userInterface.displayTaskAdded(newTask, taskList);
            storage.writeFile(taskList);

            return output;

        } catch (DukeException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }
}
