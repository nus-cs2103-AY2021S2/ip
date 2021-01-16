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

    public String readWord() {
        String input = null;
        String token;

        while(tokenizer == null || tokenizer.hasMoreTokens() == false){
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(input == null){
                return null;
            }
            tokenizer = new StringTokenizer(input);
        }
        token = tokenizer.nextToken();

        return token;
    }

    public int readInt(){
        return Integer.parseInt(readWord());
    }

    public double readDouble(){
        return Double.parseDouble(readWord());
    }
}
