package duke;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *  The Ui class contains methods to handle text represention to users via the CLI.
 */
public class Ui {

    public static final String BLANK_SPACE_BABY = "";
    public static final String HORIZONTAL_LINE = "________________________________________________";
    public static final String FILE_PATH = "data.txt";
    private static final String greeting =" Hello! I'm duke.\n What can I do for you?\n";
    private static String farewell = " Bye. Hope to see you again soon!";


    /**
     * Initialize and prints greeting message when a user runs programme.
     */

    public static String initGreeting() {
        return HORIZONTAL_LINE + "\n" + greeting + HORIZONTAL_LINE + "\n";
    }

    /**
     * Prints error message when a user encounters exceptions.
     */

    public static String showErrorMessage(String error){
        return HORIZONTAL_LINE + "\n"  + error + "\n" +HORIZONTAL_LINE;
    }


    /**
     * Prints success message when a user successfully adds a task.
     */

    public static String showSuccessfulAddedMessage(int numTask, Task task){
        String output = "";
        output += (HORIZONTAL_LINE + "\n" + " Got it! I've added this new Task!\n" );
        output += "  " + task.toString() +"\n";
        String t;
        if(numTask == 1){
            t = "task";
        } else {
            t = "tasks";
        }
        output += " Now you have " + numTask + " "+  t +" in your TaskList.\n";
        output += HORIZONTAL_LINE;
        return output;
    }


    /**
     * formars List as a string as output for the user
     * @param taskList
     * @return string output
     */

    public static String showListContent(TaskList taskList){
        String output = "";
        output += HORIZONTAL_LINE + "\n";
        if(taskList.getSize() == 0){
            output += " The list is empty!\n Please add tasks into the list :))" + "\n";
            output += HORIZONTAL_LINE;
            return output;
        } else {
            output += " Here are your remaining tasks!" + "\n";
            output += BLANK_SPACE_BABY + '\n';
            output += taskList.toString() + '\n';
            output += HORIZONTAL_LINE;
            return output;
        }
    }

    /**
     * formats the deadline tasks as a string as output for the user
     * @param deadlineTasks
     * @return string output
     */

    public static String showReminderContent(ArrayList<Deadline> deadlineTasks){
        String output = "";
        output += HORIZONTAL_LINE + "\n";
        if(deadlineTasks.size() == 0){
            output += "You have no upcoming deadlines" + "\n";
            output += HORIZONTAL_LINE;
        } else {
            output += " Here are your upcoming deadline Tasks!" +"\n";
            output += BLANK_SPACE_BABY + "\n";
            for(int i = 0; i< deadlineTasks.size(); i++){
                long noOfDaysBetween = ChronoUnit.DAYS.between( LocalDate.now(), deadlineTasks.get(i).getLocalDate());
                output +=  (i+1) + " " + deadlineTasks.get(i).toString() + " "
                        + noOfDaysBetween + " days left!!" +"\n";
            }
            output += HORIZONTAL_LINE;
        }
        return output;
    }

    /**
     * formats the done task as a string as output for the user
     * @param task
     * @return String output
     */
    public static String markTaskAsDone(Task task){
        String output = "";
        output += HORIZONTAL_LINE + "\n";
        output += " Nice! I've marked this task as done" + '\n';
        output += " " + task.toString() + "\n";
        output += HORIZONTAL_LINE;
        return output;
    }

    /**
     * formats deleted tasks as a string to be output to the user
     * @param task
     * @return String output
     */
    public static String showTaskAsDeleted(Task task){
        String output = "";
        output += HORIZONTAL_LINE + "\n";
        output += " Noted! I have removed this task from the list.\n" ;
        output += task.toString() + "\n";
        output += HORIZONTAL_LINE;
        return output;
    }


    /**
     * formats bye message as string as output for user
     * @return String output
     */

    public static String showByeMessage(){
        String output = "";
        output += HORIZONTAL_LINE + "\n";
        output += farewell + "\n";
        output += HORIZONTAL_LINE;
        return output;
    }


    /**
     * formats list of tasks that has common keywords as a string as output for user
     * @param matchingTaks
     * @return string output
     */
    public static String showKeyWordMessage(ArrayList<String> matchingTaks){
        String output = "";
        output += HORIZONTAL_LINE + "\n";
        output += "Here are the relevant tasks:\n" + BLANK_SPACE_BABY;
        for(int i=0; i< matchingTaks.size(); i++){
            output += matchingTaks.get(i) + "\n";
        }
        output += HORIZONTAL_LINE;
        return output;
    }

    /**
     * formats default message as a string as output for user
     * @return default message, list of all valid commands, as string
     */

    public static String showDefaultStatement(){
        String output = "";
        output += HORIZONTAL_LINE + "\n";
        output += " Please enter a valid command\n Valid commands include:\n";
        output += " bye\n deadline\n delete\n done\n event\n find\n list\n reminder\n todo\n";
        output += HORIZONTAL_LINE;
        return output;
    }

}
