package core;


import java.io.*;

/**
 * The main IOManager of the program, manages reading input and outputting.
 */
public class IOManager {
    private InputListener listener;
    private InputStream is;
    private PrintWriter pw;

    /**
     * Creates a new IOManager using {@code System.in} and {@code System.out}.
     */
    public IOManager() {
        this(System.in, System.out);
    }

    /**
     * Creates a new IOManager using specified input and output streams.
     * @param is - input stream
     * @param ps - output stream
     */
    public IOManager(InputStream is, PrintStream ps) {
        this.is = is;
        this.pw = new PrintWriter(ps);
    }

    /**
     * Sets a new {@code InputListener} instance to handle the input.
     * @param listener
     */
    public void setListener(InputListener listener) {
        this.listener = listener;
    }

    private void preOutputFormatting() {
        pw.println("=================");
    }

    private void postOutputFormatting() {
        pw.println("=================");
    }



    /**
     * The main input loop.
     */
    public void run() {
        try (var br = new BufferedReader(new InputStreamReader(this.is))) {
            while (true) {
                String s = br.readLine();
                preOutputFormatting();
                if (s.equalsIgnoreCase("bye")) {
                    // exit condition
                    pw.println("bye");
                    break;
                } else {
                    String toPrint = "";
                    for(var x : InputType.values()) {
                        if(x.doesMatch(s)) {
                            toPrint += listener.onNewInput(x, s);
                            break;
                        }
                    }
                    pw.println(toPrint);
                }
                postOutputFormatting();
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
