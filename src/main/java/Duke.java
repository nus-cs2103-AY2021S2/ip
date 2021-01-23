import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Duke is a task manager.
 * 
 * <p>Currently supports these functionalities:
 * 
 * <p>bye 
 *   - Close Duke
 * <p>list
 *   - List out all task
 * <p>done [number]
 *   - Mark selected task as done
 * <p>todo [description]
 *   - Add a todo task
 * <p>deadline [description] /by [due date]
 *   - Add a deadline task with a due date
 * <p>event [description] /at [date]
 *   - Add a event task with a date
 * <p>delete [number]
 *   - Delete a task
 */
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.start();

    }

    private List<Task> checkList = new ArrayList<>();
    private static String BORDER_LINE = "____________________________________________________________";

    /**
     * Starts the Duke bot
     */
    public void start() {
        Scanner sc = new Scanner(System.in);

        echo(List.of("Hello! I'm Duke","What can I do for you?","Enter \"load\" to restore previously saved checklist."));

        for (;;) {
            String command = sc.next();
            String input = sc.nextLine().strip();

            try {
                switch(command) { 
                case "bye": 
                    echo("Bye. Hope to see you again soon!");  
                    return; 
                case "list": 
                    displayList(checkList);
                    break; 
                case "done":
                    completeTask(input);
                    break;
                case "todo":
                    checkList.add(Todo.createTodo(input));
                    taskAdded();
                    break;
                case "deadline":
                    checkList.add(Deadline.createDeadline(input));
                    taskAdded();
                    break;
                case "event":
                    checkList.add(Event.createEvent(input));
                    taskAdded();
                    break;
                case "delete":
                    deleteTask(input);
                    break;
                case "save":
                    saveCheckList();
                    break;
                case "load":
                    checkList = loadCheckList();
                    break;
                default: 
                    echo("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } 
            } catch (DukeException e) {
                echo(e.getMessage());
            }
        }
    }

    private void echo(String s) {
        System.out.println("\t" + BORDER_LINE);
        System.out.println("\t  " + s);
        System.out.println("\t" + BORDER_LINE + "\n");
    }

    private void echo(List<String> lst) {
        System.out.println("\t" + BORDER_LINE);
        for (String s : lst) {
            System.out.println("\t  " + s);
        }
        System.out.println("\t" + BORDER_LINE + "\n");
    }

    private void displayList(List<Task> lst) {
        System.out.println("\t" + BORDER_LINE);
        System.out.println("\tHere are the tasks in your list:");

        int num = 1;
        for (Task t : lst) {
            System.out.printf("\t  %d. %s\n", num++, t);
        }
        System.out.println("\t" + BORDER_LINE + "\n");
    }

    private void completeTask(String num) throws DukeException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new DukeException("This is not a valid number!");
        }
        if (taskNum > checkList.size()) {
            throw new DukeException("This number is too big!");
        }

        Task t = checkList.get(taskNum-1);
        t.completed();
        
        echo(List.of("Nice! I've marked this task as done:", t.toString()));
    }

    private void taskAdded() {
        echo(List.of("Got it. I've added this task:",
                checkList.get(checkList.size()-1).toString(),
                "Now you have " + checkList.size() + " tasks in the list."));
    }

    private void deleteTask(String num) throws DukeException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new DukeException("This is not a valid number!");
        }
        if (taskNum > checkList.size()) {
            throw new DukeException("This number is too big!");
        }
        
        Task t = checkList.get(taskNum-1);
        checkList.remove(taskNum-1);
        
        echo(List.of("Noted. I've removed this task:",
                t.toString(),
                "Now you have " + checkList.size() + " tasks in the list."));
    }

    private List<Task> loadCheckList() {
        
        List<Task> lst = new ArrayList<>();
        File f = new File("data/dukeData.txt");
        Scanner sc;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            echo(List.of("No save found!", "Creating new list.."));
            return lst;
        }

        while (sc.hasNextLine()) {  
            String[] s = sc.nextLine().split(";");
            switch (s[0]) {
            case "T":
                lst.add(Todo.importData(s));
                break;
            case "D":
                lst.add(Deadline.importData(s));
                break;
            case "E":
                lst.add(Event.importData(s));
                break;
            default:
                echo(List.of("File corrupted and unable to load", "Creating new list.."));
                return new ArrayList<>();
            }
        }
        echo("Checklist loaded successfully!");
        return lst;

    } 
    
    private void saveCheckList() {
        String path = "data/dukeData.txt";

        File f = new File(path);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdir();
        }

        FileWriter fw;
        try {
            fw = new FileWriter(path);
            for (Task t : checkList) {
                fw.write(String.join(";", t.exportData()) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            echo("Please delete \"data/dukeData.txt\" file and run this command again!");
            return;
        }

        echo("Checklist saved!");
    }
}
