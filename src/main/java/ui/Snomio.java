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
    public String showWelcomeMsg(){
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
    public String showTaskList(TaskList taskList) throws SnomException{
        if(taskList.getSize() > 0){
            String message = "Here are the tasks in your list:\n";
            for(int i = 0; i < taskList.getSize(); i++){
                message += (i+1) + ". " + taskList.getTask(i).toString() + "\n";
            }
            return message;
        }else{
            throw new SnomException("You have no task in your list right now, try adding some and try again :D");
        }
    }

    /**
     * Prints out the list from searching the keyword.
     *
     * @throws SnomException if there is content after the command or there isn't any task in the task list
     */
    public void showMatchingTaskList(TaskList taskList) throws SnomException{
        showLine();
        if(taskList.getSize() > 0){
            println("Here are the matching tasks in your list:");
            for(int i = 0; i < taskList.getSize(); i++){
                println((i+1) + ". " + taskList.getTask(i).toString());
            }
        }else{
            throw new SnomException("No matching task found.");
        }
        showLine();
        flush();
    }

    /**
     * Returns the task added into the taskList and size of current taskList.
     *
     * @param task     task added
     * @param listSize task list size
     * @return String  task added into taskList
     */
    public String showTaskAdded(Task task, int listSize){
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
    public String showFinishedTasks(Task[] finishedTasks) {
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
    public String showDeletedTasks(Task[] deletedTasks) {
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
    public String showExitMessage(){
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
     * This method is exclusive to read the content of the commands (todo, deadline, event)
     *
     * @param command        command to be executed
     * @return               the content of a task
     * @throws SnomException throws exception if the content is empty
     */
    public String readContent(String command) throws SnomException {
        if(tokenizer.hasMoreTokens()){
            return tokenizer.nextToken("");
        }else{
            throw new SnomException("OOPS!!! The description of a " + command + " cannot be empty.");
        }
    }

    /**
     * This method is exclusive to read the content of the commands with Delimiters (deadline, event)
     *
     * @param command        command to be executed
     * @param delim          delimiter to split the date from content
     * @return               the content of a task
     * @throws SnomException throws exception if the content is empty
     */
    public String[] readContentWithDate(String command, String delim) throws SnomException {
        String content = this.readContent(command);
        String[] array = content.split(delim);
        if(array.length == 2){
            return array;
        }else if(array.length < 2){
            throw new SnomException("Please enter at least one date for your " + command + "!");
        }else{
            throw new SnomException("Oops! You have entered more than ONE date, please try again!");
        }
    }

    /**
     * This method is exclusive to read the content of the commands with number list (finish, delete)
     *
     * @param command        command to be executed
     * @return               a number list for command to be executed
     * @throws SnomException throws exception if the number is invalid
     */
    public int[] readContentWithNumbers(String command) throws SnomException{
        int[] nums = new int[tokenizer.countTokens()];
        if(nums.length > 0){
            for(int i = 0; i < nums.length; i++){
                nums[i] = this.readInt();
            }
            return nums;
        }else{
            throw new SnomException("Oops! Please at least give one task number to " + command + " a task");
        }
    }

    /**
     * This method is similar to BufferedReader.readLine() except it will read from the tokenizer
     * if there are remaining tokens in it. It will return everything in the tokenizer as a whole sentence.
     *
     * @return the whole line of input or the rest of the sentence from the previous read line
     */
    public String readLine(){
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
