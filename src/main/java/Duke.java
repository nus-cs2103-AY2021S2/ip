import java.util.Scanner;

public class Duke {
    /** Stores list of tasks */
    TaskList list;

    public Duke() {
        greet();
        try {
            this.list = new TaskList();
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }


    private void greet() {
        Ui.printWithStyle(new String[]{"Hello! I'm Duke", "What can I do for you?"});
    }















    public void bye() {
        Ui.printWithStyle("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (!input.equals("bye")) {
                try {
                    duke.handleInput(input);
                } catch (DukeException e) {
                    Ui.printWithStyle(e.getMessage());
                }
            } else {
                duke.bye();
                break;
            }
        }
    }
}
