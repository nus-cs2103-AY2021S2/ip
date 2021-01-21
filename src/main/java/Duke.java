import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = "Hello! I'm Duke\n" + "Please enter list below";
        dukePrint(greeting);
        loop();
    }




    public static void dukePrint(String s) {
        System.out.println("\n----------------------");
        System.out.println(s);
        System.out.println("----------------------\n");
    }

    public static void dukePrint(List<Task> ls) {
        System.out.println("\n----------------------");
        for (int i = 0 ; i < ls.size(); i++) {
            System.out.print(i+1);
            System.out.println(". " + ls.get(i) );
        }
        System.out.println("----------------------\n");
    }

    public static void loop() {
        Scanner input = new Scanner(System.in);
        boolean end = false;

        List<Task> myList = new ArrayList<>();

        while(!end) {
            String s = input.nextLine();
            if (s.equals("bye")) {
                end = true;
                dukePrint("Bye bye! See you later!");
            } else if (s.equals("list")) {
                dukePrint(myList);
            } else if (s.matches("done \\d+")) {
                s = s.replaceAll("done ","");
                int index = Integer.parseInt(s) - 1;
                if (index < 0) {
                    dukePrint("Uhhh.... Our list starts at 1...");
                } else {
                    myList.get(index).isDone = true;
                    dukePrint("Good! We finished task: " + s + ". " + myList.get(index));
                }
            } else if (s.matches("deadline .+?( /by ).*")) {
                s = s.replaceAll("deadline ","");
                String[] deadlineInfo = s.split(" /by ",-1);
                Deadline t = new Deadline(deadlineInfo[0],deadlineInfo[1]);
                myList.add(t);
                dukePrint("added new deadline: " + t);
            } else if (s.matches("event .+?( /at ).*")) {
                s = s.replaceAll("event ","");
                String[] eventInfo = s.split(" /at ",-1);
                Event t = new Event(eventInfo[0],eventInfo[1]);
                myList.add(t);
                dukePrint("added new event: " + t);
            } else if (s.matches("todo .*")) {
                s = s.replaceAll("todo ","");
                Todo t = new Todo(s);
                myList.add(t);
                dukePrint("added new todo: " + t);
            } else {
                Task t = new Task(s);
                myList.add(t);
                dukePrint("added task: " + t);
            }
        }
    }
}
