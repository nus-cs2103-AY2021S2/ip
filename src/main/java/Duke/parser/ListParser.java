package duke.parser;

import java.time.LocalDateTime;

/**
 * Parses string input saved in hard disk.
 */
public class ListParser {
    private String typeOfTask;
    private Boolean isDone;
    private String description;
    private LocalDateTime time;

    /**
     * ListParser constructor.
     */
    public ListParser() {
        this.typeOfTask = "";
        this.isDone = null;
        this.description = "";
        this.time = null;
    }

    /**
     * Return ListParser.
     *
     * @param typeOfTask typeOfTask todo, event, deadline
     * @param isDone Boolean of task completion status
     * @param description
     * @param time
     */
    public ListParser(String typeOfTask, Boolean isDone, String description, LocalDateTime time) {
        this.typeOfTask = typeOfTask;
        this.isDone = isDone;
        this.description = description;
        this.time = time;
    }

    /**
     * Parses the String in Hard Disk line by line.
     *
     * @param input
     * @return ListParser
     */
    public ListParser parse(String input) {
        String[] inputSplit = input.split("\\|");
        typeOfTask = inputSplit[0];
        String status = inputSplit[1];
        Boolean isDone;
        description = inputSplit[2];

        if (status.equals("DONE")) {
            isDone = true;
        } else { //status "PENDING"
            isDone = false;
        }
        if (inputSplit.length == 4) {
            TimeParser timeParser = new TimeParser();
            time = timeParser.parse(inputSplit[3]);
        } else {
            time = null;
        }
        return new ListParser(typeOfTask, isDone, description, time);
    }

    /**
     * Returns typeOfTask attribute.
     *
     * @return typeOfTask
     */
    public String getTypeOfTask() {
        return typeOfTask;
    }

    /**
     * Returns isDone attribute.
     * @return isDone
     */
    public Boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns description attribute.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns time attribute.
     *
     * @return time
     */
    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "|" + typeOfTask + "|" + isDone + "|" + description + "|" + time + "|";
    }
}
