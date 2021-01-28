package chat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

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
        String formatStr = "Please input with this format:\n" +
                "event [name] /at [end date/time]-[start date/time]\n" +
                "* start and end date/time should be written as dd/MM/yyyy HH:mm\n" +
                "* i.e. 2019/03/19 22:00-2019/03/19 23:00\n" +
                "* i.e. 2019/03/19 22:00 - 2019/03/19 23:00";
        
        if (!str.startsWith("event")) {
            //i.e. event(followed by one or more empty spaces)
            //i.e. list
            throw new ChatException("wrong instruction for event\n" + formatStr);
        } else if (str.strip().equals("event")) {
            //i.e. event
            //i.e. event(followed by one or more empty spaces)
            throw new ChatException("event name, start and end date/time missing\n" + formatStr);
        } else if (!str.startsWith("event ")) {
            //i.e. eventfinal exam
            throw new ChatException("no spacing after event\n" + formatStr);
        }

        String tempStr = str.replace("event ", "").stripLeading();
        if (tempStr.startsWith("/at")) {
            //i.e. event /at
            //i.e. event /at tmr 5-6pm
            throw new ChatException("event name missing\n" + formatStr);
        } else if (!tempStr.contains("/at")) {
            //i.e. event final exam
            //i.e. event final exam 5-6pm
            throw new ChatException("/at missing\n" + formatStr);
        } else if (!tempStr.contains(" /at ")) {
            //i.e. event final exam/at
            //i.e. event final exam/at 5-6pm
            //i.e. event final exam /at5-6pm
            //i.e. event final exam/at5-6pm
            throw new ChatException("missing spaces before or after /at\n" + formatStr);
        }

        String[] tempArr = tempStr.split(" /at ");
        if (tempArr.length < 2) {
            //i.e. event final exam /at
            throw new ChatException("missing start and end date/time\n" + formatStr);
        } else if (tempArr.length > 2 || tempArr[1].contains("/at")) {
            //i.e. event final exam /at /at 5-6pm
            throw new ChatException("not allowed to have more than 1 ' /at '\n" + formatStr);
        } else if (!tempArr[1].contains("-")) {
            //i.e. event final exam /at 5pm
            throw new ChatException("missing '-'\n" + formatStr);
        }

        String[] timeArr = tempArr[1].strip().split("-");
        if (timeArr.length < 2) {
            //i.e. event final exam /at 5-
            throw new ChatException("missing start or end date/time\n" + formatStr);
        } else {
            try {
                String startStr = timeArr[0].strip();
                String endStr = timeArr[1].strip();
                LocalDateTime startDateTime = LocalDateTime.parse(startStr, inputFormatter);
                LocalDateTime endDateTime = LocalDateTime.parse(endStr, inputFormatter);
                if (startDateTime.isAfter(endDateTime)) {
                    throw new ChatException("Start date/time is after end date/time\n" + formatStr);
                }
                return new Event(tempArr[0].strip(), startDateTime, endDateTime);
            } catch (DateTimeParseException e) {
                throw new ChatException("Start or end date/time is of wrong format\n" + formatStr);
            }
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
