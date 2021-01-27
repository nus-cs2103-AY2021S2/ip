import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Arrays;

public class Duke {
    private static TaskList tasks;
    private static Storage storage;
    private static Parser parser;

    public static void main(String[] args) {
        tasks = new TaskList();
        storage = new Storage();
        parser = new Parser(tasks);
        try {
            storage.loadData();
            parser.printList();
        } catch (FileNotFoundException e) {
            System.out.println("File not found :(");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Duke: Hello I'm Duke, what can I do for you?");
        System.out.println("-----------------------------------------------------");
        System.out.println("Enter an input ('bye' to quit): ");
        String userInput = sc.nextLine();
        System.out.println("User Input: " + userInput);
        while (!"bye".equals(userInput)) {
            parser.processCommand(userInput);
            Duke.writeTaskList();
            System.out.println("Enter an input ('bye' to quit): ");
            userInput = sc.nextLine();
            System.out.println("User Input: " + userInput);
        }
        System.out.println("Duke: Bye, hope to see you again! :)");
        System.out.println("-----------------------------------------------------");
    }

    public static void readTaskList(String taskData) {
        String[] splits = taskData.split(" \\| ");
        if (splits[0].equals("T")) {
            Todo addedTask = new Todo(Arrays.asList(splits).get(2), Arrays.asList(splits).get(1).equals("1"));
            tasks.addTask(addedTask);
        } else if (splits[0].equals("D")) {
            try {
                Deadline addedTask = new Deadline(Arrays.asList(splits).get(2), Arrays.asList(splits).get(1).equals("1"), Arrays.asList(splits).get(3));
                tasks.addTask(addedTask);
            } catch (DukeException e) {
                System.out.println(e);
            }
        } else if (splits[0].equals("E")) {
            try {
                Event addedTask = new Event(Arrays.asList(splits).get(2), Arrays.asList(splits).get(1).equals("1"), Arrays.asList(splits).get(3));
                tasks.addTask(addedTask);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    //PROBABLY IN UI
    public static void writeTaskList() {
        String userInput = tasks.joinToTxt();
        try {
            storage.writeNewFile(userInput);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}
