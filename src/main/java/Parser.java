import java.time.LocalDate;

public class Parser {

    /**
     * Returns a String which will guide the UI to output appropriate user texts
     * @param fullCommand String of the user input
     * @param list DukeList object in use
     * @return String
     */
    public static String[] parse(String fullCommand, DukeList list) {
        String[] commandStringArr = fullCommand.split(" ", 2);
        switch (commandStringArr[0]) {
        case "done":
            int taskNumberDone = Integer.parseInt(commandStringArr[1]) - 1;
            list.markAsDone(taskNumberDone);
            commandStringArr[1] = list.get(taskNumberDone).toString();
            return commandStringArr;
        case "delete":
            int taskNumberToDelete = Integer.parseInt(commandStringArr[1]) - 1;
            commandStringArr[1] = list.get(taskNumberToDelete).toString();
            list.delete(taskNumberToDelete);
            return commandStringArr;
        case "todo":
            ToDos todo = new ToDos(commandStringArr[1]);
            list.add(todo);
            commandStringArr[1] = todo.toString();
            return commandStringArr;
        case "deadline":
            String[] deadlineParamArr = commandStringArr[1].split(" /by ");
            LocalDate deadlineDate = LocalDate.parse(deadlineParamArr[1]);
            Deadlines deadline = new Deadlines(deadlineParamArr[0], deadlineDate);
            list.add(deadline);
            commandStringArr[1] = deadline.toString();
            return commandStringArr;
        case "event":
            String[] eventParamArr = commandStringArr[1].split(" /at ");
            LocalDate eventDate = LocalDate.parse(eventParamArr[1]);
            Events event = new Events(eventParamArr[0], eventDate);
            list.add(event);
            commandStringArr[1] = event.toString();
            return commandStringArr;
        case "reset":
            list.deleteAll();
            return commandStringArr;
        default:
            return commandStringArr;
        }
    }
}
