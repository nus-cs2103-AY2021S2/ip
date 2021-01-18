import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lineAfterCommand = "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println(lineAfterCommand + "\nHello! I'm  Duke");
        System.out.println("What can I do for you?\n" + lineAfterCommand + "\n");
        List<Task> tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")){
                break;
            }
            System.out.println(lineAfterCommand);
            String[] temp = command.split(" ");
            try {
                if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(String.valueOf(i + 1) + "." + tasks.get(i));
                    }
                    System.out.println(lineAfterCommand + "\n");
                } else if (temp[0].equals("done")) {
                    try {
                        tasks.set(Integer.parseInt(command.split(" ")[1]) - 1, tasks.get(Integer.parseInt(command.split(" ")[1]) - 1).finishTask());
                        System.out.println("  " + tasks.get(Integer.parseInt(command.split(" ")[1]) - 1));
                        System.out.println(lineAfterCommand + "\n");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The index of done cannot be empty.");
                    }
                }
                else if (temp[0].equals("delete")) {
                        try {
                            Task deleted = tasks.get(Integer.parseInt(command.split(" ")[1]) - 1);
                            tasks.remove(Integer.parseInt(command.split(" ")[1]) - 1);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + deleted);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println(lineAfterCommand + "\n");
                        }
                        catch(ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! The index of delete cannot be empty.");
                        }
                } else {
                    Task task;
                    if (temp[0].equals("todo")) {
                        try {
                            task = new Todo(command.substring(5));
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                    } else if (temp[0].equals("deadline")) {
                        try {
                            task = new Deadline(command.substring(9));
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                    } else if (temp[0].equals("event")) {
                        try {
                            task = new Event(command.substring(6));
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Got it. I've added this task:");
                    tasks.add(task);
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(lineAfterCommand + "\n");
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(lineAfterCommand + "\n");
            }
        }
        System.out.println(lineAfterCommand + "\nBye. Hope to see you again soon!\n" + lineAfterCommand + "\n");
    }
}
