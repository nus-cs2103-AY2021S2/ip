import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    static final String LINE_AFTER_COMMAND = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println(LINE_AFTER_COMMAND + "\nHello! I'm  Duke");
        System.out.println("What can I do for you?\n" + LINE_AFTER_COMMAND + "\n");
        List<Task> tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")){
                doBye();
                break;
            }
            System.out.println(LINE_AFTER_COMMAND);
            String[] inputs = command.split(" ");
            try {
                if (command.equals("list")) {
                    iterateList(tasks);
                } else if (inputs[0].equals("done")) {
                    finishATask(tasks, inputs);
                }
                else if (inputs[0].equals("delete")) {
                    deleteATask(tasks, inputs);
                } else {
                    Task task = processTask(inputs[0], command);
                    System.out.println("Got it. I've added this task:");
                    tasks.add(task);
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(LINE_AFTER_COMMAND + "\n");
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(LINE_AFTER_COMMAND + "\n");
            }
            catch (DukeExceptionDeadline e) {
                System.out.println(e.getMessage());
                System.out.println(LINE_AFTER_COMMAND + "\n");
            }
        }
    }

    public static void doBye() {
        System.out.println(LINE_AFTER_COMMAND + "\nBye. Hope to see you again soon!\n" + LINE_AFTER_COMMAND + "\n");
    }

    public static void iterateList(List<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("There are no task in your list");
            System.out.println(LINE_AFTER_COMMAND + "\n");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.valueOf(i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE_AFTER_COMMAND + "\n");
    }

    public static Task processTask(String command, String input) throws DukeException, DukeExceptionDeadline {
        Task task = new Task("");
        try {
            if (command.equals("todo")) {
                if (input.substring(5).equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new Todo(input.substring(5));
                return task;
            } else if (command.equals("deadline")) {
                if (input.substring(9).equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                String[] spliced = input.substring(9).split(" /by ");
                System.out.println(spliced[0]);
                System.out.println(spliced[1]);
                task = new Deadline(spliced[0], spliced[1]);
                return task;
            } else if (command.equals("event")) {
                if (input.substring(6).equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                String[] spliced = input.substring(6).split(" /at ");
                task = new Event(spliced[0], spliced[1]);
                return task;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the input is kinda faulty :-(");
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void finishATask(List<Task> tasks, String[] inputs) throws DukeException {
        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            tasks.set(index, tasks.get(index).finishTask());
            System.out.println("  " + tasks.get(index));
            System.out.println(LINE_AFTER_COMMAND + "\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of done cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of done exceed the tasks' list.");
        }
    }

    public static void deleteATask(List<Task> tasks, String[] inputs) throws DukeException {
        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            Task deleted = tasks.get(index);
            tasks.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + deleted);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(LINE_AFTER_COMMAND + "\n");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of delete cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of delete exceed the tasks' list.");
        }
    }
}
