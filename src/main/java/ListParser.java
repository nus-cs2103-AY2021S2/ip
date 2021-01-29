public class ListParser {
    private String typeOfTask;
    private Boolean isDone;
    private String description;
    private String time;

    ListParser() {
        this.typeOfTask = "";
        this.isDone = null;
        this.description = "";
        this.time = "";
    }

    ListParser(String typeOfTask, Boolean isDone, String description, String time) {
        this.typeOfTask = typeOfTask;
        this.isDone = isDone;
        this.description = description;
        this.time = time;
    }

    public ListParser parse(String input) {
        String[] inputSplit = input.split("|", 4);
        typeOfTask = inputSplit[0];
        String status = inputSplit[1];
        Boolean isDone;
        description = inputSplit[2];

        if (status.equals("DONE")) {
            isDone = true;
        } else {
            isDone = false;
        }

        if (inputSplit.length == 4) {
            time = inputSplit[3];
        } else {
            time = "";
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
    public String getTime() {
        return time;
    }
}
