package duke.datahandler;

import java.util.ArrayList;
import java.util.Arrays;

import duke.enums.TaskType;

public class ParseTaskHandler {
    private String description;
    private String date;

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

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
