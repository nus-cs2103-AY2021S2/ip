package blarb;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

/**
 * {@code Blarb} is the chat bot in used in the {@code Main} program.
 */
public class Blarb {
    private final Tasklist tasklist;
    private final String filePath;
    private final Ui ui;
    private final Storage storage;

    /**
     * Initializes Blarb with a given storage path.
     *
     * @param filePath The storage file path
     */
    public Blarb(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        tasklist = new Tasklist();
        ui.rollCredits();

        try {
            tasklist.addAll(storage.load());
        } catch (IOException ex) {
            ui.warn("Cannot initialize task list from storage.");
        } catch (InputMismatchException ex) {
            ui.warn("File is corrupted. Cannot initialize task list.");
        } finally {
            ui.blurt("This is BLARB.\nYou may speak.");
        }
    }

    /**
     * Initializes Blarb.
     */
    public Blarb() {
        this("data/tasklist.txt");
    }

    /**
     * Runs Blarb in an input loop.
     */
    public void run() {
        boolean active = true;
        while (active && ui.isAvailable()) {
            active = execute(ui.read());
        }
    }

    /**
     * Determines course of action for Blurb.
     *
     * @param input The inputted command.
     * @return A boolean value that shows the availability for the next command intake.
     */
    public boolean execute(String input) {
        CommandLine cml = Parser.parse(input);
        switch (cml.command) {
        case DONE:
            try {
                int index = Integer.parseInt(cml.description) - 1;
                try {
                    String output = tasklist.done(index);
                    try {
                        storage.refile(tasklist);
                    } catch (IOException ex) {
                        ui.warn("Cannot access storage.");
                    }
                    ui.blurt(output);
                } catch (IndexOutOfBoundsException ex) {
                    ui.blurt("There is no such task.");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                ui.blurt("What have you done! More specific!");
            } catch (NumberFormatException ex) {
                ui.blurt("Done what now? I don't understand");
            }
            break;
        case DELETE:
            try {
                int index = Integer.parseInt(cml.description) - 1;
                try {
                    String output = tasklist.delete(index);
                    try {
                        storage.refile(tasklist);
                    } catch (IOException ex) {
                        ui.warn("Cannot access storage.");
                    }
                    ui.blurt(output);
                } catch (IndexOutOfBoundsException ex) {
                    ui.blurt("There is no such task.");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                ui.blurt("What do you want to hide?");
            } catch (NumberFormatException ex) {
                ui.blurt("You can't delete your past.");
            }
            break;
        case TODO:
            try {
                if (cml.isSingleCommand()) {
                    throw new InputMismatchException();
                }
                Task task = new ToDo(cml.description);
                String output = tasklist.add(task);
                try {
                    storage.file(task);
                } catch (IOException ex) {
                    ui.warn("Cannot access storage.");
                }
                ui.blurt(output);
            } catch (InputMismatchException ex) {
                ui.blurt("Todo what?");
            }
            break;
        case DEADLINE:
            try {
                if (cml.isSingleCommand()) {
                    throw new InputMismatchException();
                }
                String[] fragments = cml.description.split(" /by ");
                try {
                    Task task = new Deadline(fragments[0], fragments[1]);
                    String output = tasklist.add(task);
                    try {
                        storage.file(task);
                    } catch (IOException ex) {
                        ui.warn("Cannot access storage.");
                    }
                    ui.blurt(output);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    ui.blurt("Type the deadline, then give the time using \"/by\".");
                } catch (DateTimeParseException ex) {
                    ui.blurt("blarb.Deadline time must be in the format of yyyy-mm-dd.");
                }
            } catch (InputMismatchException ex) {
                ui.blurt("Someone's having trouble with deadlines.");
            }
            break;
        case EVENT:
            try {
                if (cml.isSingleCommand()) {
                    throw new InputMismatchException();
                }
                String[] fragments = cml.description.split(" /at ");
                try {
                    Task task = new Event(fragments[0], fragments[1]);
                    String output = tasklist.add(task);
                    try {
                        storage.file(task);
                    } catch (IOException ex) {
                        ui.warn("Cannot access storage.");
                    }
                    ui.blurt(output);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    ui.blurt("Type the event, then give the time using \"/at\".");
                }
            } catch (InputMismatchException ex) {
                ui.blurt("Tell me the event!");
            }
            break;
        case FIND:
            try {
                if (cml.isSingleCommand()) {
                    throw new InputMismatchException();
                }
                ui.blurt(tasklist.find(cml.description));
            } catch (InputMismatchException ex) {
                ui.blurt("I need a clue to find stuff!");
            }
            break;
        case LIST:
            String output = cml.isSingleCommand()
                    ? tasklist.list()
                    : "Listing is simple, so typing \"list\" would suffice.";
            ui.blurt(output);
            break;
        case BYE:
            ui.adios();
            return false;
        default:
            ui.blurt(String.format("I have detailed files on human anatomy, but not %s.", input));
        }
        return true;
    }
}
