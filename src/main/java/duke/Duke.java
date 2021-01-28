package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static final String horizontalLine = "____________________________________________________________";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath, tasks);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        ui.greetings();
        boolean continueReading = true;
        while (continueReading) {
            String input = sc.nextLine();

            String[] inputArr = parser.getInputArr(input);
            String cmd = inputArr[0];

            switch(cmd) {
            case "bye":
                ui.exit();
                continueReading = false;
                break;
            case "list":
                ui.listAllTasks(tasks.list);
                break;
            case "done":
                int numDone = Integer.parseInt(inputArr[1]);
                Task task = tasks.list.get(numDone - 1);
                tasks.checkAsDone(task);
                ui.checkAsDoneMessage(task);
                break;
            case "delete":
                int numDelete = Integer.parseInt(inputArr[1]);
                Task deletedTask = tasks.deleteTask(numDelete);
                ui.deleteTaskMessage(tasks.list, deletedTask);
                break;
            case "todo":
                try {
                    parser.isEmptyDesc(inputArr);
                    String details = inputArr[1];
                    Task todo = new Todo(details);
                    tasks.addTask(todo);
                    ui.addTaskMessage(tasks.list, todo);
                } catch (DukeException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.\n" + horizontalLine);
                } finally {
                    break;
                }
            case "deadline":
                try {
                    parser.isEmptyDesc(inputArr);
                    String details = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                    String date = input.substring(input.indexOf("/") + 4);
                    Task deadline = new Deadline(details, date);
                    tasks.addTask(deadline);
                    ui.addTaskMessage(tasks.list, deadline);
                } catch (DukeException e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.\n" + horizontalLine);
                } finally {
                    break;
                }
            case "event":
                try {
                    parser.isEmptyDesc(inputArr);
                    String details = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                    String date = input.substring(input.indexOf("/") + 4);
                    Task event = new Event(details, date);
                    tasks.addTask(event);
                    ui.addTaskMessage(tasks.list, event);
                } catch (DukeException e) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.\n" + horizontalLine);
                } finally {
                    break;
                }
            default:
                try {
                    throw new DukeException();
                } catch (DukeException e) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n" + horizontalLine);
                }
            }
        }
        try {
            storage.saveTasks(tasks.list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }

}
