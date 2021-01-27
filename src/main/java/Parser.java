import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Todo stringToTodo(String string){
        String desc = "";
        String[] texts = string.split("\\s+");
        boolean status = (texts[0].charAt(4) == 'X');
        for (int j = 1; j < texts.length ; j++) {
            desc = desc + texts[j] + " ";
        }
        return new Todo(desc,status);
    }
    public static Deadline stringToDeadline(String string){
        String missions = "";
        String[] time = string.split("[|]");
        String[] texts = string.split("\\s+");
        String content = time[0];
        String[] text = content.split("\\s+");
        boolean status = (texts[0].charAt(4) == 'X');
        for (int j = 1; j < text.length ; j++) {
            missions = missions + texts[j] + " ";
        }
        String[] times = time[1].split("\\s+");
        return new Deadline(missions,status
                , LocalDate.parse(times[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                , LocalTime.parse(times[2],DateTimeFormatter.ofPattern("HH:mm")));
    }
    public static Event stringToEvent(String string){
        String missions = "";

        String[] time = string.split("[|]");
        String[] texts = string.split("\\s+");
        String content = time[0];
        String[] text = content.split("\\s+");
        for (int j = 1; j < text.length ; j++) {
            missions = missions + texts[j] + " ";
        }
        String[] startEnd = time[1].split("[~]");
        String[] start = startEnd[0].split("\\s+");
        String[] end = startEnd[1].split("\\s+");
        boolean status = (texts[0].charAt(4) == 'X');
        return new Event(missions,status,
                LocalDate.parse(start[1],DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(start[2],DateTimeFormatter.ofPattern("HH:mm")),
                LocalDate.parse(end[1],DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(end[2],DateTimeFormatter.ofPattern("HH:mm")));
    }

    public static Todo parseTodo(String[] todo){
        String s = "";
        for (int i = 1; i < todo.length; i++) {
            s = s + todo[i] + " ";
        }
        return (new Todo(s,false));
    }
    public static Event parseEvent(String[] event) {
        String s = event[0];
        String[] dates = event[1].split("\\s+");
        LocalDate startDate = LocalDate.parse(dates[1],DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalTime startTime = LocalTime.parse(dates[2],DateTimeFormatter.ofPattern("HH:mm"));
        LocalDate endDate = LocalDate.parse(dates[3],DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalTime endTime = LocalTime.parse(dates[4],DateTimeFormatter.ofPattern("HH:mm"));
        return new Event(s.substring(6), false, startDate,startTime,endDate,endTime);
    }

    public static Deadline parseDeadlinne(String[] deadline){
        String s = deadline[0];
        String[] dates = deadline[1].split("\\s+");
        LocalDate deadlineDate = LocalDate.parse(dates[1], DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalTime deadlineTime = LocalTime.parse(dates[2], DateTimeFormatter.ofPattern("HH:mm"));
        return new Deadline(s.substring(9), false, deadlineDate,deadlineTime);
    }
}
