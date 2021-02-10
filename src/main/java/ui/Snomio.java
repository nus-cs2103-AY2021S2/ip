package ui; /**
 * Deals with interactions with the user.
 * This ui.Snomio simply compile both BufferedReader and BufferedWriter
 * for easier I/O usages. For eg. read commands, contents, numbers
 * from user's input.
 *
 * Solution below adapted from https://github.com/Kattis/kattio/blob/master/Kattio.java
 *
 * @author: Sharptail
 */
import exceptions.SnomException;
import tasks.Task;
import tasks.TaskList;

import java.io.*;
import java.util.StringTokenizer;

public class Snomio extends PrintWriter {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public Snomio(){
        super(new BufferedOutputStream(System.out));
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Snomio(InputStream is, OutputStream os){
        super(new BufferedOutputStream(os));
        reader = new BufferedReader(new InputStreamReader(is));
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
     * Returns the welcome message of Snom on startup.
     *
     * @return String welcome message
     */
    public String getWelcomeMsg(){
        return "Bonjour! I'm Snom! *squish*\n"
                + "Try giving me some commands, I might be able to do something!\n"
                + "[type 'bye' to exit program]";
    }

    /**
     * Returns a message containing the entire task list.
     *
     * @return String        string of entire task List
     * @throws SnomException if there is content after the command or there isn't any task in the task list
     */
    public String getTaskList(TaskList taskList) throws SnomException{
        if(taskList.getSize() <= 0){
            throw new SnomException("You have no task in your list right now, try adding some and try again :D");
        }

        String message = "Here are the tasks in your list:\n";
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

        String message = "Here are the matching tasks in your list:\n";
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
                + "Now you have " + listSize + " tasks in the list.";
    }

    /**
     * Returns the list of recent finished tasks.
     *
     * @param finishedTasks list of finished tasks
     * @return String       recent finished tasks
     */
    public String getFinishedTasks(Task[] finishedTasks) {
        String message = "Great Job! I've marked this task(s) as finish:\n";
        for(Task task: finishedTasks){
            message += "\t" + task.toString() + "\n";
        }
        return message;
    }

    /**
     * Returns the list of recently deleted tasks.
     *
     * @param deletedTasks list of deleted tasks
     * @return String      recent deleted tasks
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
     * This method will read in the whole line and split each word into tokens.
     * Then it will extract out the first token and return it.
     *
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
     * This method is similar to BufferedReader.readLine() except it will read from the tokenizer
     * if there are remaining tokens in it. It will return everything in the tokenizer as a whole sentence.
     *
     * @return the whole line of input or the rest of the sentence from the previous read line
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
     * This method only reads the first integer input and returns it.
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
     * This method only reads the first double input and returns it.
     *
     * @return  single double input
     */
    public double readDouble(){
        return Double.parseDouble(readWord());
    }
}
