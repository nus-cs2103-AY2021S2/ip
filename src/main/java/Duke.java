import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import exceptions.DukeException;
import exceptions.MissingInputException;
import exceptions.UnknownInputException;

import tasks.ToDoTask;
import tasks.Task;
import tasks.EventTask;
import tasks.DeadlineTask;

public class Duke {

    public static void printDivider() {
        String divider = "    ___________________________________________";
        System.out.println(divider);
    }

    public static void welcome() {
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
    }

    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        printDivider();
        welcome();
        printDivider();

        Scanner sc = new Scanner(System.in);
        File file = new File("./data/duke.txt");

        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, false);
            Scanner readInput = new Scanner(file);

            boolean carryOn = true;
            TaskHandler handler = new TaskHandler();

            while (carryOn) {
                String action = sc.nextLine();
                String[] arr = action.split(" ");
                    switch(arr[0]) {
                    case "todo":
                        if (arr.length <= 1)
                            throw new MissingInputException(arr[0]);
                        printDivider();

                        handler.addPrint();
                        ToDoTask todo = handler.handleToDoTask(action);
                        System.out.println(todo);
                        handler.countTasks();

                        printDivider();
                        break;
                    case "deadline":
                        if (arr.length <= 1)
                            throw new MissingInputException(arr[0]);
                        printDivider();

                        handler.addPrint();
                        DeadlineTask deadlineTask = handler.handleDeadlineTask(action);
                        System.out.println(deadlineTask);
                        handler.countTasks();

                        printDivider();
                        break;
                    case "event":
                        if (arr.length <= 1)
                            throw new MissingInputException(arr[0]);
                        printDivider();

                        handler.addPrint();
                        EventTask eventTask  = handler.handleEventTask(action);
                        System.out.println(eventTask);
                        handler.countTasks();

                        printDivider();
                        break;
                    case "list":
                        printDivider();
                        handler.printStored();
                        printDivider();
                        break;
                    case "done":
                        int number = Integer.valueOf(arr[1]);
                        printDivider();
                        Task completed = handler.handleDone(number);
                        System.out.println(completed);
                        printDivider();
                        break;
                    case "bye":
                        carryOn = false;
                        break;
                    case "delete":
                        int index = Integer.valueOf(arr[1]);
                        printDivider();

                        Task task = handler.handleDelete(index);
                        System.out.println(task);
                        handler.countTasks();

                        printDivider();
                        break;
                    default:
                        throw new UnknownInputException();
                    }
            }

            printDivider();
            bye();
            printDivider();

            writer.write(handler.getList());
            writer.close();
            readInput.close();

        } catch (DukeException e) {
            printDivider();
            System.out.println(e.getMessage());
            printDivider();
        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }

        sc.close();
    }
}
