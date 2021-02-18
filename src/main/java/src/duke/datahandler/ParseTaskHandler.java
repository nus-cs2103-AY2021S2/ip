package duke.datahandler;

import java.util.ArrayList;
import java.util.Arrays;

import duke.enums.TaskType;

/**
 * The ParseTaskHandler object handles all task logic for the parser
 */
public class ParseTaskHandler {
    private String description;
    private String date;

    /**
     * Gets the relevant information from the parser and breaks down the relevant
     * informatino needed to create a new task
     * @param information informatino needed to create a new task
     */
    ParseTaskHandler(String[] information) {
        ArrayList<String> timeAndDescription;

        if (information.length < 2) {
            timeAndDescription = new ArrayList<String>();
        } else {
            timeAndDescription = new ArrayList<String>(Arrays.asList(information[1].split("/", 2)));
        }

        TaskType typeOfTask = TaskType.valueOf(information[0].toUpperCase());
        timeAndDescription.add("DESCRIPTION.ERROR DATE.ERROR");
        timeAndDescription.add("DATE.ERROR ");
        assert timeAndDescription.size() >= 2;

        switch (typeOfTask) {
        case TODO:
            this.description = timeAndDescription.get(0);
            break;
        case DEADLINE:
        case EVENT:
            this.description = timeAndDescription.get(0);
            this.date = timeAndDescription.get(1).split(" ", 2)[1];
            break;

        default:
            break;
        }

    }

    /**
     * gets the date of the new task
     * @return inputted new task date
     */
    public String getDate() {
        return date;
    }

    /**
     * gets the description of the new task
     * @return inputted new task description
     */
    public String getDescription() {
        return description;
    }
}
