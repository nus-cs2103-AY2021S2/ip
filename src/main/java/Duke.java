import java.io.*;

public class Duke {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static Tasklist list = new Tasklist();

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
            String[] starr = string.split(" ");
            if(!string.equals("bye")) {
                if(string.equals("list")) {
                    list.printList();
                } else if(starr[0].equals("done")){
                    list.lst.get(Integer.parseInt(starr[1]) - 1).done();
                    pw.println("Nice! I've marked this task as done:");
                    pw.printf(" %s%n", list.lst.get(Integer.parseInt(starr[1]) - 1));
                    pw.flush();
                } else if(starr[0].equals("todo")) {
                    String message = "";
                    String type = null;
                    for (int i = 0; i < starr.length; i++) {
                        if (i == 0) {
                            type = starr[i];
                        } else {
                            message = message.concat(starr[i]);
                            if (i != starr.length - 1) {
                                message = message + " ";
                            }
                        }
                    }
                    addTask(message, type, null);
                } else if(starr[0].equals("deadline") || starr[0].equals("event")) {
                    String[] arr = string.split("/");
                    String date = arr[1];
                    String message = "";
                    String[] s = arr[0].split(" ");
                    String type = "";
                    for(int i = 0; i < s.length; i++) {
                        if(i == 0) {
                            type = s[i];
                        } else {
                            message = message.concat(s[i]);
                            if(i != s.length - 1) {
                                message = message + " ";
                            }
                        }
                    }
                    addTask(message, type, date);
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

    public static void addTask(String message, String type, String date) {
        Task task = null;
        switch (type) {
            case "todo":
                task = new Todo(message);
                break;
            case "deadline":
                task = new Deadline(message, date);
                break;
            case "event":
                task = new Event(message, date);
                break;
        }

        list.addItem(task);
        pw.println("Got it. I've added this task:");
        pw.printf(" %s%n", task);
        pw.printf("Now you have %d tasks in the list.%n", list.lst.size());
        pw.flush();
    }

    public static void exit() {
        pw.println("Bye. Hope to see you again soon!");
    }
}
