import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke{
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        // Loading file from hard disk
        Storage storage = new Storage("./src/main/data/Data.txt");
        try {
            ArrayList<String> oldData = storage.load();
            ArrayList<String> parsedData = Parser.arrangeForStart(oldData);
            taskManager.upload(parsedData);
        } catch (FileNotFoundException e) {
            System.out.println("Storage file not found!");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        //After loading file from hard disk
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "What can I do for you?");
        System.out.println("");
        //Taking in current session's tasks and other commands
        while (true) {
            String input = sc.nextLine();
            try {
                String[] checkedInput = Parser.check(input);
                String command = checkedInput[0];
                if (command.equals("bye")) {
                    break;
                } else {
                    taskManager.manage(checkedInput);
                    if(!command.equals("list")) {
                        //Update harddisk file after every change in tasklist
                        ArrayList<String> modifiedTaskList = taskManager.retrieveTasksforStorage();
                        storage.store(modifiedTaskList);
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("");
            } catch (IOException e) {
                System.out.println("Storage file not in the correct format!");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
