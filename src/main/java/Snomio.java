/**
 * This Snomio simply compile both BufferedReader and BufferedWriter
 * for easier I/O usages.
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
    public int readInt(){
        return Integer.parseInt(readWord());
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
