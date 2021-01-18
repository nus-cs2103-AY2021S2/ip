import java.io.*;

public class Duke {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void main(String[] args) throws IOException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        while(true) {
            String string = br.readLine();
            if(!string.equals("bye")) {
                echo(string);
            } else {
                exit();
                break;
            }
        }
        pw.close();
    }

    public static void greet() {
        pw.println("Hello! I'm Duke");
        pw.println("What can I do for you?");
        pw.flush();
    }

    public static void echo(String string) {
        pw.println(string);
        pw.flush();
    }

    public static void exit() {
        pw.println("Bye. Hope to see you again soon!");
    }
}
