package duke.task;

public class Notes extends Task {
    public Notes(String task) {
        super(task);
    }

    String[] divideCommand = task.split(" ");

    public String createNote(String[] c) {
        String result = "";
        for (int i = 1; i < c.length; i++) {
            result += i == c.length - 1 ? c[i] : c[i] + " ";
        }
        return result;
    }

    @Override
    public String toString() {
        return createNote(divideCommand);
    }
}
