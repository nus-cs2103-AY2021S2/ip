package duke;

import java.time.format.DateTimeParseException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.function.Function;

public class Parser {

    private final TaskList mem;
    private final Hashtable<String, Function<String, String>> functions;
    private String history;

    /**
     * Loads the txt file into mem and displays the welcome message
     */
    public Parser() {
        Storage storage = new Storage();
        this.mem = new TaskList(storage.load(), storage);
        functions = new Hashtable<String, Function<String, String>>();
        this.initialize();
        this.history = Ui.welcome();
    }

    private void initialize() {

        functions.put("done", this::done);

        functions.put("todo", this::toDo);

        functions.put("deadline", this::deadline);

        functions.put("event", this::event);

        functions.put("delete", this::delete);

        functions.put("find", this::find);
        functions.put("taskson", this::tasksOnDay);
    }

    /**
     * Reads the string and determines which function to call.
     *
     * @param userInput user input
     * @return string of the output after executing the command
     */
    public String parse(String userInput) {
        assert(this.functions != null);
        Scanner sc = new Scanner(userInput);
        String inputs = sc.next();
        if (inputs.equals("bye")) {
            return Ui.bye();
        } else if (inputs.equals("list")) {
            return this.list();
        } else if (inputs.equals("clear")) {
            return this.clear();
        } else if (!this.isValidCommand(inputs)) {
            return Ui.invalidInput();
        } else if (sc.hasNext()) {
            return functions.get(inputs).apply(sc.nextLine().stripLeading());
        } else {
            return Ui.emptyDescription(inputs);
        }
    }

    String getPrevChat() {
        return this.history;
    }

    void chat(String s) {
        this.history = this.parse(s);
    }

    private String store(Task t) {
        this.mem.store(t);
        return Ui.store(t, this.mem.size());
    }

    private String list() {
        return Ui.list(this.mem);
    }

    private String done(String s) {
        try {
            int i = Integer.parseInt(s);
            Task t = this.mem.get(i);
            t.finish();
            return Ui.done(t);
        } catch (IndexOutOfBoundsException e) {
            return Ui.outOfArrayRange();
        }
    }

    private String toDo(String s) {
        ToDo t = new ToDo(s);
        return this.store(t);
    }

    private String deadline(String s) {
        try {
            String[] inputs = s.split(" /by ");
            Deadline t = new Deadline(inputs[0], inputs[1]);
            return this.store(t);
        } catch (IndexOutOfBoundsException e) {
            return Ui.missingFlag();
        } catch (DateTimeParseException e) {
            return Ui.wrongDateFormat();
        }
    }

    private String event(String s) {
        try {
            String[] inputs = s.split(" /at ");
            Event t = new Event(inputs[0], inputs[1]);
            return this.store(t);
        } catch (IndexOutOfBoundsException e) {
            return Ui.missingFlag();
        } catch (DateTimeParseException e) {
            return Ui.wrongDateFormat();
        }
    }

    private String delete(String s) {
        try {
            int i = Integer.parseInt(s);
            Task t = this.mem.get(i);
            this.mem.delete(t);
            return Ui.delete(t, this.mem.size());
        } catch (IndexOutOfBoundsException e) {
            return Ui.outOfArrayRange();
        } catch (NumberFormatException e) {
            return Ui.notAnInteger();
        }
    }

    private String tasksOnDay(String s) {
        try {
            return Ui.tasksOnDay(this.mem, s);
        } catch (DateTimeParseException e) {
            return Ui.wrongDateFormat();
        }
    }

    private String find(String s) {
        return Ui.find(this.mem, s);
    }

    private String clear() {
        this.mem.clear();
        return Ui.clear();
    }

    private boolean isValidCommand(String s) {
        return this.functions.containsKey(s);
    }
}
