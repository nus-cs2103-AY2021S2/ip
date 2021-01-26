package duke;

import duke.exceptions.DukeException;
import duke.exceptions.FileIoException;
import duke.exceptions.IndexOutOfBoundException;
import duke.exceptions.UnknownInputException;
import duke.tasks.*;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) throws DukeException, IOException {

        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        Storage storage = new Storage("test/duke.txt");
        System.out.println(ui.printWelcomeText());

        ArrayList<Task> tasks = storage.getTasks();
        TaskList taskList = new TaskList(tasks);

        if (sc.hasNext()) {
            String command = sc.nextLine();

            while (!command.equals("bye")) {
                if (command.equals("list")) {
                    System.out.println(ui.printList(taskList));
                } else if (command.startsWith("done")) {
                    int taskNum;
                    try {
                        taskNum = Integer.valueOf(command.substring(5));
                    } catch (Exception e) {
                        throw new IndexOutOfBoundException();
                    }
                    Task curr = taskList.markCompleted(taskNum - 1);
                    System.out.println(ui.printCompletedMsg(curr));

                } else if (command.startsWith("delete")) {
                    int taskNum;
                    try {
                        taskNum = Integer.valueOf(command.substring(7));
                    } catch (Exception e) {
                        throw new IndexOutOfBoundException();
                    }
                    Task curr = taskList.delete(taskNum - 1);
                    System.out.println(ui.printDeleteMsg(curr, taskList.size()));

                } else if(command.startsWith("todo")){
                    if(command.length()< 5){
                        throw new DukeException(":( Description of ToDos cannot be empty! Please try again.");
                    }
                    Todo curr = new Todo(command.substring(5), false);
                    taskList.addTask(curr);
                    System.out.println(curr.toStorageString());
                    System.out.println(ui.printTask(curr, taskList.size()));

                } else if(command.startsWith("deadline")){
                    int cut = command.indexOf("/");
                    if(cut == -1 || command.length()<= cut +4 ){
                        throw new DukeException(":( Deadline timing not specified! Please try again.");
                    }
                    try {
                        command.substring(9, cut-1);
                    } catch (Exception e){
                        throw new DukeException(":( Task name not detected! Please try again.");
                    }
                    Deadline curr = new Deadline(command.substring(9, cut-1), false, command.substring(cut+4));
                    taskList.addTask(curr);
                    System.out.println(ui.printTask(curr, taskList.size()));

                } else if(command.startsWith("event")){
                    int cut = command.indexOf("/");
                    if(cut == -1 || command.length()<= cut +4 ){
                        throw new DukeException(":( Event timing not specified! Please try again.");
                    }
                    try {
                        command.substring(6, cut-1);
                    } catch (Exception e){
                        throw new DukeException(":( Task name not detected! Please try again.");
                    }
                    Event curr = new Event(command.substring(6, cut-1), false, command.substring(cut+4));
                    taskList.addTask(curr);
                    System.out.println(ui.printTask(curr, taskList.size()));

                } else {
                    throw new UnknownInputException();
                }
                storage.writeFile(taskList);
                command = sc.nextLine();

            }
            System.out.println(ui.printGoodbye());

        }

    }
}
