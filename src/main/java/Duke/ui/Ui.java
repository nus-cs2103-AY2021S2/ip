package duke.ui;

import duke.Duke;
import duke.common.Command;
import duke.exception.DuplicateTask;
import duke.exception.EmptyDescription;
import duke.exception.InvalidTaskNumber;
import duke.exception.InvalidTypeOfTask;
import duke.parser.Parser;

/**
 *
 */
public class Ui {
    private Boolean shouldExit = false;
    private Duke duke;

    /**
     * Ui constructor.
     *
     * @param duke
     */
    public Ui(Duke duke) {
        this.duke = duke;
    }

    /**
     * Returns Duke response to user input.
     *
     * @param input user command line input.
     * @return String Duke response.
     */
    public String readCommand(String input) {
        String response = "";
        try {
            assert shouldExit == true : "shouldExit boolean false";
            Parser p = new Parser();
            p = p.parse(input);
            Command command = p.getCommand();
            switch (command) {
            case BYE:
                shouldExit = true;
                response = duke.exit();
                break;
            case LIST:
                response = duke.list();
                break;
            case FIND:
                response = duke.find(p);
                break;
            case DONE:
                response = duke.markAsDone(p);
                break;
            case DELETE:
                response = duke.deleteTask(p);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                response = duke.addTask(p);
                break;
            default:
                throw new InvalidTypeOfTask();
            }
            duke.save();
        } catch (IllegalArgumentException e) {
            InvalidTypeOfTask error = new InvalidTypeOfTask();
            response = error.toString();
        } catch (InvalidTypeOfTask e) {
            response = e.toString();
        } catch (EmptyDescription e) {
            response = e.toString();
        } catch (InvalidTaskNumber e) {
            response = e.toString();
        } catch (DuplicateTask e) {
            response = e.toString();
        }
        return response;
    }
}
