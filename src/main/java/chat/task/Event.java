package chat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import chat.Chat;
import chat.ChatException;

/**
 * Event is a type of <b>task</b> with a <b>start</b> and <b>end</b> date and time.
 */
public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mma");

    /**
     * Initialises Event object.
     * <p>Boolean isDone has been set to false by default.</p>
     * 
     * @param name Name or description of the task.
     * @param start Start date and time of the task.
     * @param end End date and time of the task.
     */
    private Event (String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        
        assert start != null; 
        assert end != null;
        this.start = start;
        this.end = end;
    }

    /**
     * Initialises Event object.
     * 
     * @param isDone Boolean that tells if task is completed.
     * @param name Name or description of the task.
     * @param start Start date and time of the task.
     * @param end End date and time of the task.
     */
    public Event (boolean isDone, String name, String start, String end) { 
        super(isDone, name);

        assert start != null;
        assert end != null;
        this.start = LocalDateTime.parse(start, inputFormatter);
        this.end = LocalDateTime.parse(end, inputFormatter);
    }

    /**
     * Static method that creates an Event object.
     * 
     * @param str Inputted command string from user to Chat the Cat.
     * @return Event object.
     * @throws ChatException If format of command is wrong.
     */
    public static Event createEvent (String str) throws ChatException {
        assert str != "";
        
        String formatStr = "Please input with this format:\n" +
                "event [name] /at [end date/time]-[start date/time]\n" +
                "* start and end date/time should be written as dd/MM/yyyy HH:mm\n" +
                "* i.e. 2019/03/19 22:00-2019/03/19 23:00\n" +
                "* i.e. 2019/03/19 22:00 - 2019/03/19 23:00";
        
        try {
            checkWrongCommand(str);
            checkAllParametersIsMissing(str);
            checkNoSpaceBetweenEventAndName(str);
            
            String parameterStr = str.replace("event ", "").stripLeading();
            checkNoName(parameterStr);
            checkNoAt(parameterStr);
            checkNoSpaceAroundAt(parameterStr);
            
            String[] parameters = parameterStr.split(" /at ");
            checkNoStartAndEndDateTime(parameters);
            checkDuplicateAt(parameters);
            checkForMinusSign(parameters);
            
            String[] dateTimeStrings = parameters[1].strip().split("-");
            checkForMissingStartOrEndDateTime(dateTimeStrings);
            
            LocalDateTime[] dateTimes = parseStartAndEndDateTime(dateTimeStrings);
            LocalDateTime start = dateTimes[0];
            LocalDateTime end = dateTimes[1];
            checkEndBeforeStart(start, end);
            
            return new Event(parameters[0].strip(), start, end);
            
        } catch (ChatException e) {
            throw new ChatException(e.getMessage() + formatStr);
        }
    }
    
    private static void checkWrongCommand(String str) throws ChatException {
        if (!str.startsWith("event")) {
            //i.e. list
            throw new ChatException("wrong instruction for event\n");
        }
    }
    
    private static void checkAllParametersIsMissing(String str) throws ChatException {
        if (str.strip().equals("event")) {
            //i.e. event
            //i.e. event(followed by one or more empty spaces)
            throw new ChatException("event name, start and end date/time missing\n");
        }
    }
    
    private static void checkNoSpaceBetweenEventAndName(String str) throws ChatException {
        if (!str.startsWith("event ")) {
            //i.e. eventfinal exam
            throw new ChatException("no spacing after event\n");
        }
    }

    private static void checkNoName(String parameterStr) throws ChatException {
        if (parameterStr.startsWith("/at")) {
            //i.e. event /at
            //i.e. event /at tmr 5-6pm
            throw new ChatException("event name missing\n");
        }
    }
    
    private static void checkNoAt(String parameterStr) throws ChatException {
        if (!parameterStr.contains("/at")) {
            //i.e. event final exam
            //i.e. event final exam 5-6pm
            throw new ChatException("/at missing\n");
        }
    }
    
    private static void checkNoSpaceAroundAt(String parameterStr) throws ChatException { 
        if (!parameterStr.contains(" /at ")) {
            //i.e. event final exam/at
            //i.e. event final exam/at 5-6pm
            //i.e. event final exam /at5-6pm
            //i.e. event final exam/at5-6pm
            throw new ChatException("missing spaces before or after /at\n");
        }
    }
    
    private static void checkNoStartAndEndDateTime(String[] parameters) throws ChatException {
        if (parameters.length < 2) {
            //i.e. event final exam /at
            throw new ChatException("missing start and end date/time\n");
        }
    }
    
    private static void checkDuplicateAt(String[] parameters) throws ChatException {
        if (parameters.length > 2 || parameters[1].contains("/at")) {
            //i.e. event final exam /at /at 5-6pm
            throw new ChatException("not allowed to have more than 1 ' /at '\n");
        }
    }
    
    private static void checkForMinusSign(String[] parameters) throws ChatException {
        if (!parameters[1].contains("-")) {
            //i.e. event final exam /at 5pm
            throw new ChatException("missing '-'\n");
        }
    }
    
    private static void checkForMissingStartOrEndDateTime(String[] parameters) throws ChatException {
        if (parameters.length < 2) {
            //i.e. event final exam /at 5-
            throw new ChatException("missing start or end date/time\n");
        }
    }
    
    private static LocalDateTime[] parseStartAndEndDateTime(String[] parameters) throws ChatException {
        try {
            String startStr = parameters[0].strip();
            String endStr = parameters[1].strip();
            LocalDateTime startDateTime = LocalDateTime.parse(startStr, inputFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(endStr, inputFormatter);
            LocalDateTime[] dateTimes = {startDateTime, endDateTime};
            return dateTimes;
        } catch (DateTimeParseException e) {
            throw new ChatException("Start or end date/time is of wrong format\n");
        }
    }
    
    private static void checkEndBeforeStart(LocalDateTime start, LocalDateTime end) throws ChatException {
        if (start.isAfter(end)) {
            throw new ChatException("Start date/time is after end date/time\n");
        }
    }
    
    /**
     * Returns the start date and time of the task.
     * 
     * @return Start date and time of the task.
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Returns the end date and time of the task.
     *
     * @return End date and time of the task.
     */
    public LocalDateTime getEnd() {
        return this.end;
    }

    /**
     * Returns a comma separated string of all parameters.
     *
     * @return Comma separated string with all parameters listed out.
     */
    public String allParameterStr() {
        return String.format("E,%s,%s,%s,%s", this.getIsDone(), this.getName(), this.getStart().format(inputFormatter),
                this.getEnd().format(inputFormatter));
    }

    /**
     * Returns a string that shows the details of the task.
     * <p>[ ] will be displayed if isDone = false.</p>
     * <p>[X] will be displayed if isDone = true.</p>
     *
     * @return String showing details of task, i.e. [E][ ] name (at: start - end).
     */
    @Override
    public String toString() {
        String startStr = this.getStart().format(displayFormatter);
        String endStr = this.getEnd().format(displayFormatter);
        if (this.getStart().toString().split("T")[0].equals(this.getEnd().toString().split("T")[0])) {
            //start and end are of the same day
            DateTimeFormatter tempFormatter = DateTimeFormatter.ofPattern("h:mma");
            endStr = this.getEnd().format(tempFormatter);
        }
        return String.format("[E]%s (at: %s - %s)", super.toString(), startStr, endStr);
    }

}
