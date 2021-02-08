package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


/**
 *  The Parser class contains methods to handle user's input commands.
 */
public class Parser {

    /**
     *Parses the rest of the input string after todo and updates database
     *
     * @param input Scanner
     * @param taskList the current taskList
     * @param ui the current ui
     * @param database the current database
     * @throws DukeException if the user input a command with invalid format.
     */

    public static void parseTodoCommand(Scanner input, TaskList taskList, Ui ui, Database database){
        try {
            String s1 = input.nextLine();
            if (s1.equals("")) {
                throw new DukeException(" Enter a valid todo task");
            } else {
                char[] chars = s1.toCharArray();
                if(chars[1] == ' '){
                    throw new DukeException(" Enter valid todo task");
                } else {
                    try{
                        String desc = s1.substring(1);
                        Todo newTodo = new Todo(desc);
                        taskList.addTask(newTodo);
                        ui.showSuccessfulAddedMessage(taskList.getSize(), newTodo);
                        database.writeTaskToFile(taskList.getList());
                    } catch (Exception e){
                        System.out.println(" Enter a valid todo task");
                    }
                }
            }
        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     *Parses the rest of the input string after event and updates database
     *
     * @param input Scanner
     * @param taskList the current taskList
     * @param ui the current ui
     * @param database the current database
     * @throws DukeException if the user input a command with invalid format.
     */

    public static void parseEventCommand(Scanner input, TaskList taskList, Ui ui, Database database){
        try{
            String s1 = input.nextLine();
            if(s1.equals("")){
                throw new DukeException(" Enter a valid event task.");
            } else {
                try {
                    String wholeString = s1.substring(1);
                    String[] parts = wholeString.split(" /at ");
                    String eventDesc = parts[0];
                    if(parts.length == 1){
                        throw new DukeException(" Please adhere to convention:\n(event event_name /at event_details)");
                    } else {
                        String eventDetails = parts[1];
                        Event newEvent = new Event(eventDesc, eventDetails);
                        taskList.addTask(newEvent);
                        ui.showSuccessfulAddedMessage(taskList.getSize(), newEvent);
                        database.writeTaskToFile(taskList.getList());
                    }
                } catch(DukeException e) {
                    ui.showErrorMessage(e.getMessage());
                }
            }

        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }

    }

    /**
     *Parses the rest of the input string after deadline and updates database
     *
     * @param input Scanner
     * @param taskList the current taskList
     * @param ui the current ui
     * @param database the current database
     * @throws DukeException if the user input a command with invalid format.
     */

    public static void parseDeadlineCommand(Scanner input, TaskList taskList, Ui ui, Database database){
        try{
            String s1 = input.nextLine();
            if(s1.equals("")){
                throw new DukeException(" Enter valid deadline task.");
            } else {
                try {
                    String wholeString = s1.substring(1);
                    String[] parts = wholeString.split(" /by ");
                    String deadlineDesc = parts[0];
                    if(parts.length == 1){
                        throw new DukeException(" Please adhere to convention:\n(deadline task_name /by deadline date(YYYY-MM-DD))");
                    } else {
                        String dl = parts[1];
                        try{
                            LocalDate deadline = LocalDate.parse(dl);
                            Deadline newDeadline = new Deadline(deadlineDesc, deadline);
                            taskList.addTask(newDeadline);
                            ui.showSuccessfulAddedMessage(taskList.getSize(), newDeadline);
                            database.writeTaskToFile(taskList.getList());
                        } catch (DateTimeParseException e){
                            ui.showErrorMessage(" Please enter date as follows\n YYYY-MM-DD");
                        }
                    }
                } catch(DukeException e) {
                    ui.showErrorMessage(e.getMessage());
                }
            }
        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }

    }

    /**
     *Parses the rest of the input string after done and updates database
     *
     * @param input Scanner
     * @param taskList the current taskList
     * @param ui the current ui
     * @param database the current database
     * @throws DukeException if the user input a command with invalid format.
     */

    public static void parseDoneCommand(Scanner input, TaskList taskList, Ui ui, Database database){
        try{
            String s1 = input.nextLine();
            if(s1.equals("")){
                throw new DukeException(" Please specify what task is done");
            } else {
                String parts[] = s1.split(" ");
                if(parts.length> 2){
                    throw new DukeException(" Please insert valid index to mark as done");
                } else {
                    try {
                        String indexString = parts[1];
                        int index =Integer.parseInt(indexString);
                        Task t = taskList.getList().get(index - 1);
                        t.markAsDone();
                        ui.markTaskAsDone(t);
                        database.writeTaskToFile(taskList.getList());
                    } catch (Exception e){
                        ui.showErrorMessage(" Please enter a valid index");
                    }
                }
            }

        } catch(DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     *Parses the rest of the input string after delete and updates database
     *
     * @param input Scanner
     * @param taskList the current taskList
     * @param ui the current ui
     * @param database the current database
     * @throws DukeException if the user input a command with invalid format.
     */

    public static void parseDeleteCommand(Scanner input, TaskList taskList, Ui ui, Database database){
        try{
            String s1 = input.nextLine();
            if(s1.equals("")){
                throw new DukeException(" Please specify which task to delete");
            } else {
                String parts[] = s1.split(" ");
                if(parts.length> 2){
                    throw new DukeException(" Please insert valid index to delete");
                } else {
                    try {
                        String indexString = parts[1];
                        int index =Integer.parseInt(indexString);
                        Task t = taskList.getList().get(index-1);
                        taskList.removeTask(index-1);
                        ui.showTaskAsDeleted(t);
                        database.writeTaskToFile(taskList.getList());

                    } catch (Exception e){
                        ui.showErrorMessage(" Please enter a valid index");
                    }
                }
            }

        } catch(DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     *Parses the rest of the input string after List and calls ui to print tasks in TaskList
     *
     * @param taskList the current taskList
     * @param ui the current ui
     */

    public static void parseListCommand(TaskList taskList, Ui ui){
        ui.showListContent(taskList);

    }

    /**
     *
     * Calls ui to print bye message
     * Empties buffer is System
     * @param ui the current ui
     */
    public static void parseByeCommand(Ui ui){
        ui.showByeMessage();
        System.exit(0);
    }

    /**
     *
     * Calls ui to print default message
     * @param input the scanner
     * @param ui the current ui
     */
    public static void parseDefault(Scanner input, Ui ui){
        input.nextLine();
        ui.showDefaultStatement();
    }

}
