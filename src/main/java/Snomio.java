/**
 * This Snomio simply compile both BufferedReader and BufferedWriter
 * for easier I/O usages. For eg. read commands, contents, numbers
 * from user's input.
 *
 * Solution below adapted from https://github.com/Kattis/kattio/blob/master/Kattio.java
 *
 * @author: Sharptail
 */
import java.io.*;
import java.util.StringTokenizer;

public class Snomio extends PrintWriter {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public Snomio(InputStream is, OutputStream os){
        super(new BufferedOutputStream(os));
        reader = new BufferedReader(new InputStreamReader(is));
    }

    public boolean hasMoreWord(){
        return tokenizer.hasMoreTokens();
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
     * @return               the content of a task
     * @throws SnomException throws exception if the content is empty
     */
    public String[] readContentWithDate(String command, String date) throws SnomException {
        String content = this.readContent(command);
        String[] array = content.split(date);
        if(array.length == 2){
            return array;
        }else if(array.length < 2){
            throw new SnomException("Please enter a valid " + command + " date!");
        }else{
            throw new SnomException("OOPS! You have entered more than ONE date, please try again!");
        }
    }

    /**
     * This method is exclusive to read the content of the commands with number list (done, delete)
     *
     * @param command        command to be executed
     * @return               a number list for command to be executed
     * @throws SnomException throws exception if the number is invalid
     */
    public int[] readContentWithNumbers(String command) throws SnomException{
        int[] nums = new int[tokenizer.countTokens()];
        for(int i = 0; i < nums.length; i++){
            nums[i] = this.readInt();
        }
        return nums;
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
