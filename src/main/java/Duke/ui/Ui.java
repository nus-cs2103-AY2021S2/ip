package duke.ui;

import duke.common.Response;
import duke.exception.EmptyDescription;
import duke.exception.InvalidTypeOfTask;
import duke.parser.Parser;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private Boolean shouldExit = false;

    public Ui() {

    }
    public TaskList readCommand(TaskList taskList1, Scanner s) throws InvalidTypeOfTask, EmptyDescription {
        String input = s.nextLine();
        Parser p = new Parser();
        p = p.parse(input);
        String typeofTask = p.getTypeOfTask();
        TaskList taskList = taskList1;

        switch (typeofTask) {
        case "bye":
            shouldExit = true;
            break;
        case "list":
            taskList.list();
            break;
        case "find":
            ////////////////
            taskList.find(p);
            break;
        case "done":
            taskList.markAsDone(p);
            break;
        case "delete":
            taskList.delete(p);
            break;
        case "todo":
        case "deadline":
        case "event":
            taskList.add(p);
            break;
        default:
            throw new InvalidTypeOfTask();
        }
        return taskList;
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
        enclose(msg+ "\n");
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
