package duke.ui;
import java.util.Scanner;
import duke.common.Command;
import duke.common.Response;
import duke.exception.EmptyDescription;
import duke.exception.InvalidTypeOfTask;
import duke.parser.Parser;
import duke.task.TaskList;

public class Ui {
    private Boolean shouldExit = false;

    public Ui() {

    }

    /**
     * Reads command user input.
     *
     * @param taskList
     * @param s
     * @return TaskList
     * @throws InvalidTypeOfTask
     * @throws EmptyDescription
     */
    public TaskList readCommand(TaskList taskList, Scanner s) throws InvalidTypeOfTask, EmptyDescription {
        String input = s.nextLine();
        Parser p = new Parser();
        p = p.parse(input);
        Command command = p.getCommand();
        TaskList newTaskList = taskList;

        switch (command) {
        case BYE:
            shouldExit = true;
            break;
        case LIST:
            taskList.list();
            break;
        case FIND:
            taskList.find(p);
            break;
        case DONE:
            taskList.markAsDone(p);
            break;
        case DELETE:
            taskList.delete(p);
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            taskList.add(p);
            break;
        default:
            throw new InvalidTypeOfTask();
        }
        return newTaskList;
    }


    public Boolean getExit() {
        return shouldExit;
    }


    /**
     * Greets user.
     */
    public void greet() {
        enclose(Response.GREET.toString());
    }

    /**
     * Echo user input.
     */
    public void echo(String msg) {
        enclose(msg + "\n");
    }

    /**
     * Say bye bye
     */
    public void exit() {
        enclose(Response.EXIT.toString());
    }

    /**
     * Prints output to user in generic format.
     */
    public void enclose(String reply) {
        System.out.println("---------------------------------------");
        System.out.println(reply);
        System.out.println("---------------------------------------\n");
    }
}
