import java.time.Month;

public class Parser {

    public static String parseDate(String unformattedDate){
        String[] dateArr = unformattedDate.split(" ");
        String monthString = String.format("%02d", Month.valueOf(dateArr[0].toUpperCase()).getValue());
        String day = String.format("%02d", Integer.parseInt(dateArr[1]));
        String year = dateArr[2];
        String formattedDate = year + "-" + monthString + "-" + day;
        return formattedDate;
    }


    public static ToDo parseAddTodo(String command) {
        ToDo newTodo = new ToDo(command.split("todo")[1]);
        return newTodo;
    }

    public static Deadline parseAddDeadline(String command){
        String[] deadlineAndTask = command.split("/by ");
        return new Deadline(deadlineAndTask[1], deadlineAndTask[0].substring(9));
    }

    public static Event parseAddEvent(String command){
        String[] eventTimeAndTask = command.split("/at ");
        //offset of 6 to remove "event " frm statement
        return new Event(eventTimeAndTask[1], eventTimeAndTask[0].substring(6));
    }

    public static int parseDeleteCommand(String command){
        return Integer.parseInt(command.split("")[1]);
    }


}

