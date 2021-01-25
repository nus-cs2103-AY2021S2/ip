public class Parser {
    private static final String EXIT_COMMAND = "bye";

    public boolean parse(String input, Tasks tasks) {
        if (input.equals(EXIT_COMMAND)) {
            // close program
            Ui.formatText();
            Ui.sayGoodbye();
            Ui.formatText();
            return true;
        } else if (input.equals("list")) {
            // show everything in the list
            Ui.formatText();
            tasks.iterateList();
            Ui.formatText();
        } else if (input.split(" ", 2)[0].equals("done")) {
            // mark task with the given index as completed
            tasks.markAsDone(input.split(" ", 2)[1]);
        } else if (input.split(" ", 2)[0].equals("delete")) {
            tasks.deleteTask(input.split(" ", 2)[1]);
        } else {
            // add new task to list
            tasks.addTask(input);
        }
        return false;
    }

}
