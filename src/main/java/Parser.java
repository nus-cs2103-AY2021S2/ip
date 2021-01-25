import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Parser {

    public static Command parse(String input) throws DukeException {

        String[] processedInput = input.split(" ");
        String command = processedInput[0];
        String description = processDescription(processedInput);

        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "done":
            if (processedInput.length == 1) {
                throw new DukeException("Please enter a task number to mark done");
            }
            return new DoneCommand(Integer.parseInt(processedInput[1]));
        case "todo":
            return new AddToDo(command, description);
        case "event":
            return new AddEvent(command, description);
        case "deadline":
            try {
                String preProcessedData = input.split(" /by ")[1];
                String[] dateTime = preProcessedData.split(" ");
                String[] date = dateTime[0].split("/");
                int time = Integer.parseInt(dateTime[1]);
                LocalDate deadline = LocalDate.parse(date[0] + "-" + date[1] + "-" + date[2]);
                description = input.split(" /by ")[0].split("deadline ")[1];
                return new AddDeadline(command, description, deadline, time);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DukeException("Please enter a valid format YYYY/MM/DD");
            }
        case "delete":
            if (processedInput.length == 1) {
                throw new DukeException("Please enter a task number to delete");
            }
            return new DeleteCommand(Integer.parseInt(processedInput[1]));
        default:
            throw new DukeException("Invalid command. Please enter a valid one");
        }
        
    }

    public static String processDescription(String[] processedInput) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < processedInput.length; i++) {
            sb.append(processedInput[i] + " ");
        }
        return sb.toString();
    }
}
