import java.time.LocalDate;

public class Parser {

    public String Parse(String fullCommand, DukeList list) {
        String[] commandStringArr = fullCommand.split(" ", 2);
        String commandName = commandStringArr[0];
        switch (commandName) {
        case "done":
            list.done(Integer.parseInt(commandStringArr[1]));
            return "done";
        case "delete":
            list.delete(Integer.parseInt(commandName));
            return "delete";
        case "reset":
            list.deleteAll();
            return "reset";
        case "list":
            return "list";
        case "show":
            LocalDate date = LocalDate.parse(commandStringArr[1]);
            list.showTaskOnDay(date); // change to return a string instead
            return "show";
        case "todo":
            ToDos todo = new ToDos(commandStringArr[1]);
            list.add(todo);
        case "deadline":
            LocalDate deadlineDate = LocalDate.parse(commandStringArr[3]);
            Deadlines deadline = new Deadlines(commandStringArr[1], deadlineDate);
            list.add(deadline);
        case "event":
            LocalDate eventDate = LocalDate.parse(commandStringArr[3]);
            Events event = new Events(commandStringArr[1], eventDate);
            list.add(event);
        case "bye":
            return "bye";
        default:
            return "unknown";
        }
    }
}
