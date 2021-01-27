

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Arrays;

public class Duke {
    private static TaskList tasks;
    private static Storage storage;
    private static Parser parser;
    private static Ui ui;

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
        ui = new Ui();
        String userInput = ui.nextInput();
        while (!"bye".equals(userInput)) {
            parser.processCommand(userInput);
            Duke.writeTaskList();
            userInput = ui.nextInput();
        }
        ui.close();
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

    public static void writeTaskList() {
        String userInput = tasks.joinToTxt();
        try {
            storage.writeNewFile(userInput);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}
