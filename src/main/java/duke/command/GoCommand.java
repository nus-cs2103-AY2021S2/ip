package duke.command;

import duke.DukeException;
import duke.PlaceList;
import duke.StringParser;
import duke.TaskList;
import duke.place.Place;

public class GoCommand extends Command {

    private static final int LENGTH_GO = "go ".length();
    private static final int LENGTH_SEPARATOR = 5;

    private static final String ERROR_EMPTY_ARGUMENT = "Invalid argument: Argument field cannot be empty.";
    private static final String ERROR_EMPTY_CONTENT = "Invalid argument: Content field is blank.";
    private static final String ERROR_EMPTY_TIME = "Void argument: Time field is blank.";
    private static final String ERROR_UNDETECTED_AT = "Invalid argument: /at not detected or no argument before /at.";

    private static final String MARK_DO = " /do ";

    private final String command;

    /**
     * Constructs a go command.
     *
     * @param command Input string.
     */
    public GoCommand(String command) {
        this.command = command;
    }

    private Place convertToPlace() throws DukeException {
        if (command.length() <= LENGTH_GO) {
            throw new DukeException(ERROR_EMPTY_ARGUMENT);
        }

        int indexOfAt = command.toLowerCase().indexOf(MARK_DO);
        if (indexOfAt < LENGTH_GO) {
            throw new DukeException(ERROR_UNDETECTED_AT);
        }

        String subStrContent = command.substring(LENGTH_GO, indexOfAt);
        String subStrPlace = command.substring(indexOfAt + LENGTH_SEPARATOR);
        if (StringParser.isBlank(subStrContent)) {
            throw new DukeException(ERROR_EMPTY_CONTENT);
        } else if (StringParser.isBlank(subStrPlace)) {
            throw new DukeException(ERROR_EMPTY_TIME);
        }

        return new Place(subStrContent, subStrPlace);
    }

    /**
     * Execute and print a go command.
     *
     * @param listT Passes TaskList in case of reading and writing to the list.
     * @param listP Passes PlaceList in case of reading and writing to the list.
     * @throws DukeException When encounter an error in command argument.
     */
    @Override
    public String executeAndPrint(TaskList listT, PlaceList listP) throws DukeException {
        Place place = convertToPlace();
        listP.addPlace(place);
        return "Place added:\n" + place.toString()
                + "Now you have " + listP.getSize()
                + (listT.getSize() == 1 ? " place in the list.\n" : " places in the list.\n");
    }
}
