import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I help you with today! :-)");

        Scanner scan = new Scanner(System.in);
        Validation validate = new Validation();
        DateValidation dateValidation = new DateValidation();

        Storage storage = new Storage("data/tasks.txt");
        storage.checkFileExistence();
        TaskList tasks = new TaskList(storage.loadTasks());

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
                        storage.addTask(newToDo);
                        System.out.println("Done! One new task:\n" + newToDo.toString() + "\nNow you have " +
                                tasks.getSize() + ((tasks.getSize() == 1) ? " task" : " tasks") +
                                " in the list");
                        break;
                    case "deadline":
                        try {
                            validate.checkForSchedule(command, findSlash);
                            String descriptionDeadline = command.substring(index + 1, findSlash - 1);
                            LocalDate date = dateValidation.handleDate(command.substring(findSlash + 4));
                            Deadline newDeadline = new Deadline(descriptionDeadline, date);
                            tasks.add(newDeadline);
                            storage.addTask(newDeadline);
                            System.out.println("Done! One new task:\n" + newDeadline.toString() +
                                    "\nNow you have " + tasks.getSize() + ((tasks.getSize() == 1) ? " task" : " tasks") +
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
                            storage.addTask(newEvent);
                            System.out.println("Done! One new task:\n" + newEvent.toString() +
                                    "\nNow you have " + tasks.getSize() + ((tasks.getSize() == 1) ? " task" : " tasks") +
                                    " in the list");
                            break;
                        } catch (DukeException e) {
                            System.out.println(e);
                            break;
                        }
                    case "done":
                        // todo: if number out of bounds, throw exception
                        try {
                            number = Integer.parseInt(command.substring(index + 1));
                            validate.checkValidRange(tasks.getSize(), number);
                            Task toMark = tasks.find(number - 1);
                            String response = storage.markTask(toMark);
                            System.out.println(response);
                            break;
                        } catch (DukeException e) {
                            System.out.println(e);
                            break;
                        }
                    case "delete":
                        try {
                            number = Integer.parseInt(command.substring(index + 1));
                            validate.checkValidRange(tasks.getSize(), number);
                            Task selected = tasks.find(number - 1);
                            tasks.delete(number - 1);
                            storage.deleteTask(selected);
                            System.out.println("Noted, I've removed this task:\n" + selected.toString() +
                                    "\nNow you have " + tasks.getSize() + ((tasks.getSize() == 1) ? " task" : " tasks") +
                                    " in the list");
                        } catch (DukeException e) {
                            System.out.println(e);
                            break;
                        }
                    }
                } else {
                    switch (command) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        tasks.list();
                        break;
                    }
                }
            } catch (DukeException | IOException e) {
                System.out.println(e);
            }
            if (command.equals("bye")) {
                break;
            }
        }
    }
}
