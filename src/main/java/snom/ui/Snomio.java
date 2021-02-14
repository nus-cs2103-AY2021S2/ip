package snom.ui;

import snom.exceptions.SnomException;
import snom.model.task.Task;
import snom.model.task.TaskList;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Deals with interactions with the user.
 * This snom.ui.Snomio simply compile both BufferedReader and BufferedWriter
 * for easier I/O usages. For eg. read snom.logic.commands, contents, numbers
 * from user's input.
 *
 * Solution below adapted from https://github.com/Kattis/kattio/blob/master/Kattio.java
 */
public class Snomio extends PrintWriter {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public Snomio(){
        super(new BufferedOutputStream(System.out));
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Prints a horizontal line.
     * Note that the output is not flushed. Remember to flush after calling the method.
     */
    public void showLine(){
        println("--------------------------------");
    }

    /**
     * Prints given message as an error
     *
     * @param errMsg error message
     */
    public void showError(String errMsg){
        showLine();
        println("Error: " + errMsg);
        showLine();
        flush();
    }

    /**
     * Prints an error of unable to load save file and prompt user a new save file will be created.
     */
    public void showLoadingError(){
        showError("Failed to load file. A new save file will be created.");
    }

    /**
     * Returns the welcome message of snom.model.Snom on startup.
     *
     * @return String welcome message
     */
    public String getWelcomeMsg(){
        return "Bonjour! I'm snom.model.Snom! *squish*\n"
                + "Try giving me some snom.logic.commands, I might be able to do something!\n"
                + "[type 'bye' to exit program]";
    }

    /**
     * Returns a message containing the entire task list.
     *
     * @return String        string of entire task List
     * @throws SnomException if there is content after the command or there isn't any task in the task list
     */
    public String getTaskList(TaskList taskList) throws SnomException {
        if(taskList.getSize() <= 0){
            throw new SnomException("You have no task in your list right now, try adding some and try again :D");
        }

        String message = "Here are the snom.tasks in your list:\n";
        for(int i = 0; i < taskList.getSize(); i++){
            message += (i+1) + ". " + taskList.getTask(i).toString() + "\n";
        }

        return message;
    }

    /**
     * Returns out the list from searching the keyword.
     *
     * @return String        string of matching task list
     * @throws SnomException if there is content after the command or there isn't any task in the task list
     */
    public String getMatchingTaskList(TaskList taskList) throws SnomException{
        if(taskList.getSize() <= 0){
            throw new SnomException("No matching task found.");
        }

        String message = "Here are the matching snom.tasks in your list:\n";
        for(int i = 0; i < taskList.getSize(); i++){
            message += (i+1) + ". " + taskList.getTask(i).toString() + "\n";
        }

        return message;
    }

    /**
     * Returns the task added into the taskList and size of current taskList.
     *
     * @param task     task added
     * @param listSize task list size
     * @return String  task added into taskList
     */
    public String getTaskAdded(Task task, int listSize){
        return "Got it. I've added this task:\n"
                + "\t" + task.toString() + "\n"
                + "Now you have " + listSize + " snom.tasks in the list.";
    }

    /**
     * Returns the list of recent finished snom.tasks.
     *
     * @param finishedTasks list of finished snom.tasks
     * @return String       recent finished snom.tasks
     */
    public String getFinishedTasks(Task[] finishedTasks) {
        String message = "Great Job! I've marked this task(s) as finish:\n";
        for(Task task: finishedTasks){
            message += "\t" + task.toString() + "\n";
        }
        return message;
    }

    /**
     * Returns the list of recently deleted snom.tasks.
     *
     * @param deletedTasks list of deleted snom.tasks
     * @return String      recent deleted snom.tasks
     */
    public String getDeletedTasks(Task[] deletedTasks) {
        String message = "Noted, I've removed this task(s)\n";
        for(Task task: deletedTasks){
            message += "\t" + task.toString() + "\n";
        }
        return message;
    }

    /**
     * Returns exit message.
     *
     * @return String exit message
     */
    public String getExitMessage(){
        return "Ciao! Hope to see you again soon!";
    }

    /**
     * Returns the first word from next input line.
     * If there are already words/tokens in the tokenizer, it will return the next first token instead.
     *
     * @return  the first word that extracted from the tokenizer
     */
    public String readWord() {
        String input = null;
        String token;

        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tokenizer = new StringTokenizer(input);
        }
        token = tokenizer.nextToken();

        return token;
    }

    /**
     * Returns remaining words/tokens in the tokenizer as a string.
     *
     *  @return the whole line of input or the rest of the sentence from the previous read line
     */
    public String readRemainingLine(){
        String line = "";

        if(tokenizer == null || !tokenizer.hasMoreTokens()){
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            line += tokenizer.nextToken("");
        }

        return line;
    }

    /**
     * Returns the first integer input.
     *
     * @return  single integer input
     */
    public int readInt() throws SnomException{
        try{
            return Integer.parseInt(readWord());
        }catch(NumberFormatException e){
            throw new SnomException("Oops! You have entered invalid task numbers, please try again!");
        }
    }

    /**
     * Returns first double input.
     *
     * @return  single double input
     */
    public double readDouble(){
        return Double.parseDouble(readWord());
    }
}
