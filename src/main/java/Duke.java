/**
 * Duke program maintains a taskList for user to track tasks.
 * Reads user input tasks(todo, event, deadline).
 * Able to perfomr add, delete, markasDone tasks.
 *
 * @author Oh Jun Ming
 * @version 1.0
 * @Since 2020-01-17
 */
import java.util.Scanner;

public class Duke {
    private static Boolean doExit = false;

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Duke.initiate(s);
    }

    public static void initiate(Scanner s) {
        Duke.Greet();
        TaskList tasklist = new TaskList();
        while(s.hasNextLine()){
            try {
                Duke.process(s, tasklist);
                if (doExit.equals(true)) {
                    break;
                }
            } catch (EmptyDescription e) {
                Duke.output(e.toString());
            } catch (InvalidTypeofTask e) {
                Duke.output(e.toString());
            }
        }
        Duke.exit();
    }

    public static void process(Scanner s, TaskList tasklist) throws InvalidTypeofTask, EmptyDescription {
        String input = s.nextLine();
        Parser p = new Parser();
        p = p.parse(input);
//        System.out.println(p);
        String typeofTask = p.getTypeOfTask();

        if (typeofTask.equals("bye")) {
            doExit = true;
        } else if (typeofTask.equals("list")) {
            tasklist.list();
        } else if (typeofTask.equals("done")) {
            tasklist.markAsDone(p);
        } else if (typeofTask.equals("delete")) {

            tasklist.delete(p);
        } else if (typeofTask.equals("todo") || typeofTask.equals("deadline") ||
                        typeofTask.equals("event")){
            tasklist.add(p);
        }  else {
            throw new InvalidTypeofTask();
        }
    }

    public static void Greet() {
        Duke.output(Response.GREET.toString());
    }

    public static void echo(String msg) {
        Duke.output(msg+ "\n");
    }

    public static void exit() {
        Duke.output(Response.EXIT.toString());
    }

    public static void output(String response) {
        System.out.println("---------------------------------------");
        System.out.println(response);
        System.out.println("---------------------------------------\n");
    }
}
