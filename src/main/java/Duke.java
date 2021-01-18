import java.io.*;

public class Duke {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static List list = new List();

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
                if(string.equals("list")) {
                    list.printList();
                } else {
                    addTask(string);
                }
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

    public static void addTask(String string) {
        list.addItem(string);
        pw.printf("added: %s%n", string);
        pw.flush();
    }

    public static void exit() {
        pw.println("Bye. Hope to see you again soon!");
    }
}
