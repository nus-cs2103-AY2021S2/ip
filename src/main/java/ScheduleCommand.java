import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private LocalDate targetDate;
    private String successMessage;
    private String errorMessage;

    public ScheduleCommand(String time) {
        this.targetDate = convertToDate(time);
    }

    private LocalDate convertToDate(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate dateTime = LocalDate.parse(time, formatter);
//        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
//        LocalDate dateTime = LocalDateTime.parse(time, inputFormat);
        return dateTime;
    }

    @Override
    public CommandResult execute() throws DukeException {
        try {
            ArrayList<Task> tasksByDate = tasks.getScheduleByDate(this.targetDate);
            String responseToUser = "";

            if (tasksByDate.size() < 1) {
                responseToUser = "Congrats! It's a FREE day.";
            } else {
                responseToUser = "Here are the tasks for this date: ";
                // loop through list and print every task in a new line
                for (int i = 1; i < tasksByDate.size() + 1; i++) {
                    Task curTask = tasksByDate.get(i - 1);
                    String toAdd = "\n" + i + ". " + curTask.toString();
                    responseToUser += toAdd;
                }
            }
            this.successMessage = responseToUser;
            return new CommandResult(successMessage);
        } catch (Exception e) {
            errorMessage = "failed getScheduleByDate";
            return new CommandResult(errorMessage);
        }
    }
}
