package duke;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;


/**
 * The Duke class scans users inputs as command line arguments.
 * It prints the relevant information for the user requests to the CLI.
 */

public class Duke {

    private Database database;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private ArrayList<Deadline> deadlineTasks;

    public Duke() throws FileNotFoundException {
        this.ui = new Ui();
        this.database = new Database(ui.FILE_PATH);
        this.parser = new Parser();
        this.taskList = readInput(database.readFile());
        this.deadlineTasks = readDeadlineTasks(database.readFile());
    }

    /**
     * launches the GUI
     * @param args
     */

    public static void main(String[] args) {
        Application.launch(Main.class, args);
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

    /**
     * Parses the users input and returns output for given input
     * @param wholeInput String representation of User input
     * @return String output for for the given command
     */

    public String parseCommands(String wholeInput){
        String str = wholeInput;
        String[] parts = str.split(" ");
        String s = parts[0];
        switch (s){
            case "todo":
                return parser.parseTodoCommand(str,taskList,ui,database);

            case "deadline":
                return parser.parseDeadlineCommand(str,taskList,ui,database, deadlineTasks);

            case "reminder":
                return parser.parseReminderCommand(str, deadlineTasks,ui);
            case "event":
                return parser.parseEventCommand(str, taskList, ui, database);

            case "list":
                return parser.parseListCommand(str, taskList, ui);

            case "bye":
                return parser.parseByeCommand(ui);

            case "done":
                return parser.parseDoneCommand(str,taskList,ui,database);

            case "delete":
                return parser.parseDeleteCommand(str, taskList, ui, database);

            case "find":
                return parser.parseFindCommand(str, taskList,ui);

            default:
                return parser.parseDefault(ui);

        }
    }

    /**
     * Reads list of string representation of tasks and returns only deadline tasks in a deadline List
     * @param strings
     * @return list of deadline tasks that is sorted in order of date
     */
    private static ArrayList<Deadline> readDeadlineTasks(ArrayList<String> strings){
        ArrayList<Deadline> arrayListOfDeadlines = new ArrayList<>();
        for(String str: strings){
            char identifier = str.charAt(1);
            if(identifier == 'D' ){
                String subString = str.substring(7);
                String[] inputs = subString.split("by: ");
                String name = inputs[0].substring(0, inputs[0].length()-2);
                String deadline = inputs[1].substring(0, inputs[1].length()-1);
                LocalDate deadline1 = LocalDate.parse(deadline);
                if(deadline1.isAfter(LocalDate.now())){
                    Deadline deadline2 = new Deadline(name, deadline1);
                    arrayListOfDeadlines.add(deadline2);
                }
            }
        }
        Collections.sort(arrayListOfDeadlines);
        return arrayListOfDeadlines;
    }

}
