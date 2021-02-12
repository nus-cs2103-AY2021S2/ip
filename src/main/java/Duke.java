import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class Duke {

    private Storage storage;

    private TaskList tasks;

    private Ui ui;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));


    private String name;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());
        name = "Link";
    }

    /**
     * Gets the appropriate response based on the user input.
     *
     * @param input The command selected by the user.
     * @param parts The array of strings in the user input, including the arguments for the command selected.
     * @return A string that is determined by the user input command.
     * @throws InsufficientArgumentsException
     * @throws FileNotFoundException
     */
    String getResponse(String input, String[] parts) throws InsufficientArgumentsException,
            FileNotFoundException, IllegalKeywordException {
        int listCounter = 1;
        switch (input) {
        case "done":
            String doneString = new DoneCommand(input, parts, tasks).execute();
            storage.save();
            return doneString;
        case "todo":
        case "deadline":
        case "event":
            String addString = new AddCommand(input, parts, tasks).execute();
            storage.save();
            return addString;
        case "delete":
            String deleteString = new DeleteCommand(input, parts, tasks).execute();
            storage.save();
            return deleteString;
        case "list":
            String listString = new ListCommand(input, parts, tasks, listCounter).execute();
            storage.save();
            return listString;
        case "find":
            String findString = new FindCommand(input, parts, tasks).execute();
            storage.save();
            return findString;
        default:
            throw new IllegalKeywordException("Unable to match any command");
        }
    }
}




