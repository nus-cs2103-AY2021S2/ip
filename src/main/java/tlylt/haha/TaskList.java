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
    void tellAdd() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Outputs information about size of database.
     */
    void tellSize() {
        String task = database.size() > 1 ? " tasks" : " task";
        System.out.println("Now you have " + database.size() + task + " in the list");
    }

    /**
     * Adds task into database.
     *
     * @param task Task created by user.
     */
    void addToDB(Task task) {
        database.add(task);
        tellAdd();
        System.out.println("  " + database.get(database.size() - 1));
        tellSize();
    }

    /**
     * Removes task from database.
     *
     * @param inputNum String that will be parsed for task number.
     */
    void deleteFromDB(String inputNum) {
        try {
            int num = Parser.taskNumber(inputNum);
            Task currentTask = database.get(num - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(currentTask);
            database.remove(currentTask);
            tellSize();
        } catch (HahaTaskNumberNotIntException ex) {
            System.out.println(ex);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("OOPS! Wrong number!\nTry specify the right task number");
        }
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
    void listFromDB() {
        if (database.size() == 0) {
            System.out.println("You have nothing going on!");
        } else {
            System.out.println("Here are your list of tasks:");
            for (int i = 0; i < database.size(); i++) {
                String idx = Integer.toString(i + 1) + '.';
                String task = idx + database.get(i);
                System.out.println(task);
            }
        }
    }

    /**
     * Marks selected task as done.
     *
     * @param inputNum String for parsing task number.
     */
    void markDoneToDB(String inputNum) {
        try {
            int givenIndex = Parser.taskNumber(inputNum) - 1;
            if (givenIndex < 0 || givenIndex >= database.size()) {
                System.out.println("OOPS! Wrong number!\nTry specify the right task number");
            } else {
                Task currentTask = database.get(givenIndex);
                if (currentTask.getIsDone()) {
                    System.out.println("OOPS! I've marked this task as done ALREADY");
                } else {
                    System.out.println("Nice! I've marked this task as done:");
                    currentTask.setDone(true);
                    System.out.println(currentTask);
                }
            }
        } catch (HahaTaskNumberNotIntException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Responds to the respective command and perform database related tasks.
     *
     * @param command Valid command given by user.
     * @param ui      Ui component.
     * @return Whether the user wants to exit.
     */
    boolean executeCommand(LegitCommand command, Ui ui) {
        switch (command) {
        case BYE:
            ui.bye();
            return true;
        case TODO:
            this.addToDB(new Todo(false, LegitCommand.TODO.getDetail()));
            this.updateFile();
            break;
        case EVENT:
            this.addToDB(new Event(false, LegitCommand.EVENT.getDetail()));
            this.updateFile();
            break;
        case DEADLINE:
            this.addToDB(new Deadline(false, LegitCommand.DEADLINE.getDetail()));
            this.updateFile();
            break;
        case LIST:
            this.listFromDB();
            break;
        case DONE:
            this.markDoneToDB(LegitCommand.DONE.getDetail());
            this.updateFile();
            break;
        case DELETE:
            this.deleteFromDB(LegitCommand.DELETE.getDetail());
            this.updateFile();
            break;
        }
        return false;
    }

}
