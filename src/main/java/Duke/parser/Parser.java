package duke.parser;

import java.time.LocalDateTime;

import duke.common.Command;
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
    public Parser() {
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
        String[] inputSplit = input.split("/");
        typeOfTask = inputSplit[0].replaceAll("\\s+", "");

        if (inputSplit.length == 2) {
            description = inputSplit[1];
        } else if (inputSplit.length == 3) {
            description = inputSplit[1];
            TimeParser timeParser = new TimeParser();
            time = timeParser.parse(inputSplit[2]);
        } else {
        }
        return new Parser(typeOfTask, description, time);
    }
    /**
     * Convert typeOfTask string into command.
     *
     * @return Command
     */
    public Command getCommand() {
        return Command.valueOf(typeOfTask.toUpperCase());
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
