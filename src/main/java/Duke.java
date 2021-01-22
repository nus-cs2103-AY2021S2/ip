import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Event> list = new ArrayList<>();
        Scanner sc = new Scanner (System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! What can I do for you:>");

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }
            } else {
                String[] inputSplit = input.split(" ");
                if (inputSplit[0].equals("done")) {
                    int value = Integer.valueOf(inputSplit[1]);
                    list.get(value - 1).setDone();
                    System.out.println("Nice, I have set this task as done!");
                    System.out.println(list.get(value - 1).toString());
                } else {
                    list.add(new Event(false, input));
                    System.out.println("added: " + input);
                }
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. See you again!");
    }
}

class Event {
    public boolean done = false;
    public String eventName = "";

    Event(boolean done, String eventName) {
        this.done = done;
        this.eventName = eventName;
    }
    public void setDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (this.done == true) {
            return "[X] " + eventName;
        } else {
            return "[ ] " + eventName;
        }
    }
}
