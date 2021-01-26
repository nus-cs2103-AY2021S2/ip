import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime end;
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mma");
    
    private Deadline (String name, LocalDateTime end) {
        super(name);
        this.end = end;
    }
    
    public Deadline (boolean done, String name, String end) { 
        super(done, name);
        this.end = LocalDateTime.parse(end, inputFormatter);
    }

    public static Deadline createDeadline (String str) throws ChatException {
        String formatStr = "Please input with this format:\n" + 
                "deadline [name] /by [end date/time]\n" +
                "* end date/time should be written as dd/MM/yyyy HH:mm\n" +
                "* i.e. 19/03/2019 23:55";
        
        if (!str.startsWith("deadline")) {
            //i.e. event
            throw new ChatException("wrong instruction for deadline\n" + formatStr);
        } else if (str.strip().equals("deadline")) {
            //i.e. deadline
            //i.e. deadline(followed by one or more empty spaces)
            throw new ChatException("deadline name and end date/time missing\n" + formatStr);
        } else if (!str.startsWith("deadline ")) {
            //i.e. deadlinereturn book
            throw new ChatException("no spacing after deadline\n" + formatStr);
        }

        String tempStr = str.replace("deadline ", "").stripLeading();
        if (tempStr.startsWith("/by")) {
            //i.e. deadline /by
            //i.e. deadline /by night
            throw new ChatException("deadline name missing\n" + formatStr);
        } else if (!tempStr.contains("/by")) {
            //i.e. deadline return book
            //i.e. deadline return book tmr
            throw new ChatException("/by missing\n" + formatStr);
        } else if (!tempStr.contains(" /by ")) {
            //i.e. deadline return book/by
            //i.e. deadline return book/by tmr
            //i.e. deadline return book /bytmr
            //i.e. deadline return book/bytmr
            throw new ChatException("missing spaces before or after /by\n" + formatStr);
        }

        String[] tempArr = tempStr.split(" /by ");
        if (tempArr.length < 2) {
            //i.e. deadline return book /by
            throw new ChatException("missing end date/time\n" + formatStr);
        } else if (tempArr.length > 2 || tempArr[1].contains("/by")) {
            //i.e. deadline return book /by /by night
            throw new ChatException("not allowed to have more than 1 ' /by '\n" + formatStr);
        } 
        
        try {
            String endStr = tempArr[1].strip();
            LocalDateTime endDateTime = LocalDateTime.parse(endStr, inputFormatter);
            return new Deadline(tempArr[0].strip(), endDateTime);
        } catch (DateTimeParseException e) {
            throw new ChatException("End date/time is of wrong format\n" + formatStr);
        }
        
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    public String allParameterStr() {
        return String.format("D,%s,%s,%s", this.getDone(), this.getName(), this.getEnd().format(inputFormatter));
    }
    
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getEnd().format(displayFormatter));
    }

}
