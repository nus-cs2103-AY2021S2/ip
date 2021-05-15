package chat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chat.ChatException;

/**
 * Deadline is a type of <b>task</b> with an <b>end</b> date and time.
 */
public class Deadline extends Task {

    private LocalDateTime end;
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mma");

    /**
     * Initialises Deadline object.
     * <p>Boolean isDone has been set to false by default.</p>
     * 
     * @param name Name or description of the task.
     * @param end End date and time of the task.
     */
    private Deadline (String name, LocalDateTime end) {
        super(name);
        
        assert end != null;
        this.end = end;
    }

    /**
     * Initialises Deadline object.
     * 
     * @param isDone Boolean that tells if task is completed.
     * @param name Name or description of the task.
     * @param end End date and time of the task.
     */
    public Deadline (boolean isDone, String name, String end) { 
        super(isDone, name);
        
        assert end != null;
        this.end = LocalDateTime.parse(end, inputFormatter);
    }

    /**
     * Static method that creates a Deadline object.
     * 
     * @param str Inputted command string from user to Chat the Cat.
     * @return Deadline object. 
     * @throws ChatException If format of command is wrong.
     */
    public static Deadline createDeadline (String str) throws ChatException {
        assert str != ""; 

        String formatStr = "Please input with this format:\n" +
                "deadline [name] /by [end date/time]\n" +
                "* end date/time should be written as dd/MM/yyyy HH:mm\n" +
                "* i.e. 19/03/2019 23:55";
        
        try {
            checkWrongCommand(str);
            checkAllParametersIsMissing(str);
            checkNoSpaceBetweenDeadlineAndName(str);
            
            String parameterStr = str.replace("deadline ", "").stripLeading();
            checkNoName(parameterStr);
            checkNoBy(parameterStr);
            checkNoSpaceAroundBy(parameterStr);
            
            String[] parameters = parameterStr.split(" /by ");
            checkNoEnd(parameters);
            checkDuplicateBy(parameters);
            
            LocalDateTime end = parseEndDateTime(parameters);
            return new Deadline(parameters[0].strip(), end);
        } catch (ChatException e) {
            throw new ChatException(e.getMessage() + formatStr);
        }
    }
    
    private static void checkWrongCommand(String str) throws ChatException {
        if (!str.startsWith("deadline")) {
            //i.e. event
            throw new ChatException("wrong instruction for deadline\n");
        }
    }

    private static void checkAllParametersIsMissing(String str) throws ChatException {
        if (str.strip().equals("deadline")) {
            //i.e. deadline
            //i.e. deadline(followed by one or more empty spaces)
            throw new ChatException("deadline name and end date/time missing\n");
        }
    }

    private static void checkNoSpaceBetweenDeadlineAndName(String str) throws ChatException {
        if (!str.startsWith("deadline ")) {
            //i.e. deadlinereturn book
            throw new ChatException("no spacing after deadline\n");
        }
    }
    
    private static void checkNoName(String parameterStr) throws ChatException {
        if (parameterStr.startsWith("/by")) {
            //i.e. deadline /by
            //i.e. deadline /by night
            throw new ChatException("deadline name missing\n");
        }
    }

    private static void checkNoBy(String parameterStr) throws ChatException {
        if (!parameterStr.contains("/by")) {
            //i.e. deadline return book
            //i.e. deadline return book tmr
            throw new ChatException("/by missing\n");
        }
    }
    
    private static void checkNoSpaceAroundBy(String parameterStr) throws ChatException {
        if (!parameterStr.contains(" /by ")) {
            //i.e. deadline return book/by
            //i.e. deadline return book/by tmr
            //i.e. deadline return book /bytmr
            //i.e. deadline return book/bytmr
            throw new ChatException("missing spaces before or after /by\n");
        }
    }
    
    private static void checkNoEnd(String[] parameters) throws ChatException {
        if (parameters.length < 2) {
            //i.e. deadline return book /by
            throw new ChatException("missing end date/time\n");
        }
    }
    
    private static void checkDuplicateBy(String[] parameters) throws ChatException{
        if (parameters.length > 2 || parameters[1].contains("/by")) {
            //i.e. deadline return book /by /by night
            throw new ChatException("not allowed to have more than 1 ' /by '\n");
        }
    }
    
    private static LocalDateTime parseEndDateTime(String[] parameters) throws ChatException {
        try {
            String endStr = parameters[1].strip();
            LocalDateTime endDateTime = LocalDateTime.parse(endStr, inputFormatter);
            return endDateTime;
        } catch (DateTimeParseException e) {
            throw new ChatException("End date/time is of wrong format\n");
        }
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
        return String.format("D,%s,%s,%s", this.getIsDone(), this.getName(), this.getEnd().format(inputFormatter));
    }

    /**
     * Returns a string that shows the details of the task.
     * <p>[ ] will be displayed if isDone = false.</p>
     * <p>[X] will be displayed if isDone = true.</p>
     *
     * @return String showing details of task, i.e. [D][ ] name (by: end).
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getEnd().format(displayFormatter));
    }

}
