import java.time.LocalDateTime;

/**
 * Parses user input into typeOfTask, description, time.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
public class Parser {
    private String typeOfTask;
    private String description;
    private LocalDateTime time;

    /**
     * Returns Parser.
     *
     * @return Parser
     */
    Parser() {
        this.typeOfTask = "";
        this.description = "";
        this.time = null;
    }

    /**
     * Returns Parser.
     *
     * @param typeOfTask Type of task : Todo, Deadline, Event.
     * @param description Task description.
     * @param time time of task.
     * @return Parser
     */
    Parser(String typeOfTask, String description, LocalDateTime time) {
        this.typeOfTask = typeOfTask;
        this.description = description;
        this.time = time;
    }

    /**
     * Returns a Parser that represent the parsed user input.
     * Parses user input into typeOfTask, description, time.
     *
     * @param input user input.
     * @return Parser
     */
    public Parser parse(String input) {
        String[] inputSplit = input.split(" ", 2);
        typeOfTask = inputSplit[0];
        if (inputSplit.length >= 2) {
            if (inputSplit[1].equals("")) {
            } else {
                TimeParser timeParser = new TimeParser();
                if (inputSplit[1].split("/by").length == 2) {
                    String[] descSplit = inputSplit[1].split("/by ");
                    description = descSplit[0];
                    time = timeParser.parse(descSplit[1]);

                } else if (inputSplit[1].split("/at").length == 2) {
                    String[] descSplit = inputSplit[1].split("/at ");
                    description = descSplit[0];
                    time = timeParser.parse(descSplit[1]);
                } else {
                    description = inputSplit[1];
                }
            }
        }
        return new Parser(typeOfTask, description, time);
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

    /**
     * Returns String of parsed user input.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "|" + typeOfTask + "|" + description + "|" + time + "|";
    }
}
