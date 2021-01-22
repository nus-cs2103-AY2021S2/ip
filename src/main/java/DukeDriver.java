import java.util.Scanner;

public class DukeDriver {

    public static void executeDuke() {
        Scanner sc = new Scanner(System.in);
        System.out.println(Format.LOGO);
        System.out.println(Format.greeting);

        while(sc.hasNext()) {
            String message = sc.nextLine();
            boolean bye = inputHandler(message);
            if (bye) {
                break;
            }
        }
        System.out.println(Format.farewell);
    }

    /**
     * handle done command key in by user.
     *
     * @param line String array of the full line key in by user.
     */
    private static final void handleDone(String[] line) {
        if (line.length == 2) {
            String arg = line[1].replaceAll("[^0-9]", "");
            try {
                int num = Integer.parseInt(arg);
                Task.done(num);
            } catch (NumberFormatException e) {
                System.out.println("Please lah, key in number");
            }
        } else {
            DukeException.argumentErrorException();
        }
    }

    /**
     * handle todo command key in by user.
     *
     * @param line String array of the full line key in by user.
     * @param message the name of the task to be set.
     */
    private static final void handleToDo(String[] line, String message) {
        if (line.length > 1) {
            String msg = message.replaceAll(line[0], "")
                    .trim();
            Todo todo = new Todo(msg);
            System.out.println(Format.biggerBox(todo));
        } else {
            DukeException.argumentErrorException();
        }
    }

    /**
     * handle deadline command key in by user.
     *
     * @param line String array of the full line that user key in.
     * @param message String representation of the name of the task key in.
     */
    private static final void handleDeadline(String[] line, String message) {
        if (line.length > 2) {
            String[] comments = message.trim().toLowerCase().split("/");
            String msg = comments[0].replaceAll(line[0], "").trim();
            if (comments.length == 2) {
                if (msg.equals("")) {
                    DukeException.emptyBodyException();
                } else {
                    Deadlines deadline = new Deadlines(msg, comments[1]);
                    System.out.println(Format.biggerBox(deadline));
                }
            } else {
                DukeException.slashErrorException();
            }

        } else {
            DukeException.missingArgumentErrorException();
        }
    }

    /**
     * handle event command key in by user.
     *
     * @param line String array of the full line key in by user.
     * @param message String representation of the name of the task key in by user.
     */
    private static final void handleEvent(String[] line, String message) {
        if (line.length > 2) {
            String[] comments = message.trim().toLowerCase().split("/");
            String msg = comments[0].replaceAll(line[0], "").trim();
            if (comments.length == 2) {
                if (msg.equals("")) {
                    DukeException.emptyBodyException();
                } else {
                    Event event = new Event(msg, comments[1]);
                    System.out.println(Format.biggerBox(event));
                }
            } else {
                DukeException.slashErrorException();
            }
        } else {
            DukeException.missingArgumentErrorException();
        }
    }


    /**
     * The main handler of all the command.
     *
     * @param message String representation of the message key in by user.
     * @return a boolean to whether to exit the program.
     */
    private static final boolean inputHandler(String message) {
        String[] line = message.trim().toLowerCase().split(" ");
        String command = line[0];
        if (command.equals("bye")) {
            return true;
        } else {
            if (command.equals("list")) {
                Format.LISTING();
            } else if (command.equals("done")) {
                handleDone(line);
            } else if (command.equals("todo")) {
                handleToDo(line, message);
            } else if (command.equals("deadline")) {
                handleDeadline(line, message);
            } else if (command.equals("event")){
                handleEvent(line, message);
            } else {
                DukeException.commandErrorException();
            }
            return false;
        }
    }

    private static final String extractCommand(String input) {
        return input.trim().toLowerCase().split(" ")[0];
    }
}
