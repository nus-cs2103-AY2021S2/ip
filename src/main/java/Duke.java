import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Duke {
    private static boolean exit = false;
    private static List<Task> tasks = new ArrayList<>();

    private static final Ui ui = new Ui();

    public static void main(String[] args) {
        ui.displayIntro();
        while (exit == false) {
            processInput(ui.readCommand());
        }
        ui.close();
    }


    private static void processInput(String userInput) {
        String keyword_UC = userInput.toUpperCase().split(" ", -1)[0];
        Queries query = Queries.ADD;
        String response = "";

        if (Queries.containsValue(keyword_UC)) {
            query = Queries.valueOf(keyword_UC);
        }

        try {
            switch (query) {
            case BYE:
                response = "Bye. Hope to see you again soon!";
                ui.respond(response);
                exit = true;
                break;

            case ADD:
                Task toAdd;
                try {
                    if (keyword_UC.equals("TODO")) {
                        toAdd = new Todo(userInput.split(" ", 2)[1]);
                    } else if (keyword_UC.equals("DEADLINE")) {
                        String[] info = userInput.split(" ", 2);
                        if (info[1].contains("/by")) {
                            info = info[1].split("/by");
                        } else {
                            throw new DukeInvalidDesException(keyword_UC);
                        }
                        toAdd = new Deadline(info[0], info[1]);
                    } else if (keyword_UC.equals("EVENT")) {
                        String[] info = userInput.split(" ", 2);
                        if (info[1].contains("/at")) {
                            info = info[1].split(("/at"));
                        } else {
                            throw new DukeInvalidDesException(keyword_UC);
                        }
                        toAdd = new Event(info[0], info[1]);
                    } else {
                        throw new DukeIDKException();
                    }
                    tasks.add(toAdd);
                    response = "Got it. I've added this task:\n"
                            + " " + toAdd + "\n"
                            + "Now you have " + tasks.size() + " tasks in the list.\n";
                    ui.respond(response);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingDesException(keyword_UC);
                }
                break;

            case DONE:
                try {
                    int taskNum = Integer.parseInt(userInput.split(" ")[1]);
                    Task task = tasks.get(taskNum - 1);
                    Task updatedTask = task.markDone();
                    tasks.set(taskNum - 1, updatedTask);
                    response = "Nice! I've marked this task as done: \n"
                            + " " + updatedTask + "\n";
                    ui.respond(response);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingDesException("DONE");
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new DukeInvalidDesException("DONE");
                }
                break;

            case DELETE:
                try {
                    int taskNum = Integer.parseInt(userInput.split(" ")[1]);
                    Task task = tasks.get(taskNum - 1);
                    tasks.remove(taskNum - 1);
                    response = "Noted. I've removed this task: \n"
                            + " " + task + "\n";
                    ui.respond(response);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeMissingDesException("DELETE");
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new DukeInvalidDesException("DELETE");
                }
                break;
            case LIST:
                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    response += Integer.toString(i + 1) + "."
                            + t + "\n";
                }
                ui.respond(response);
                break;
            }
        } catch (DukeException e) {
            String output = e.getMessage();
            ui.respond(output);
        }

    }


}

