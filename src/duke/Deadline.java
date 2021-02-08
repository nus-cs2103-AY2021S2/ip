package duke;

public class Deadline extends Task {
    String by;

    Deadline() {

    }
    Deadline(boolean isDone, String task, String by) {
        this.isDone = isDone;
        this.task = task;
        this.by = by;
    }
    Deadline(String input) throws DukeIncompleteCommandException {
        input = input.substring(8).trim();

        if (input.equals("")) {
            throw new DukeIncompleteCommandException();
        }

        String[] inputs = input.split("/by");

        if (!input.contains("/by") || inputs.length < 2) {
            throw new DukeIncompleteCommandException("Oh no! Please enter an due date. :P");
        }
        this.task = inputs[0].trim();
        this.isDone = false;
        this.by = inputs[1].trim();
    }

    static Deadline fileReader(String line) {
        Deadline deadline = new Deadline();
        if (line.charAt(5) == 'X') {
            deadline.isDone = true;
        } else {
            deadline.isDone = false;
        }
        String[] lines = line.substring(7).trim().split("by: ");
        deadline.task = lines[0].substring(0, lines[0].length() - 2).trim();
        deadline.by = lines[1].substring(0, lines[1].length() - 1);
        return deadline;
    }
    @Override
    public String toString() {
        return String.format("DDLN%s (by: %s)" ,
                super.toString(), by);
    }
}
