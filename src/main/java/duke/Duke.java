package duke;

import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Deadline;
import duke.Event;
import duke.Duke;
import duke.Storage;
import duke.Ui;
import duke.Parser;
import duke.DukeException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.getTaskList());
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        final String exitCommand = "bye";
        final String listCommand = "list";
        final String doneCommand = "done";
        final String deleteCommand = "delete";
        final String addTodoCommand = "todo";
        final String addDeadlineCommand = "deadline";
        final String addEventCommand = "event";
        final String findCommand = "find";
        ui.printWelcomeGreeting();
        Scanner sc = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye) {
            //task taken in
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");
            Task newTask;
            ui.printHorizontalRule();
            try {
                switch (commandArr[0]) {
                case exitCommand:
                    ui.printExitMessage();
                    sc.close();
                    //add data back into the file
                    storage.writeData(tasks.getTaskList());
                    isBye = true;
                    break;
                case listCommand:
                    ui.printTasks(tasks);
                    break;
                case doneCommand:
                    int taskNumber = Integer.parseInt(commandArr[1]);
                    ui.printDoneTask(tasks, taskNumber);
                    break;
                case addTodoCommand:
                    if (commandArr.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    else {
                        newTask = parser.parseAddTodo(command);
                        ui.printAddedTask(tasks, newTask);
                    }
                    break;
                case addDeadlineCommand:
                    newTask = parser.parseAddDeadline(command);
                    ui.printAddedTask(tasks, newTask);
                    break;
                case addEventCommand:
                    newTask = parser.parseAddEvent(command);
                    ui.printAddedTask(tasks, newTask);
                    break;
                case deleteCommand:
                    int taskNumToBeDeleted = parser.parseDeleteCommand(command);
                    ui.printDeletedTask(tasks, taskNumToBeDeleted);
                    break;
                case findCommand:
                    TaskList tasksFound = parser.parseFindCommand(command, tasks);
                    ui.printFoundTasks(tasksFound);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}
