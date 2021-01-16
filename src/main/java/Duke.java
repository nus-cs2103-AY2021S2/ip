import java.io.*;

public class Duke {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void main(String[] args) throws IOException {
        // Prints greeting
        printHorizontalLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        pw.println("Hello! I'm \n" + logo + "\nWhat can I do for you?");
        printHorizontalLine();
        pw.flush();

        // Ask for commands
        while (true) {
            String command = br.readLine();
            printHorizontalLine();
            if (command.equals("bye")) {
                // Exit program
                break;
            } else {
                // Echos command
                pw.println(command);
                printHorizontalLine();
                pw.flush();
            }
        }

        pw.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
        pw.close();
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 60; i++) {
            pw.print('-');
        }
        pw.println();
    }
}