package blarb;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

/**
 * {@code Processor} decides on the course of action for Blarb.
 */
public class Processor {
    /**
     * Checks if Blarb will terminate.
     *
     * @param input User input
     * @return Termination status.
     */
    static boolean leave(String input) {
        CommandLine cml = Parser.parse(input);
        return cml.command.equals(Command.BYE) && cml.isSingleCommand();
    }

    /**
     * Executes Blarb's will.
     *
     * @param input User input
     * @return Blarb's response output.
     */
    static Output execute(String input, Tasklist tasklist, Storage storage) {
        String[] outputs = new String[2];
        outputs[1] = null;
        CommandLine cml = Parser.parse(input);
        switch (cml.command) {
        case DONE:
            try {
                int index = Integer.parseInt(cml.description) - 1;
                String output = tasklist.done(index);
                outputs[0] = output;
                storage.refile(tasklist);
            } catch (IOException ex) {
                outputs[1] = "Cannot access storage.";
            } catch (ArrayIndexOutOfBoundsException ex) {
                outputs[0] = "What have you done! More specific!";
            } catch (IndexOutOfBoundsException ex) {
                outputs[0] = "There is no such task.";
            } catch (NumberFormatException ex) {
                outputs[0] = "Done what now? I don't understand";
            }
            break;
        case DELETE:
            try {
                int index = Integer.parseInt(cml.description) - 1;
                String output = tasklist.delete(index);
                outputs[0] = output;
                storage.refile(tasklist);
            } catch (IOException ex) {
                outputs[1] = "Cannot access storage.";
            } catch (ArrayIndexOutOfBoundsException ex) {
                outputs[0] = "What do you want to hide?";
            } catch (IndexOutOfBoundsException ex) {
                outputs[0] = "There is no such task.";
            } catch (NumberFormatException ex) {
                outputs[0] = "You can't delete your past.";
            }
            break;
        case TODO:
            try {
                if (cml.isSingleCommand()) {
                    throw new InputMismatchException();
                }
                Task task = new ToDo(cml.description);
                String output = tasklist.add(task);
                outputs[0] = output;
                storage.file(task);
            } catch (IOException ex) {
                outputs[1] = "Cannot access storage.";
            } catch (InputMismatchException ex) {
                outputs[0] = "Todo what?";
            }
            break;
        case DEADLINE:
            try {
                if (cml.isSingleCommand()) {
                    throw new InputMismatchException();
                }
                String[] fragments = cml.description.split(" /by ");
                Task task = new Deadline(fragments[0], fragments[1]);
                String output = tasklist.add(task);
                outputs[0] = output;
                storage.file(task);
            } catch (IOException ex) {
                outputs[1] = "Cannot access storage.";
            } catch (ArrayIndexOutOfBoundsException ex) {
                outputs[0] = "Type the deadline, then give the time using \"/by\".";
            } catch (DateTimeParseException ex) {
                outputs[0] = "blarb.Deadline time must be in the format of yyyy-mm-dd.";
            } catch (InputMismatchException ex) {
                outputs[0] = "Someone's having trouble with deadlines.";
            }
            break;
        case EVENT:
            try {
                if (cml.isSingleCommand()) {
                    throw new InputMismatchException();
                }
                String[] fragments = cml.description.split(" /at ");
                Task task = new Event(fragments[0], fragments[1]);
                String output = tasklist.add(task);
                outputs[0] = output;
                storage.file(task);
            } catch (IOException ex) {
                outputs[1] = "Cannot access storage.";

            } catch (ArrayIndexOutOfBoundsException ex) {
                outputs[0] = "Type the event, then give the time using \"/at\".";
            } catch (InputMismatchException ex) {
                outputs[0] = "Tell me the event!";
            }
            break;
        case FIND:
            try {
                if (cml.isSingleCommand()) {
                    throw new InputMismatchException();
                }
                outputs[0] = tasklist.find(cml.description);
            } catch (InputMismatchException ex) {
                outputs[0] = "I need a clue to find stuff!";
            }
            break;
        case LIST:
            outputs[0] = cml.isSingleCommand()
                    ? tasklist.list()
                    : "Listing is simple, so typing \"list\" would suffice.";
            break;
        case BYE:
            outputs[0] = cml.isSingleCommand()
                    ? "Hasta la vista, baby."
                    : "Type \"bye\" to see me go.";
            break;
        case UNKNOWN:
            outputs[0] = String.format("I have detailed files on human anatomy, but not %s.", input);
            break;
        default:
            assert false;
        }
        return new Output(outputs[0], outputs[1]);
    }
}

