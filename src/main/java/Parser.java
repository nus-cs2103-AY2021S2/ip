import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {


    /**
     * Parse a string to Todo.
     * @param string the string.
     * @return the todo needed.
     *
     */
    public static final String REGEX_SPACE = "\\s+";
    public static final String REGEX_VERTICLAL_LINE = "[|]";
    public static final String REGEX_TILDE = "[~]";
    public static final String MM_DD_YYYY = "MM/dd/yyyy";
    public static final String HH_MM = "HH:mm";

    public static Todo stringToTodo(String string) {
        String desc = "";
        String[] texts = string.split(REGEX_SPACE);
        boolean status = (texts[0].charAt(4) == 'X');
        for (int j = 1; j < texts.length ; j++) {
            desc = desc + texts[j] + " ";
        }
        return new Todo(desc,status);
    }


    /**
     * Parse a string to Deadline.
     * @param string the string.
     * @return the deadline needed.
     *
     */


    public static Deadline stringToDeadline(String string){
        String missions = "";
        String[] time = string.split(REGEX_VERTICLAL_LINE);
        String[] texts = string.split(REGEX_SPACE);
        String content = time[0];
        String[] text = content.split(REGEX_SPACE);
        boolean status = (texts[0].charAt(4) == 'X');
        for (int j = 1; j < text.length ; j++) {
            missions = missions + texts[j] + " ";
        }
        String[] times = time[1].split(REGEX_SPACE);
        return new Deadline(missions,status
                , LocalDate.parse(times[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                , LocalTime.parse(times[2],DateTimeFormatter.ofPattern(HH_MM)));
    }

    /**
     * Parse a string to Event.
     * @param string the string.
     * @return the event needed.
     *
     */

    public static Event stringToEvent(String string){
        String missions = "";

        String[] time = string.split(REGEX_VERTICLAL_LINE);
        String[] texts = string.split(REGEX_SPACE);
        String content = time[0];
        String[] text = content.split(REGEX_SPACE);
        for (int j = 1; j < text.length ; j++) {
            missions = missions + texts[j] + " ";
        }
        String[] startEnd = time[1].split(REGEX_TILDE);
        String[] start = startEnd[0].split(REGEX_SPACE);
        String[] end = startEnd[1].split(REGEX_SPACE);
        boolean status = (texts[0].charAt(4) == 'X');
        return new Event(missions,status,
                LocalDate.parse(start[1],DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(start[2],DateTimeFormatter.ofPattern(HH_MM)),
                LocalDate.parse(end[1],DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(end[2],DateTimeFormatter.ofPattern(HH_MM)));
    }

    /**
     * Parse input to Todo.
     * @param todo array of strings of input.
     * @return the todo needed.
     *
     */

    public static Todo parseTodo(String[] todo) {
        String s = "";
        for (int i = 1; i < todo.length; i++) {
            s = s + todo[i] + " ";
        }
        return (new Todo(s,false));
    }

    /**
     * Parse input to Event.
     * @param event array of strings of input.
     * @return the event needed.
     *
     */

    public static Event parseEvent(String[] event) {
        String s = event[0];
        String[] dates = event[1].split(REGEX_SPACE);
        LocalDate startDate = LocalDate.parse(dates[1],DateTimeFormatter.ofPattern(MM_DD_YYYY));
        LocalTime startTime = LocalTime.parse(dates[2],DateTimeFormatter.ofPattern(HH_MM));
        LocalDate endDate = LocalDate.parse(dates[3],DateTimeFormatter.ofPattern(MM_DD_YYYY));
        LocalTime endTime = LocalTime.parse(dates[4],DateTimeFormatter.ofPattern(HH_MM));
        return new Event(s.substring(6), false, startDate,startTime,endDate,endTime);
    }


    /**
     * Parse input to Deadline.
     * @param deadline array of strings of input.
     * @return the deadline needed.
     *
     */

    public static Deadline parseDeadlinne(String[] deadline) {
        String s = deadline[0];
        String[] dates = deadline[1].split(REGEX_SPACE);
        LocalDate deadlineDate = LocalDate.parse(dates[1], DateTimeFormatter.ofPattern(MM_DD_YYYY));
        LocalTime deadlineTime = LocalTime.parse(dates[2], DateTimeFormatter.ofPattern(HH_MM));
        return new Deadline(s.substring(9), false, deadlineDate,deadlineTime);
    }
}
