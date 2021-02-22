package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


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
     * @return String response from Ui
     */

    public static String parseTodoCommand(String input, TaskList taskList, Ui ui, Database database){
        try {
            String inputCopy = input;
            if (inputCopy.equals("")) {
                throw new DukeException(" Enter a valid tododwn task");
            } else {
                char[] chars = inputCopy.toCharArray();
                String ws = inputCopy.substring(1);
                String[] parts = ws.split(" ");
                try{
                    String desc = inputCopy.substring(1);
                    Todo newTodo = new Todo(desc);
                    taskList.addTask(newTodo);
                    database.writeTaskToFile(taskList.getList());
                    return ui.showSuccessfulAddedMessage(taskList.getSize(), newTodo);
                } catch (Exception e){
                    return " Enter a valid todo task";
                }
            }
        } catch (DukeException e){
            return ui.showErrorMessage(e.getMessage());
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
     * @return String response from Ui
     */

    public static String parseEventCommand(String input, TaskList taskList, Ui ui, Database database){
        try{
            String inputCopy = input;
            if(inputCopy.equals("")){
                throw new DukeException(" Enter a valid event task.");
            } else {
                try {
                    String wholeString = inputCopy.substring(1);
                    String[] parts = wholeString.split(" /at ");
                    String eventDesc = parts[0];
                    if(parts.length == 1){
                        throw new DukeException(" Please adhere to convention:\nevent event_name /at event_details");
                    } else {
                        String eventDetails = parts[1];
                        Event newEvent = new Event(eventDesc, eventDetails);
                        taskList.addTask(newEvent);
                        database.writeTaskToFile(taskList.getList());
                        return ui.showSuccessfulAddedMessage(taskList.getSize(), newEvent);
                    }
                } catch(DukeException e) {
                    return ui.showErrorMessage(e.getMessage());
                }
            }

        } catch (DukeException e){
            return ui.showErrorMessage(e.getMessage());
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
     * @return String response from Ui
     */

    public static String parseDeadlineCommand(String input, TaskList taskList, Ui ui, Database database, ArrayList<Deadline> deadlines){
        try{
            String inputCopy = input;
            if(inputCopy.equals("")){
                throw new DukeException(" Enter valid deadline task after typing deadline.");
            } else {
                try {
                    String wholeString = inputCopy.substring(1);
                    String[] parts = wholeString.split(" /by ");
                    String deadlineDesc = parts[0];
                    if(parts.length == 1){
                        throw new DukeException(" Please adhere to convention:\ndeadline task_name /by deadline YYYY-MM-DD");
                    } else {
                        String dl = parts[1];
                        try{
                            LocalDate deadline = LocalDate.parse(dl);
                            Deadline newDeadline = new Deadline(deadlineDesc, deadline);
                            taskList.addTask(newDeadline);
                            if(deadline.isAfter(LocalDate.now())){
                                deadlines.add(newDeadline);
                            }
                            database.writeTaskToFile(taskList.getList());
                            return ui.showSuccessfulAddedMessage(taskList.getSize(), newDeadline);

                        } catch (DateTimeParseException e){
                            return ui.showErrorMessage(" Please enter date as follows\n YYYY-MM-DD");
                        }
                    }
                } catch(DukeException e) {
                    return ui.showErrorMessage(e.getMessage());
                }
            }
        } catch (DukeException e){
            return ui.showErrorMessage(e.getMessage());
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
     * @return String response from Ui
     */

    public static String parseDoneCommand(String input, TaskList taskList, Ui ui, Database database){
        try{
            String inputCopy = input;
            if(inputCopy.equals("")){
                throw new DukeException(" Please specify what task is done");
            } else {
                String parts[] = inputCopy.split(" ");
                if(parts.length> 2){
                    throw new DukeException(" Please insert valid index to mark as done");
                } else {
                    try {
                        String indexString = parts[1];
                        int index =Integer.parseInt(indexString);
                        assert index>0 : "index should be more than zero";
                        Task t = taskList.getList().get(index - 1);
                        t.markAsDone();
                        database.writeTaskToFile(taskList.getList());
                        return ui.markTaskAsDone(t);
                    } catch (Exception e){
                        return ui.showErrorMessage(" Please enter a valid index");
                    }
                }
            }

        } catch(DukeException e){
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     *Parses the rest of the input string after find and finds tasks in
     *TaskList that corresponds to the input keyword
     * @param input Scanner
     * @param taskList the current taskList
     * @param ui the current ui
     * @throws DukeException if the user input a command with invalid format.
     * @return String response from Ui
     */
    public static String parseFindCommand(String input, TaskList taskList, Ui ui){
        try{
            String inputCopy = input;
            if(inputCopy.equals("")){
                throw new DukeException("Please specify a keyword you are trying to find");
            } else {
                String parts[] = inputCopy.split(" ");
                if(parts.length> 2){
                    throw new DukeException(" Please insert only 1 keyword");
                } else {
                    try{
                        String keyWord = parts[1];
                        ArrayList<String> matchingWithKeyword = new ArrayList<>();
                        boolean hasMatch = false;
                        for(int i =0; i< taskList.getSize(); i++){
                            if(taskList.getList().get(i).hasKeyWord(keyWord)){
                                matchingWithKeyword.add(taskList.getList().get(i).toString());
                                hasMatch = true;
                            }
                        }
                        if(!hasMatch){
                            throw new DukeException("Sorry. None of your tasks contain this keyword");
                        } else {
                            //
                            return ui.showKeyWordMessage(matchingWithKeyword);
                        }
                    } catch (ArrayIndexOutOfBoundsException e){
                        return ui.showErrorMessage("Please specify a keyword you are trying to find");
                    }
                }
            }
        } catch (DukeException e){
            return ui.showErrorMessage(e.getMessage());
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
     * @return String response from Ui
     */
    public static String parseDeleteCommand(String input, TaskList taskList, Ui ui, Database database){
        try{
            String inputCopy = input;
            if(inputCopy.equals("")){
                throw new DukeException(" Please specify which task to delete");
            } else {
                String parts[] = inputCopy.split(" ");
                if(parts.length> 2){
                    throw new DukeException(" Please insert valid index to delete");
                } else {
                    try {
                        String indexString = parts[1];
                        int index =Integer.parseInt(indexString);
                        assert index>0: "index should be more than 0";
                        Task t = taskList.getList().get(index-1);
                        taskList.removeTask(index-1);
                        database.writeTaskToFile(taskList.getList());
                        return ui.showTaskAsDeleted(t);

                    } catch (Exception e){
                        return ui.showErrorMessage(" Please enter a valid index");
                    }
                }
            }

        } catch(DukeException e){
            return ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     *Parses the rest of the input string after List and calls ui to print tasks in TaskList
     *
     * @param taskList the current taskList
     * @param ui the current ui
     * @return String response from Ui
     */

    public static String parseListCommand(TaskList taskList, Ui ui){
        return ui.showListContent(taskList);
    }

    /**
     * Parses the deadline tasks to Ui to get String representation of the response.
     * @param listOfUpComingsTasks
     * @param ui
     * @return String response from Ui.
     */
    public static String parseReminderCommand(ArrayList<Deadline> listOfUpComingsTasks, Ui ui){
        return ui.showReminderContent(listOfUpComingsTasks);
    }

    /**
     *
     * Calls ui to print bye message
     * Empties buffer is System
     * @param ui the current ui
     * @return String response from Ui
     */
    public static String parseByeCommand(Ui ui){
        return ui.showByeMessage();
    }

    /**
     *
     * Calls ui to print default message
     * @param ui the current ui
     * @return String response from Ui
     */
    public static String parseDefault(Ui ui){

        return ui.showDefaultStatement();
    }

}
