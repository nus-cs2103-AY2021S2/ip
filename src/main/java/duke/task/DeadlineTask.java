package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class handles the deadlineTasks
 */
public class DeadlineTask extends Task {
    private LocalDate date;

    public DeadlineTask(String task) {
        super(task);
    }

    String[] divideCommand = task.split(" ");

    @Override
    public String toString() {
        String time = "";
        for (int i = 4; i < divideCommand.length; i++) {
            if (i == divideCommand.length - 1) {
                time += divideCommand[i];
            } else {
                time += divideCommand[i] + " ";
            }
        }
        date = LocalDate.parse(time);
        String dateFormat = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String taskRepresent = divideCommand[1] + " " + divideCommand[2]
                + " (" + divideCommand[3].substring(1) + ": " + dateFormat + ")";
        if (this.isDone()) {
            return "[D][X] " + taskRepresent;
        } else {
            return "[D][ ] " + taskRepresent;
        }
    }
}
