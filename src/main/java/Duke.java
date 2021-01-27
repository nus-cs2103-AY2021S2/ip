/**
 * Duke program maintains a taskList for user to track tasks.
 * Reads user input tasks(todo, event, deadline).
 * Able to perform add, delete, markasDone tasks.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
import java.util.Scanner;

public class Duke {
    private static Boolean shouldExit = false;

    /**
     * Initialise scanner.
     *
     * @param args
     */
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Duke.initiate(s);
    }

    /**
     * Start Duke chat services.
     *
     * @param s Scanner variable for user input.
     */
    public static void initiate(Scanner s) {
        Duke.Greet();
        TaskList tasklist = new TaskList();
        while(s.hasNextLine()){
            try {
                Duke.process(s, tasklist);
                if (shouldExit.equals(true)) {
                    break;
                }
            } catch (EmptyDescription e) {
                Duke.output(e.toString());
            } catch (InvalidTypeOfTask e) {
                Duke.output(e.toString());
            }
        }
        Duke.exit();
    }

    /**
     * Calls the respective typeOfTask.
     *
     * @param s Scanner variable for user input.
     * @param tasklist List of tasks.
     * @throws InvalidTypeOfTask
     * @throws EmptyDescription
     */
    public static void process(Scanner s, TaskList tasklist) throws InvalidTypeOfTask, EmptyDescription {
        String input = s.nextLine();
        Parser p = new Parser();
        p = p.parse(input);
        String typeofTask = p.getTypeOfTask();

        switch (typeofTask) {
        case "bye":
            shouldExit = true;
            break;
        case "list":
            tasklist.list();
            break;
        case "done":
            tasklist.markAsDone(p);
            break;
        case "delete":
            tasklist.delete(p);
            break;
        case "todo":
        case "deadline":
        case "event":
            tasklist.add(p);
            break;
        default:
            throw new InvalidTypeOfTask();
        }
    }

    /**
     * Greets user.
     */
    public static void Greet() {
        Duke.output(Response.GREET.toString());
    }

    /**
     * Echo user input.
     */
    public static void echo(String msg) {
        Duke.output(msg+ "\n");
    }

    /**
     * Saying bye to user.
     */
    public static void exit() {
        Duke.output(Response.EXIT.toString());
    }

    /**
     * Prints output to user in generic format.
     */
    public static void output(String response) {
        System.out.println("---------------------------------------");
        System.out.println(response);
        System.out.println("---------------------------------------\n");
    }
}
