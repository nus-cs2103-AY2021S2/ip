import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I help you with today! :-)");
        List<Task> tasks = new ArrayList<Task>();
        Scanner scan = new Scanner(System.in);
        Validation validate = new Validation();
        int number;

        while (true) {
            String command = scan.nextLine();
            try {
                validate.checkValidCommand(command);
                int index = command.indexOf(' ');
                int findSlash = command.indexOf('/');
                if (index > -1) {
                    String type = command.substring(0, index);
                    switch (type) {
                    case "todo":
                        String descriptionTask = command.substring(index + 1);
                        ToDo newToDo = new ToDo(descriptionTask);
                        tasks.add(newToDo);
                        System.out.println("Done! One new task:\n" + newToDo.toString() + "\nNow you have " +
                                tasks.size() + ((tasks.size() == 1) ? " task" : " tasks") +
                                " in the list");
                        break;
                    case "deadline":
                        try {
                            validate.checkForSchedule(command, findSlash);
                            String descriptionDeadline = command.substring(index + 1, findSlash - 1);
                            String date = command.substring(findSlash + 4);
                            Deadline newDeadline = new Deadline(descriptionDeadline, date);
                            tasks.add(newDeadline);
                            System.out.println("Done! One new task:\n" + newDeadline.toString() +
                                    "\nNow you have " + tasks.size() + ((tasks.size() == 1) ? " task" : " tasks") +
                                    " in the list");
                            break;
                        } catch (DukeException e) {
                            System.out.println(e);
                            break;
                        }
                    case "event":
                        try {
                            validate.checkForSchedule(command, findSlash);
                            String descriptionEvent = command.substring(index + 1, findSlash - 1);
                            String time = command.substring(findSlash + 4);
                            Event newEvent = new Event(descriptionEvent, time);
                            tasks.add(newEvent);
                            System.out.println("Done! One new task:\n" + newEvent.toString() +
                                    "\nNow you have " + tasks.size() + ((tasks.size() == 1) ? " task" : " tasks") +
                                    " in the list");
                            break;
                        } catch (DukeException e) {
                            System.out.println(e);
                            break;
                        }
                    case "done":
                        number = Integer.parseInt(command.substring(index + 1));
                        Task toMark = tasks.get(number - 1);
                        String response = toMark.markAsDone();
                        System.out.println(response);
                        break;
                    case "delete":
                        number = Integer.parseInt(command.substring(index + 1));
                        Task selected = tasks.get(number - 1);
                        tasks.remove(number - 1);
                        System.out.println("Noted, I've removed this task:\n" + selected.toString() +
                                "\nNow you have " + tasks.size() + ((tasks.size() == 1) ? " task" : " tasks") +
                                        " in the list");
                    }
                } else {
                    switch (command) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i).toString());
                        }
                        break;
                    }
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
            if (command.equals("bye")) {
                break;
            }
        }
    }
}
