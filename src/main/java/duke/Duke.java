package duke;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Duke class scans users inputs as command line arguments.
 * It prints the relevant information for the user requests to the CLI.
 */

public class Duke {

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        Ui ui = new Ui();
        Parser parser = new Parser();
        Database database = new Database(ui.FILE_PATH);
        ArrayList<String> listOfTasks;
        ArrayList<Task> myList;
        TaskList taskList = new TaskList();
        try {
            listOfTasks = database.readFile();
            taskList = readInput(listOfTasks);
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("No File Detected");
        }
        ui.initGreeting();
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()) {
            String s = input.next();
            switch (s){
                case "todo":

                    parser.parseTodoCommand(input,taskList,ui,database);
                    break;

                case "deadline":

                    parser.parseDeadlineCommand(input,taskList,ui,database);
                    break;

                case "event":

                    parser.parseEventCommand(input, taskList, ui, database);
                    break;

                case "list":

                    parser.parseListCommand(taskList, ui);
                    break;

                case "bye":
                    parser.parseByeCommand(ui);
                    break;
                case "done":

                    parser.parseDoneCommand(input,taskList,ui,database);
                    break;

                case "delete":

                    parser.parseDeleteCommand(input, taskList, ui, database);
                    break;

                case "find":
                    parser.parseFindCommand(input, taskList,ui);
                    break;

                default:
                    parser.parseDefault(input,ui);
                    break;
            }
        }
    }

    /**
     * Reads string as input from database and initialize TaskList with tasks
     *
     * @param strings from Database.
     * @return tasklist as saved in database.
     */

    private static TaskList readInput(ArrayList<String> strings) {
        TaskList tasks = new TaskList();
        for(String str: strings) {
            char identifier = str.charAt(1);
            switch(identifier) {
                case 'D':
                    String subString = str.substring(7);
                    String[] inputs = subString.split("by: ");
                    String name = inputs[0].substring(0, inputs[0].length()-2);
                    String deadline = inputs[1].substring(0, inputs[1].length()-1);
                    LocalDate deadline1 = LocalDate.parse(deadline);
                    Deadline deadline2 = new Deadline(name, deadline1);
                    tasks.addTask(deadline2);
                    break;
                case 'T':
                    String subString1 = str.substring(7);
                    Todo todo1 = new Todo(subString1);
                    tasks.addTask(todo1);
                    break;
                case 'E':
                    String subString2 = str.substring(7);
                    String[] inputs1 = subString2.split("at: ");
                    String desc = inputs1[0].substring(0, inputs1[0].length()-2);
                    String at = inputs1[1].substring(0, inputs1[1].length()-1);
                    Event event1 = new Event(desc, at);
                    tasks.addTask(event1);

                    break;
            }
        }
        return tasks;
    }


}
