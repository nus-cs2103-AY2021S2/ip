import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MyDuke {

    public static String DASH = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>(); // change from string to task
        print(new String[] { "Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.", "Pai Kia Bot: What you want?" });

        System.out.print("You: ");
        String input = sc.nextLine();

        // level-1
        while (!input.equals("bye")) {

            String[] inputArr = input.split(" ", 2); //improved implementation
            System.out.println(inputArr[0]);
            // Level-2 implementation
            if (input.equals("list")) {
                int counter = 1;
                String[] tempArr = new String[100];
                if (list.isEmpty()) { //improved implementation in case list is empty, gives a clear output
                    print("Paikia Bot: eh list is empty leh."); 
                } else {
                    for (Task t : list) { // changed String s to Task t
                        tempArr[counter - 1] = counter + ". " + t.toString();
                        counter++;
                    }
                    print(tempArr);
                }
                
            } else if (inputArr[0].equals("done")) { // level-3 addition
                if (inputArr.length == 1) { // if no arg, error management
                    print("Paikia Bot: you done what task, limpeh need more information ah. input 'done <task number>. eg, done 3");
                } else {
                    int ref = Integer.parseInt(inputArr[1]); //error management: input ref may exceed total number of task in list, KIV
                    list.set(ref - 1, list.get(ref - 1).setAsDone());
                    print("Paikia Bot: ok i just help u checked this task as done -- " + list.get(ref - 1).toString());
                }
            } else if (inputArr[0].equals("todo")) {
                if (inputArr.length == 1) { // if no arg, error management
                    print("Paikia Bot: you want to add what todo task, limpeh need more information ah. input 'todo <info>. eg, todo read book");
                } else {
                    ToDo td = new ToDo(inputArr[1], false);
                    list.add(td);
                    print(new String[] {
                        "Paikia Bot: ok i just help u added this todo -- " + td.toString(),
                        "Paikia Bot: now u got " + list.size() + " item(s) in your list ah"
                    });
                }
            } else if (inputArr[0].equals("event")) {
                if (inputArr.length == 1) { // if no arg, error management
                    print("Paikia Bot: you want to add what event task, limpeh need more information ah. input 'todo <info>. eg, todo read book");
                } else {
                    String[] temp = inputArr[1].split("/", 2); //warning, error management required
                    Event e = new Event(temp[1], temp[0], false);
                    list.add(e);
                    print(new String[] {
                        "Paikia Bot: ok i just help u added this event -- " + e.toString(),
                        "Paikia Bot: now u got " + list.size() + " item(s) in your list ah"
                    });
                }
            } else if (inputArr[0].equals("deadline")) {
                if (inputArr.length == 1) { // if no arg, error management
                    print("Paikia Bot: you want to add what deadline task, limpeh need more information ah. input 'todo <info>. eg, todo read book");
                } else {
                    String[] temp = inputArr[1].split("/", 2); //warning, error management required
                    Deadline d = new Deadline(temp[1], temp[0], false);
                    list.add(d);
                    print(new String[] {
                        "Paikia Bot: ok i just help u added this deadline -- " + d.toString(),
                        "Paikia Bot: now u got " + list.size() + " item(s) in your list ah"
                    });
                }
            }
            else {
                print("Paikia Bot: wrong input format leh, can try again onot?");
            }

            System.out.print("You: ");
            input = sc.nextLine();
        }
        print("Pai Kia Bot: Leave so soon ah? Limpeh sleep first, if got no issue don't disturb me.");
    }

    static void print(String s) {
        System.out.println(DASH);
        System.out.println(s);
        System.out.println(DASH);
    }

    static void print(String[] sArr) {
        System.out.println(DASH);
        for (String s : sArr) {
            // Level-2 adjustments
            if (s != null) {
                System.out.println(s);
            }

        }
        System.out.println(DASH);
    }

}

// Level-3 additions
class Task {
    String info;
    boolean isDone;

    Task(String s, boolean b) {
        this.info = s;
        this.isDone = b;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    Task setAsDone() {
        return new Task(this.info, true);
    }

    Task setAsUndone() {
        return new Task(this.info, false);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.info;
    }
}

class ToDo extends Task {

    ToDo(String s, boolean b) {
        super(s,b);
    }

    ToDo setAsDone() {
        return new ToDo(this.info, true);
    }

    ToDo setAsUndone() {
        return new ToDo(this.info, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    String deadline;

    Deadline(String deadline, String s, boolean b) {
        super(s,b);
        this.deadline = deadline;
    }

    Deadline setAsDone() {
        return new Deadline(this.deadline, this.info, true);
    }

    Deadline setAsUndone() {
        return new Deadline(this.deadline, this.info, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.deadline + ")";
    }
}

class Event extends Task {
    String date;

    Event(String date, String s, boolean b) {
        super(s,b);
        this.date = date;
    }

    Event setAsDone() {
        return new Event(this.date, this.info, true);
    }

    Event setAsUndone() {
        return new Event(this.date, this.info, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.date + ")";
    }
}