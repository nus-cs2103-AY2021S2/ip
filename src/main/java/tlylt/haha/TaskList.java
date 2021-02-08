package tlylt.haha;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a task handler.
 * Mainly for handling of task after receiving user input.
 * "database" in this class refers to the main data structure that will
 * hold all the information when program is running.
 */
public class TaskList {
    private final List<Task> database = new ArrayList<>();

    /**
     * Outputs information about adding of task to database.
     */
    String tellAdd() {
        return "Got it. I've added this task:\n";
    }

    /**
     * Outputs information about size of database.
     */
    String tellSize() {
        String task = database.size() > 1 ? " tasks" : " task";
        return String.format("Now you have %d %s in the list\n", database.size(), task);
    }

    /**
     * Adds task into database.
     *
     * @param task Task created by user.
     */
    String addToDB(Task task) {
        database.add(task);

        String response = tellAdd();
        response += "  " + database.get(database.size() - 1) + "\n";
        response += tellSize();
        return response;
    }

    /**
     * Removes task from database.
     *
     * @param inputNum String that will be parsed for task number.
     */
    String deleteFromDB(String inputNum) {
        String response = "";
        try {
            int num = Parser.getTaskNumber(inputNum);
            Task currentTask = database.get(num - 1);
            response += "Noted. I've removed this task:\n";
            response += currentTask + "\n";
            database.remove(currentTask);
            response += tellSize();
        } catch (HahaTaskNumberNotIntException ex) {
            return ex.toString();
        } catch (IndexOutOfBoundsException ex) {
            return "OOPS! Wrong number!\nTry specify the right task number\n";
        }
        return response;
    }

    /**
     * Reads tasks from a file that contains previous usage.
     *
     * @param file Content of previously recorded tasks.
     */
    void readTasks(List<String> file) {
        List<Task> tasks = new ArrayList<>();
        file.forEach(line -> tasks.add(Parser.parseLine(line)));
        database.addAll(tasks);
    }

    /**
     * Updates database to file.
     */
    void updateFile() {
        List<String> str = new ArrayList<>();
        database.forEach(task -> str.add(task.fileStorageFormat()));
        try {
            Files.write(Paths.get(System.getProperty("user.dir"), "Haha_data", "database.txt"),
                    str, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lists out all the tasks in database.
     */
    String listFromDB() {
        String response = "";
        if (database.size() == 0) {
            response += "You have nothing going on!\n";
        } else {
            response += "Here are your list of tasks:\n";
            for (int i = 0; i < database.size(); i++) {
                String idx = Integer.toString(i + 1) + '.';
                String task = idx + database.get(i);
                response += task + "\n";
            }
        }
        return response;
    }

    private String findFromDB(String keyword, Ui ui) {
        String response = "";
        response += "Here are the matching tasks in your list:\n";
        boolean hasRelated = false;
        for (int i = 0; i < database.size(); i++) {
            String idx = Integer.toString(i + 1) + '.';
            Task currentTask = database.get(i);
            if (currentTask.description.contains(keyword)) {
                String task = idx + database.get(i);
                System.out.println(task);
                response += task + "\n";
                hasRelated = true;
            }
        }
        if (!hasRelated) {
            response += ui.taskNotFound();
        }
        return response;
    }

    /**
     * Marks selected task as done.
     *
     * @param inputNum String for parsing task number.
     */
    String markDoneToDB(String inputNum) {
        String response = "";
        try {
            int givenIndex = Parser.getTaskNumber(inputNum) - 1;
            if (givenIndex < 0 || givenIndex >= database.size()) {
                return "OOPS! Wrong number!\nTry specify the right task number\n";
            }

            Task currentTask = database.get(givenIndex);
            if (currentTask.getIsDone()) {
                return "OOPS! I've marked this task as done ALREADY\n";
            }
            response += "Nice! I've marked this task as done:\n";
            currentTask.setDone(true);
            response += currentTask + "\n";

        } catch (HahaTaskNumberNotIntException ex) {
            return ex.toString();
        }
        return response;
    }

    /**
     * Responds to the respective command and perform database related tasks.
     *
     * @param command Valid command given by user.
     * @param ui      Ui component.
     * @return Whether the user wants to exit.
     */
    String executeCommand(LegitCommand command, Ui ui) {
        String response = "";
        switch (command) {
        case BYE:
            return ui.bye();
        case TODO:
            response += this.addToDB(new Todo(false, LegitCommand.TODO.getDetail()));
            this.updateFile();
            break;
        case EVENT:
            response += this.addToDB(new Event(false, LegitCommand.EVENT.getDetail()));
            this.updateFile();
            break;
        case DEADLINE:
            response += this.addToDB(new Deadline(false, LegitCommand.DEADLINE.getDetail()));
            this.updateFile();
            break;
        case LIST:
            return this.listFromDB();
        case DONE:
            response += this.markDoneToDB(LegitCommand.DONE.getDetail());
            this.updateFile();
            break;
        case FIND:
            return this.findFromDB(LegitCommand.FIND.getDetail(), ui);
        case DELETE:
            response += this.deleteFromDB(LegitCommand.DELETE.getDetail());
            this.updateFile();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + command);
        }
        return response;
    }


}
