package blarb;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

/**
 * {@code Processor} decides on the course of action for Blarb.
 */
class Processor {
    /**
     * Checks if Blarb will terminate.
     *
     * @param input User input
     * @return Termination status.
     */
     public boolean willTerminate(String input) {
        CommandLine cml = Parser.parse(input);
        return cml.command.equals(Command.BYE) && cml.isSingleCommand();
    }

    /**
     * Executes Blarb's will.
     *
     * @param input User input
     * @return Blarb's response output.
     */
     public Output execute(String input, Tasklist tasklist, Storage storage) {
        String[] outputs = new String[2];
        CommandLine cml = Parser.parse(input);
        switch (cml.command) {
        case DONE:
            outputs = executeDone(cml, tasklist, storage);
            break;
        case DELETE:
            outputs = executeDelete(cml, tasklist, storage);
            break;
        case TODO:
            outputs = executeToDo(cml, tasklist, storage);
            break;
        case DEADLINE:
            outputs = executeDeadline(cml, tasklist, storage);
            break;
        case EVENT:
            outputs = executeEvent(cml, tasklist, storage);
            break;
        case FIND:
            outputs = executeFind(cml, tasklist);
            break;
        case LIST:
            outputs = executeList(cml, tasklist);
            break;
        case BYE:
            outputs = executeBye(cml);
            break;
        default:
            outputs[0] = String.format("I have detailed files on human anatomy, but not %s.", input);
            outputs[1] = null;
        }
        return new Output(outputs);
    }

    /**
     * Executes the done command.
     *
     * @param cml Input commandline
     * @param tasklist Tasklist of tasks
     * @param storage Storage processing unit
     * @return Output generated by the done command
     */
    private String[] executeDone(CommandLine cml, Tasklist tasklist, Storage storage) {
        String[] outputs = new String[2];
        outputs[1] = null;
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
        return outputs;
    }

    /**
     * Executes the delete command.
     *
     * @param cml Input commandline
     * @param tasklist Tasklist of tasks
     * @param storage Storage processing unit
     * @return Output generated by the delete command
     */
    private String[] executeDelete(CommandLine cml, Tasklist tasklist, Storage storage) {
        String[] outputs = new String[2];
        outputs[1] = null;
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
        return outputs;
    }

    /**
     * Executes the todo command.
     *
     * @param cml Input commandline
     * @param tasklist Tasklist of tasks
     * @param storage Storage processing unit
     * @return Output generated by the todo command
     */
    private String[] executeToDo(CommandLine cml, Tasklist tasklist, Storage storage) {
        String[] outputs = new String[2];
        outputs[1] = null;
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
        return outputs;
    }

    /**
     * Executes the deadline command.
     *
     * @param cml Input commandline
     * @param tasklist Tasklist of tasks
     * @param storage Storage processing unit
     * @return Output generated by the deadline command
     */
    private String[] executeDeadline(CommandLine cml, Tasklist tasklist, Storage storage) {
        String[] outputs = new String[2];
        outputs[1] = null;
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
            outputs[0] = "Deadline time must be in the format of yyyy-mm-dd.";
        } catch (InputMismatchException ex) {
            outputs[0] = "Someone's having trouble with deadlines.";
        }
        return outputs;
    }

    /**
     * Executes the event command.
     *
     * @param cml Input commandline
     * @param tasklist Tasklist of tasks
     * @param storage Storage processing unit
     * @return Output generated by the event command
     */
    private String[] executeEvent(CommandLine cml, Tasklist tasklist, Storage storage) {
        String[] outputs = new String[2];
        outputs[1] = null;
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
        return outputs;
    }

    /**
     * Executes the find command.
     *
     * @param cml Input commandline
     * @param tasklist Tasklist of tasks
     * @return Output generated by the find command
     */
    private String[] executeFind(CommandLine cml, Tasklist tasklist) {
        String[] outputs = new String[2];
        outputs[1] = null;
        try {
            if (cml.isSingleCommand()) {
                throw new InputMismatchException();
            }
            outputs[0] = tasklist.find(cml.description);
        } catch (InputMismatchException ex) {
            outputs[0] = "I need a clue to find stuff!";
        }
        return outputs;
    }

    /**
     * Executes the list command.
     *
     * @param cml Input commandline
     * @param tasklist Tasklist of tasks
     * @return Output generated by the list command
     */
    private String[] executeList(CommandLine cml, Tasklist tasklist) {
        String[] outputs = new String[2];
        outputs[1] = null;
        outputs[0] = cml.isSingleCommand()
                ? tasklist.list()
                : "Listing is simple, so typing \"list\" would suffice.";
        return outputs;
    }

    /**
     * Executes the bye command.
     *
     * @param cml Input commandline
     * @return Output generated by the bye command
     */
    private String[] executeBye(CommandLine cml) {
        String[] outputs = new String[2];
        outputs[1] = null;
        outputs[0] = cml.isSingleCommand()
                ? "Hasta la vista, baby."
                : "Type \"bye\" to see me go.";
        return outputs;
    }
}

