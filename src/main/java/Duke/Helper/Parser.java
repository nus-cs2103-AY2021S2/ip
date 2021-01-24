package Duke.Helper;

import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Todo;

import Duke.Exception.EmptyTaskException;
import Duke.Exception.InvalidTask;

public class Parser {
    public Todo parseTodo(String command) throws EmptyTaskException {
        String description = command.substring(5);
        if (description.isEmpty()){
            throw new EmptyTaskException("todo");
        }
        return new Todo(description);
    }

    public Deadline parseDeadline(String command) throws InvalidTask, EmptyTaskException {
        String content = command.substring(9);
        if (content.isEmpty()){
            throw new EmptyTaskException("deadline");
        }
        int byIndex = content.indexOf("/by");
        if (byIndex == -1){
            throw new InvalidTask("deadline");
        } else {
            String description = content.substring(0, byIndex - 1);
            String by = content.substring(byIndex + 4);
            DateTimeProcessor processor = new DateTimeProcessor(by);
            String time = processor.getFullDateTime();
            if (time.equals("Invalid format for date and time.")){
                return null;
            } else {
                return new Deadline(description, time);
            }
        }
    }

    public Event parseEvent(String command) throws EmptyTaskException, InvalidTask {
        String content = command.substring(6);
        if (content.isEmpty()){
            throw new EmptyTaskException("event");
        }
        int atIndex = content.indexOf("/at");
        if (atIndex == -1){
            throw new InvalidTask("event");
        } else {
            String description = content.substring(0, atIndex - 1);
            String at = content.substring(atIndex + 4);
            DateTimeProcessor processor = new DateTimeProcessor(at);
            String time = processor.getFullDateTime();
            if (time.equals("Invalid format for date and time.")) {
                return null;
            } else {
                return new Event(description, time);
            }
        }
    }
}
