package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * A chat bot that accepts certain commands and keeps track of them in a
 * seperate file. The commands are similar to a calendar where the user would
 * like to keep track of todo events and deadlines that are upcoming. Tasks can
 * be marked as done or pending, time can be specified for time sensitive
 * events.
 */
public class Duke {
    public static void main(String[] args) {

        Ui.greeting();

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        TaskList taskList = new TaskList();

        Parser parser = new Parser(sc.nextLine());
        while (parser.isCommandNotBye()) {
            try {
                Command taskType = parser.getCommand();
                switch (taskType) {
                    case LIST:
                        Ui.execute(Command.LIST, taskList, null);
                        break;
                    case DELETE:
                        Task deletedTask = taskList.get(parser.getTaskIndex() - 1);
                        taskList.delete(parser.getTaskIndex());
                        Ui.execute(Command.DELETE, taskList, deletedTask.getStatus());
                        break;
                    case DONE:
                        Task doneTask = taskList.get(parser.getTaskIndex() - 1);
                        doneTask.setDone(true);
                        Ui.execute(Command.DONE, taskList, doneTask.getStatus());
                        break;
                    case DEADLINE:
                    case EVENT:
                    case TODO:
                        if (parser.hasDescriptionError()) {
                            Ui.exception(parser.getDescription());
                        } else if (!taskType.equals(Command.TODO) && parser.hasDateError()) {
                            Ui.exception(parser.getDate());
                        }
                        Task task = taskType.equals(Command.EVENT)
                                ? new Event(parser.getDescription(), LocalDate.parse(parser.getDate(), formatter))
                                : taskType.equals(Command.TODO) ? new ToDo(parser.getDescription())
                                        : new Deadline(parser.getDescription(),
                                                LocalDate.parse(parser.getDate(), formatter));
                        taskList.add(task);
                        Ui.execute(Command.TODO, taskList, task.getStatus());
                        break;
                    case FIND:
                        Ui.execute(Command.FIND, taskList, parser.getDescription());
                        break;
                    case NONE:
                        throw new TaskException("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    case BYE:
                        break;

                    default:
                        break;

                }

            } catch (TaskException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
            Storage.createAndWrite("savedTasks.txt", taskList);
            parser = new Parser(sc.nextLine());
        }
        sc.close();
        Ui.execute(Command.BYE, taskList, null);

    }
}
