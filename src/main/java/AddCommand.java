import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String fullCommand;
    private String[] checkCommands;

    public AddCommand(String fullCommand, String[] checkCommands) {
        this.fullCommand = fullCommand;
        this.checkCommands = checkCommands;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws
            DukeException, IOException, DateTimeParseException, StringIndexOutOfBoundsException {
        Task temp;
        String description;
        String dateTime;

        if (checkCommands[0].equals("todo") || checkCommands[0].equals("deadline") || checkCommands[0].equals("event")) {
            if (checkCommands.length == 1) {
                throw new DescriptionNotFoundException();
            }
            if (checkCommands[0].equals("todo")) {
                description = fullCommand.substring(5).trim();
                temp = new TodoTask(description);
            } else if (checkCommands[0].equals("deadline")) {
                int index = fullCommand.indexOf("/by");
                if (index == -1) {
                    throw new DateTimeNotFoundException();
                }

                description = fullCommand.substring(9, index).trim();
                if (description.isEmpty()) {
                    throw new DescriptionNotFoundException();
                }

                dateTime = fullCommand.substring(index + 3).trim();
                if (dateTime.isEmpty()) {
                    throw new DateTimeNotFoundException();
                }

                String dateString = dateTime.substring(0, 10);
                LocalDate date = LocalDate.
                        parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String timeString = dateTime.substring(10).trim();
                if (timeString.isEmpty()) {
                    temp = new DeadlineTask(description, date);
                } else {
                    LocalTime time = LocalTime.
                            parse(dateTime.substring(10).trim(), DateTimeFormatter.ofPattern("HHmm"));
                    temp = new DeadlineTask(description, date, time);
                }
            } else {
                int index = fullCommand.indexOf("/at");
                if (index == -1) {
                    throw new DateTimeNotFoundException();
                }

                description = fullCommand.substring(6, index).trim();
                if (description.isEmpty()) {
                    throw new DescriptionNotFoundException();
                }

                dateTime = fullCommand.substring(index + 3).trim();
                if (dateTime.isEmpty()) {
                    throw new DateTimeNotFoundException();
                }

                String dateString = dateTime.substring(0, 10);
                LocalDate date = LocalDate.
                        parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String timeString = dateTime.substring(10).trim();
                if (timeString.isEmpty()) {
                    temp = new EventTask(description, date);
                } else {
                    String startTimeString = timeString.substring(0, 4);
                    LocalTime startTime = LocalTime.
                            parse(startTimeString, DateTimeFormatter.ofPattern("HHmm"));
                    String endTimeString = timeString.substring(4).trim();
                    if (endTimeString.isEmpty()) {
                        temp = new EventTask(description, date, startTime);
                    } else {
                        LocalTime endTime = LocalTime.
                                parse(endTimeString, DateTimeFormatter.ofPattern("HHmm"));
                        if (endTime.compareTo(startTime) < 0) {
                            throw new InvalidTimeDurationException();
                        }
                        temp = new EventTask(description, date, startTime, endTime);
                    }
                }
            }
        } else {
            throw new CommandNotValidException();
        }
        tasks.add(temp);
        //TODO change output to UI
        System.out.println("\t____________________________________________________________\n"
                + "\tGot it. I've added this task:\n"
                + "\t   " + temp.toString() + "\n"
                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                + "\t____________________________________________________________\n");
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
