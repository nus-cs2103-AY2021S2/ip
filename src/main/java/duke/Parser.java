package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Parser {
    private boolean end;
    
    /**
    * Constructor for the Parser class
    */
    public Parser() {
        end = false;
    }
    
    /**
    * Returns the End object which indicates whether the chatbot has been terminated by the user.
    * @return boolean object. True - chatbot ended, False - chatbot still running
    */
    public boolean checkEnd() {
        return end;
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
    public String process(String in, TaskList tl){
        
        try {

            String res = "";
            
            String[] split = in.split("\\s");
            
            String cmd = split[0];


            switch(cmd) {
            case "bye":
                end = true;
                res = "Bye. Hope to see you again soon!";
                break;
            case "list":
                res = "Here are the tasks in your list:\n";
                res += tl.printList();
                break;
            case "done":
                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! A number needs to be specified.");
                }
                
                try {
                    Task t = tl.markDone(Integer.parseInt(split[1]));
                    
                    res = "Nice! I've marked this task as done:\n";
                    res += t;
                } catch(NumberFormatException ne) {
                    throw new DukeException("OOPS!!! A number needs to be specified.");
                }
                
                break;
            case "todo":
                split = in.split("todo\\s");
                
                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                
                Task td = new Task(split[1]);
                tl.add(td);
                
                res = "Got it. I've added this task:\n";
                res += td + "\n";
                res += "Now you have "+tl.count()+" tasks in the list.";
                break;
            case "deadline":
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
                
                } catch(DateTimeParseException dte) {
                    throw new DukeException("OOPS!!! The date format should be in YYYY-MM-DD");
                }
                
                Task dl = new Task(split[0], 'D', dt);
                tl.add(dl);
                
                res = "Got it. I've added this task:\n";
                res += dl + "\n";
                res += "Now you have "+tl.count()+" tasks in the list.\n";
                break;
            case "event":
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
                
                } catch(DateTimeParseException e) {
                    throw new DukeException("OOPS!!! The date format should be in YYYY-MM-DD");
                }
                
                Task ev = new Task(split[0], 'E', dtt);
                tl.add(ev);
                
                res = "Got it. I've added this task:\n";
                res += ev + "\n";
                res += "Now you have "+tl.count()+" tasks in the list.\n";
                break;
            case "delete":
                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! A number needs to be specified.");
                }
                
                try {
                    Task t = tl.remove(Integer.parseInt(split[1]));
                    
                    res = "Noted. I've removed this task\n";
                    res += t + "\n";
                    res += "Now you have "+tl.count()+" tasks in the list.\n";
                } catch(NumberFormatException ne) {
                    throw new DukeException("OOPS!!! A number needs to be specified.");
                }
                
                break;
            case "find":
                if (split.length <= 1) {
                    throw new DukeException("OOPS!!! A search term needs to be specified.");
                }
                
                String searchTerm = split[1];
                
                TaskList searchList = new TaskList();
                for (int i = 0; i < tl.count(); i++) {
                    Task t = tl.getTask(i);
                    
                    if (t.getName().contains(searchTerm)) {
                        searchList.add(t);
                        continue;
                    }
                    
                    if (t.getType() == 'D' || t.getType() == 'E') {
                        if (t.getDate().contains(searchTerm)) {
                            searchList.add(t);
                            continue;
                        }
                    }
                }
                
                res = "Here are the matching task in your list:\n";
                res += searchList.printList();
                
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    
            }
            
            return res;

        } catch(DukeException de) {
            return de.getMessage();
        }
        
    
    }

}