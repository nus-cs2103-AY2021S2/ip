import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");//only take in lines and not by whitespace, coz have one case where " " keeps the sc running to the next
        ArrayList<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________"+
                "________________\n";
        System.out.println(line + " Hey there! I'm Duke\n" +
                " How can I help you?\n" + line);

        while(sc.hasNext()) {
            String str = sc.nextLine();
            str = str.trim();
            if (str.equals("bye")) {
                break;
            } else if(str.length()==0) {//if just enter spaces
                System.out.println(line + " Please enter a task\n"
                        + line);
            } else if (str.equals("list")) {
                System.out.println(line);
                if (tasks.size() > 0) {
                    for (int j = 1; j <= tasks.size(); j++) {
                        System.out.println(" " + j + ". " + tasks.get(j - 1));
                    }
                } else {
                    System.out.println(" No tasks so far!");
                }
                System.out.println(line);
            } else {
                String[] split = str.split(" ");
                if(split.length==2 && split[0].equals("done")) {
                    try {
                        int num = Integer.parseInt(split[1]);
                        System.out.println(line + tasks.get(num-1).doneTask()
                                + "\n" + line);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(line + " Enter 'done' followed by a number between " +
                                "1 and " + tasks.size() +"\n" + line);
                    }
                } else {
                    //System.out.println(split.length);
                    tasks.add(new Task(str));
                    System.out.println(line + " added: " + str + "\n" + line);
                }
            }
        }

        System.out.println(line + " Bye, see you again!\n"
                + line);
    }
}
