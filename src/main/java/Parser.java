public class Parser {
    private static final String EXIT_COMMAND = "bye";

    public boolean parse(String input, Tasks tasks) {
        if (input.equals(EXIT_COMMAND)) {
            // close program
            Duke.formatText();
            System.out.println("Bye, see you soon! Don't miss me too much.");
            Duke.formatText();
            return true;
        } else if (input.equals("list")) {
            // show everything in the list
            Duke.formatText();
            tasks.iterateList();
            Duke.formatText();
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
