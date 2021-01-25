package main.java.subfiles;

public class Parser {
    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Calls the task manager to perform either task addition,
     * deletion, printing, or marking as done, based on the
     * user input. Also terminates the program if a terminating
     * input is supplied by the user.
     *
     * @param s A user input.
     * @return True if the user input is not a terminating
     *         input, and false otherwise.
     */
    public boolean parse(String s) {
        String[] sArray = s.split(" ");

        switch (sArray[0]) {
        case "bye":
            return false;
        case "list":
            ui.printTasks(s);
            break;
        case "done":
            ui.inputDone(sArray[1]);
            break;
        case "delete":
            ui.inputDelete(sArray[1]);
            break;
        default:
            ui.inputAdd(s);
            break;
        }
        return true;
    }
}
