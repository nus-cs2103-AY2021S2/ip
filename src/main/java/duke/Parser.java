package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Parser {
    private boolean isEnd;
    
    /**
    * Constructor for the Parser class
    */
    public Parser() {
        isEnd = false;
    }
    
    /**
    * Returns the End object which indicates whether the chatbot has been terminated by the user.
    * @return boolean object. True - chatbot ended, False - chatbot still running
    */
    public boolean checkEnd() {
        return isEnd;
    }
    
    /**
    * Returns a String object which contains the output from processing user's input. This method will process
    * the user's input and perform functions accordingly to the TaskList object supplied in the argument <p>
    * The in String argument should be a valid command. The following are commands acceptable by the process function:<br/>
    * <ul>
    * <li>list - Displays current list items</li>
    * <li>bye - Exits Duke program</li>
    * <li>done <int:number> - Marks item number specified (1-based) as done</li>
    * <li>todo <String:name> - Creates an item type of "todo" inside TaskList</li>
    * <li>deadline <String:name> /by <String:date> - Creates an item type of "deadline" inside TaskList. The date should be specified in an YYYY-MM-DD format</li>
    * <li>event <String:name> /at <String:date> - Creates an item type of "event" inside TaskList. The date should be specified in an YYYY-MM-DD format</li>
    * <li>delete <int:number> - Deletes item number specified (1-based)</li>
    * </ul>
    * @param in the input command entered by user
    * @TaskList the TaskList object the commands will be processed on
    * @return String object containing results of processing 
    */
    public String process(String in, TaskList taskList) {
        
        try {

            String result = "";
            String[] split = in.split("\\s");
            String command = split[0];

            switch(command) {
            case "bye": {
                isEnd = true;
                result = "Bye. Hope to see you again soon!";
                break;
            } case "list": {
                result = "Here are the tasks in your list:\n";
                result += taskList.printList();
                break;
            } case "done": {
                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! A number needs to be specified.");
                }

                try {
                    Task task = taskList.markDone(Integer.parseInt(split[1]));

                    result = "Nice! I've marked this task as done:\n";
                    result += task;
                } catch (NumberFormatException ne) {
                    throw new DukeException("OOPS!!! A number needs to be specified.");
                }

                break;
            } case "todo": {
                split = in.split("todo\\s");

                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }

                Task task = new Task(split[1]);
                taskList.add(task);

                result = "Got it. I've added this task:\n";
                result += task + "\n";
                result += "Now you have " + taskList.count() + " tasks in the list.";
                break;
            } case "deadline": {
                split = in.split("deadline\\s");

                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }

                split = split[1].split("\\s/by\\s");

                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! A date and time is needed.");
                }

                LocalDate dt = LocalDate.now();

                try {

                    dt = LocalDate.parse(split[1]);

                } catch (DateTimeParseException dte) {
                    throw new DukeException("OOPS!!! The date format should be in YYYY-MM-DD");
                }

                Task task = new Task(split[0], 'D', dt);
                taskList.add(task);

                result = "Got it. I've added this task:\n";
                result += task + "\n";
                result += "Now you have " + taskList.count() + " tasks in the list.\n";
                break;
            } case "event": {
                split = in.split("event\\s");

                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }

                split = split[1].split("\\s/at\\s");

                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! A date and time is needed.");
                }

                LocalDate dtt = LocalDate.now();

                try {

                    dtt = LocalDate.parse(split[1]);

                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPS!!! The date format should be in YYYY-MM-DD");
                }

                Task task = new Task(split[0], 'E', dtt);
                taskList.add(task);

                result = "Got it. I've added this task:\n";
                result += task + "\n";
                result += "Now you have " + taskList.count() + " tasks in the list.\n";
                break;
            } case "delete": {
                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! A number needs to be specified.");
                }

                try {
                    Task task = taskList.remove(Integer.parseInt(split[1]));

                    result = "Noted. I've removed this task\n";
                    result += task + "\n";
                    result += "Now you have " + taskList.count() + " tasks in the list.\n";
                } catch (NumberFormatException ne) {
                    throw new DukeException("OOPS!!! A number needs to be specified.");
                }

                break;
            } case "find": {
                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! A search term needs to be specified.");
                }

                String searchTerm = split[1];

                TaskList searchList = new TaskList();
                for (int i = 0; i < taskList.count(); i++) {
                    Task task = taskList.getTask(i);

                    if (task.getName().contains(searchTerm)) {
                        searchList.add(task);
                        continue;
                    }

                    if (task.getType() == 'D' || task.getType() == 'E') {
                        if (task.getDate().contains(searchTerm)) {
                            searchList.add(task);
                            continue;
                        }
                    }
                }

                result = "Here are the matching task in your list:\n";
                result += searchList.printList();

                break;
            } default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
            
            return result;

        } catch(DukeException de) {
            return de.getMessage();
        }
        
    
    }

}