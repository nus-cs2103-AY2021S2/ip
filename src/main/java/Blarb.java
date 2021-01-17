import java.util.ArrayList;
import java.util.List;

/**
 * {@code Blarb} is the chat bot in used in the main {@code Duke} program.
 */
public class Blarb {
    private final List<Task> list;

    /**
     * Initializes Blarb
     */
    public Blarb() {
        list = new ArrayList<>(100);
        blurt("This is BLARB.\nYou may speak.");
    }

    /**
     * Prints output in response format.
     * @param output Output string
     */
    private void blurt(String output) {
        String response = "%s\n\n> ";
        System.out.printf(response, output);
    }

    /**
     * Prints the task list.
     */
    private void list() {
        if (list.size() < 1) {
            blurt("You have nothing on your list.");
        } else {
            int i = 1;
            StringBuilder sb = new StringBuilder("Here are your tasks:\n");
            for (Task task : list) {
                sb.append(String.format("\n%d. %s", i++, task.toString()));
            }
            blurt(sb.substring(0));
        }
    }

    /**
     * Changes the indexed task to a completed state.
     * @param index The index of the task.
     */
    private void done(int index) {
        try {
            list.get(index).markAsDone();
            String done = "I've marked this task as done:\n%s";
            blurt(String.format(done, list.get(index)));
        } catch (IndexOutOfBoundsException ex) {
            blurt("There is no such task.");
        }
    }

    /**
     * Delete the indexed task.
     * @param index The index of the task.
     */
    private void delete(int index) {
        try {
            String delete = "The task is terminated:\n%s";
            blurt(String.format(delete, list.get(index)));
            list.remove(index);
        } catch (IndexOutOfBoundsException ex) {
            blurt("There is no such task.");
        }
    }

    /**
     * Adds a new Task to the task list.
     * @param task Task to be added.
     */
    private void add(Task task) {
        String addTask = "Affirmative. I've added this task:\n %s\n"
                + "Now you have %d tasks in the list.";
        list.add(task);
        blurt(String.format(addTask, task.toString(), list.size()));
    }

    /**
     * Parses and determines course of action for Blurb.
     * @param input The inputted command.
     * @return A boolean value that shows the availability for the next command intake.
     */
    public boolean execute(String input) {
        if (input.equalsIgnoreCase("bye")) {
            System.out.println("Hasta la vida, baby.");
            return false;
        }

        //Splits the input into two parts -- the command and the description.
        String[] tokens = input.split(" ", 2);

        if (tokens[0].equalsIgnoreCase("list") && tokens.length < 2) {
            list();
        } else if (tokens[0].equalsIgnoreCase("done")) {
            try {
                int idx = Integer.parseInt(tokens[1]) - 1;
                done(idx);
            } catch (ArrayIndexOutOfBoundsException ex) {
                blurt("What have you done! More specific!");
            } catch (NumberFormatException ex) {
                blurt("Done what now? I don't understand");
            }
        } else if (tokens[0].equalsIgnoreCase("delete")) {
            try {
                int idx = Integer.parseInt(tokens[1]) - 1;
                delete(idx);
            } catch (ArrayIndexOutOfBoundsException ex) {
                blurt("What do you want to hide?");
            } catch (NumberFormatException ex) {
                blurt("You can't delete your past.");
            }
        } else if (tokens[0].equalsIgnoreCase("todo")) {
            try {
                Task task = new ToDo(tokens[1]);
                add(task);
            } catch (ArrayIndexOutOfBoundsException ex) {
                blurt("Todo what?");
            }
        } else if (tokens[0].equalsIgnoreCase("deadline")) {
            try {
                String[] fragments = tokens[1].split(" /by ");
                try {
                    Task task = new Deadline(fragments[0], fragments[1]);
                    add(task);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    blurt("Type the deadline, then give the time using \"/by\".");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                blurt("Someone's having trouble with deadlines.");
            }
        } else if (tokens[0].equalsIgnoreCase("event")) {
            try {
                String[] fragments = tokens[1].split(" /at ");
                try {
                    Task task = new Event(fragments[0], fragments[1]);
                    add(task);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    blurt("Type the event, then give the time using \"/at\".");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                blurt("Tell me the event!");
            }
        } else {
            blurt(String.format("I have detailed files on human anatomy, but not %s.", input));
        }

        return true;
    }
}

