package duke;

import java.util.Scanner;

/**
 * Handles individual inputs and interprets the commands from the user.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Only constructor for parser. Catches cases where the user inputs an invalid command.
     *
     * @param duke
     * @param taskList
     * @param ui
     * @param storage
     */
    protected void start(Duke duke, TaskList taskList, Ui ui, Storage storage) {
        Scanner sc = new Scanner(System.in);
        String scannedLine = sc.nextLine();
        while (duke.isOn) {
            try {
                handleCommand(duke, scannedLine, taskList, ui, storage);
            } catch (Exception e) {
                System.out.format("%s\n☹ %s\n%s", Duke.line, e.getMessage(), Duke.line);
            } finally {
                scannedLine = sc.nextLine();
            }
        }
    }

    /**
     * Listens to input. Parses the input into the command, body and date/time.
     * Activates taskList to manage the task.
     * Activates UI to respond to the user.
     * Hanldes commands:
     * "list", "save", "bye", "delete", "done", "todo" , "deadline", "event".
     *
     * @param duke
     * @param currLine the current input from the user
     * @param taskList
     * @param ui
     * @param storage
     * @throws Exception when an invalid command is made. i.e. the first word in the input is
     *                   invalid
     */
    protected void handleCommand(Duke duke, String currLine, TaskList taskList, Ui ui, Storage storage) throws Exception {
        // basic commands
        currLine = currLine.toLowerCase();
        String[] parsedLine = currLine.split(" ");
        if (currLine.startsWith("list")) {
            ui.printListTasks(taskList);
        } else if (currLine.startsWith("find")) {
            ui.find(taskList, currLine);
        } else if (currLine.startsWith("save")) {
            storage.save(ui, taskList);
            System.out.println("Your information has been saved!");
        } else if (currLine.startsWith("bye")) {
            taskList.bye(duke);
            ui.bye();
        } else if (currLine.startsWith("delete")) {
            Task task = taskList.delete(Integer.parseInt(parsedLine[1]));
            ui.delete(task, taskList.list.size());
        } else if (currLine.startsWith("done")) {
            Task task = taskList.doTask(Integer.parseInt(parsedLine[1]));
            ui.doTask(task);
        } else if (currLine.startsWith("todo")) {
            Task task = taskList.addTask(new Todo(currLine));
            ui.addTask(task, taskList.list.size());
        } else if (currLine.startsWith("deadline")) {
            Task task = taskList.addTask(new Deadline(currLine));
            ui.addTask(task, taskList.list.size());
        } else if (currLine.startsWith("event")) {
            Task task = taskList.addTask(new Event(currLine));
            ui.addTask(task, taskList.list.size());
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
