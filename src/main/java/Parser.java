import java.time.LocalDate;

public class Parser {

    /**
     * Returns a String which will guide the UI to output appropriate user texts
     * @param fullCommand String of the user input
     * @param list DukeList object in use
     * @return String
     */
    public static String parse(String fullCommand, DukeList list) {
        String[] commandStringArr = fullCommand.split(" ", 2);
        String commandName = commandStringArr[0];
        switch (commandName) {
        case "done":
            list.markAsDone(Integer.parseInt(commandStringArr[1]) - 1);
            return "done";
        case "delete":
            list.delete(Integer.parseInt(commandStringArr[1]) - 1);
            return "delete";
        case "todo":
            ToDos todo = new ToDos(commandStringArr[1]);
            list.add(todo);
            return "todo";
        case "deadline":
            String[] deadlineParamArr = commandStringArr[1].split(" /by ");
            LocalDate deadlineDate = LocalDate.parse(deadlineParamArr[1]);
            Deadlines deadline = new Deadlines(deadlineParamArr[0], deadlineDate);
            list.add(deadline);
            return "deadline";
        case "event":
            String[] eventParamArr = commandStringArr[1].split(" /at ");
            LocalDate eventDate = LocalDate.parse(eventParamArr[1]);
            Events event = new Events(eventParamArr[0], eventDate);
            list.add(event);
            return "event";
        case "show":
            LocalDate date = LocalDate.parse(commandStringArr[1]);
            list.setDate(date);
            return "show";
        case "reset":
            list.deleteAll();
            return "reset";
        case "list":
            return "list";
        case "bye":
            return "bye";
        default:
            return "unknown";
        }
    }
}
