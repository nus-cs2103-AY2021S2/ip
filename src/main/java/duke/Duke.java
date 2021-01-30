package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import java.nio.file.Paths;

/**
 * Represents the highest level code responsible for Duke's operations.
 */
public class Duke {


    public static void main(String[] args) {

        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage(Paths.get("data", "duke.txt"), Paths.get("data"));

        taskList = new TaskList(storage.readFromFile());

        Ui.showInitUi();

        while (sc.hasNextLine()) {

            try {
                DukeCommand dukeCommand = Parser.parseCommand(sc.nextLine());
                if (dukeCommand.getCommand() == Command.BYE) {
                    Ui.showExitUi();
                    sc.close();
                    return;
                } else if (dukeCommand.getCommand() == Command.DELETE) {
                    Integer index = Integer.parseInt(dukeCommand.getDetails()) - 1;

                    if (index >= taskList.getTasks().size()) {
                        throw new DukeException("No such task in the list");
                    }

                    Task removedTask = taskList.delete(Integer.parseInt(dukeCommand.getDetails()) - 1);
                    storage.writeToFile(taskList);
                    Ui.showSuccessfulDelete(taskList.getTasks().size(), removedTask);


                } else if (dukeCommand.getCommand() == Command.LIST) {
                    Ui.showList(taskList);

                } else if (dukeCommand.getCommand() == Command.DONE) {
                    Integer index = Integer.parseInt(dukeCommand.getDetails()) - 1;

                    if (index >= taskList.getTasks().size()) {
                        throw new DukeException("No such task in the list");
                    }

                    taskList.markAsDone(index);
                    storage.writeToFile(taskList);
                    Ui.showSuccessfulDone(taskList.get(index));


                } else if (dukeCommand.getCommand() == Command.INVALID) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");

                } else if (dukeCommand.getCommand() == Command.FIND) {
                    if (dukeCommand.getDetails().length() == 0) {
                        throw new DukeException("☹ OOPS!!! Search keyword cannot be empty");
                    }

                    ListIterator<Task> taskIter = taskList.getTasks().listIterator();
                    String keyword = dukeCommand.getDetails();
                    TaskList matchedTasks = new TaskList();

                    while (taskIter.hasNext()) {
                        Task curr = taskIter.next();

                        if (curr.getDescription().contains(keyword)) {
                            matchedTasks.add(curr);
                        }
                    }

                    Ui.showMatchedTasks(matchedTasks);


                } else {
                    Task newTask = Parser.parseRemainder(dukeCommand.getCommand(), dukeCommand.getDetails());

                    if (newTask == null) {
                        throw new DukeException("parseRemainder() returned null instead of a new task to add...");
                    }

                    taskList.add(newTask);
                    storage.writeToFile(taskList);
                    Ui.showSuccessfulAdd(taskList.getTasks().size(), newTask);
                }



            } catch(DukeException exp) {
                Ui.showDukeException(exp);

            } catch(Exception err) {
                err.printStackTrace();
            }

        }

    }
}
