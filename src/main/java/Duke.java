import java.nio.file.Path;
import java.time.format.DateTimeParseException;

import duke.*;

import duke.exceptions.DukeException;
import duke.exceptions.ParseException;
import duke.tasks.Task;
import javafx.scene.image.Image;

// potential exceptions to catch:
// 1) deleting a non-existent task
// 2) marking a non-existent task as done
// 3) marking an already done task done again

public class Duke {
    private Storage storage;
    private TaskList tasks;

    private Path filePath = Storage.getSaveFilePath();
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/Eloj.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/jeffBezox.png"));

    /**
     * Creates a Duke object
     */
    public Duke() throws DukeException {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

//    /**
//     * Runs the Tasker program which allows user to interact with
//     * to organize the user's Tasks.
//     */
//    public void run() {
//        try {
//            ui.greetUser();
//            ui.indicateReady();
//            sc = new Scanner(System.in);
//            String input = sc.nextLine();
//            String[] command = input.split(" ");
//            while (!input.equals("bye")) {
//                parser.receive(command);
//                input = sc.nextLine();
//                command = input.split(" ");
//            }
//            ArrayList<Task> arrToSave = parser.consolidate();
//            storage.saveData(arrToSave);
//            ui.sayGoodbye();
//        } catch (DukeException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            sc.close();
//        }
//    }
    /**
     * Get responses from Tasker depending on the user's input
     *
     * @param input user input
     * @return Tasker's response
     */
    public String getResponse(String input) {
        try {
            int indexOfWhitespaceAfterCommand = input.indexOf(' ');
            input.substring(indexOfWhitespaceAfterCommand);
        } catch (StringIndexOutOfBoundsException e) {
            try {
                Parser.parseCommand(input);
            } catch (IllegalArgumentException exception) {
                return GuiUi.displayGenericError();
            }
            Parser.Command command = Parser.parseCommand(input);
            switch (command) {
            case LIST:
                return GuiUi.displayList(this.tasks);
            case BYE:
                return GuiUi.displayExitMsg();
            default:
                return GuiUi.displayGenericError();
            }
        }
        try {
            int indexOfSpaceAfterCommand = input.indexOf(' ');
            //get the command word
            String commandWord = input.substring(0, indexOfSpaceAfterCommand);
            //get the rest of the input
            String inputAfterCommand = input.substring(indexOfSpaceAfterCommand + 1);
            Parser.Command command = Parser.parseCommand(commandWord);
            switch (command) {
            case DONE:
                int indexToMark = Integer.parseInt(inputAfterCommand);
                try {
                    String taskMarkedDone = GuiUi.displayMarkDoneSuccess(tasks.mark(indexToMark));
                    storage.saveData(tasks.getTasks());
                    return taskMarkedDone;
                } catch (DukeException e) {
                    return GuiUi.displayFailToSaveError();
                } catch (IndexOutOfBoundsException e) {
                    return GuiUi.displayIndexOutOfBoundsError(tasks);
                }
            case DELETE:
                int indexToDelete = Integer.parseInt(inputAfterCommand);
                try {
                    Task taskDeleted = tasks.delete(indexToDelete);
                    storage.saveData(tasks.getTasks());
                    return GuiUi.displayDeleteSuccess(tasks, taskDeleted);
                } catch (DukeException e) {
                    return GuiUi.displayFailToSaveError();
                } catch (IndexOutOfBoundsException e) {
                    return GuiUi.displayIndexOutOfBoundsError(tasks);
                }
            case FIND:
                TaskList matchingTasks = tasks.find(inputAfterCommand);
                return GuiUi.showMatchingResult(matchingTasks);
            default:
                try {
                    Task taskToBeAdded = Parser.parseDescription(command, inputAfterCommand);
                    tasks.add(taskToBeAdded);
                    storage.saveData(tasks.getTasks());
                    return GuiUi.displayAddSuccess(tasks, taskToBeAdded);
                } catch (DukeException e) {
                    return GuiUi.displayFailToSaveError();
                } catch (ParseException e) {
                    return GuiUi.insertMsgIntoChatBox(e.getErrMsg());
                } catch (DateTimeParseException e) {
                    return GuiUi.displayParseError();
                }
            }
        } catch (IllegalArgumentException e) {
            return GuiUi.displayGenericError();
        }
    }
}


