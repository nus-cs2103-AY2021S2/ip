import java.time.LocalDateTime;

public class ListParser {
    private String typeOfTask;
    private Boolean isDone;
    private String description;
    private LocalDateTime time;

    ListParser() {
        this.typeOfTask = "";
        this.isDone = null;
        this.description = "";
        this.time = null;
    }

    ListParser(String typeOfTask, Boolean isDone, String description, LocalDateTime time) {
        this.typeOfTask = typeOfTask;
        this.isDone = isDone;
        this.description = description;
        this.time = time;
    }

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
        // T|DONE|MSG|TIME
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
