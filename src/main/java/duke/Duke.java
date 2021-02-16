package duke;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


/**
 * The Duke class scans users inputs as command line arguments.
 * It prints the relevant information for the user requests to the CLI.
 */
//
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

    public String parseCommands(String wholeInput){
        String str = wholeInput;
        String[] parts = str.split(" ");
        String s = parts[0];
        String rest;
        switch (s){
            case "todo":
                rest = str.substring(4);
                return parser.parseTodoCommand(rest,taskList,ui,database);

            case "deadline":
                rest = str.substring(8);
                return parser.parseDeadlineCommand(rest,taskList,ui,database);

            case "reminder":
                rest = str.substring(8);
                if(!rest.isEmpty()){
                    return "Please do not type other chars after reminder";
                }
                return parser.parseReminderCommand(deadlineTasks,ui);

            case "event":
                rest = str.substring(5);
                return parser.parseEventCommand(rest, taskList, ui, database);

            case "list":
                rest = str.substring(4);
                if(!rest.isEmpty()){
                    return "Please do not type other chars after list";
                }
                return parser.parseListCommand(taskList, ui);

            case "bye":
                return parser.parseByeCommand(ui);

            case "done":
                rest = str.substring(4);

                return parser.parseDoneCommand(rest,taskList,ui,database);

            case "delete":
                rest = str.substring(6);
                return parser.parseDeleteCommand(rest, taskList, ui, database);

            case "find":
                rest = str.substring(4);
                return parser.parseFindCommand(rest, taskList,ui);

            default:
                return parser.parseDefault(ui);

        }
    }

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
