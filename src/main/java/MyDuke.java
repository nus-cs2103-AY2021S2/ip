import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class MyDuke {

    public static String DASH = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>(); // change from string to task
        print(new String[] { "Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.", "Pai Kia Bot: What you want?" });

        System.out.print("You: ");
        String input = sc.nextLine();
        System.out.println(input);

        // level-1
        while (!input.equals("bye")) {

            String[] inputArr = input.split(" ", 2); // improved implementation
            // System.out.println(inputArr[0]);
            // Level-2 implementation
            if (input.equals("list")) {
                int counter = 1;
                String[] tempArr = new String[100];
                if (list.isEmpty()) { // improved implementation in case list is empty, gives a clear output
                    print("Paikia Bot: eh list is empty leh.");
                } else {
                    for (Task t : list) { // changed String s to Task t
                        tempArr[counter - 1] = counter + ". " + t.toString();
                        counter++;
                    }
                    print(tempArr);
                }

            } else if (inputArr[0].equals("done")) { // level-3 addition

                try {
                    indexChecker(inputArr);
                    int ref = Integer.parseInt(inputArr[1]);
                    list.set(ref - 1, list.get(ref - 1).setAsDone());
                    print("Paikia Bot: ok i just help u checked this task as done -- " + list.get(ref - 1).toString());
                } catch (NoIndexException e) {
                    print(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    print("Paikia Bot: the number that you inputted is invalid leh (more than the total number of tasks or less than 1), try again pls ah");
                } catch (NumberFormatException e) {
                    print("Paikia Bot: ur input after 'done' is invalid, reminder that it should be a single integer"
                            + "and remember to not leave a space after your input. eg, done 3");
                }

            } else if (inputArr[0].equals("delete")) { // level-6 addition

                try {
                    indexChecker(inputArr);
                    int ref = Integer.parseInt(inputArr[1]);
                    Task toRemove = list.get(ref - 1);
                    list.remove(ref - 1);
                    print(new String[] {
                        "Paikia Bot: ok i just help u deleted this task -- " + toRemove.toString(),
                        "Paikia Bot: now u got " + list.size() + " item(s) in your list ah"
                    });
                } catch (NoIndexException e) {
                    print(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    print("Paikia Bot: the number that you inputted is invalid leh (more than the total number of tasks or less than 1), try again pls ah");
                } catch (NumberFormatException e) {
                    print("Paikia Bot: ur input after 'done' is invalid, reminder that it should be a single integer"
                            + "and remember to not leave a space after your input. eg, done 3");
                }

            } else if (inputArr[0].equals("todo")) {

                try {
                    todoChecker(inputArr);
                    ToDo td = new ToDo(inputArr[1], false);
                    list.add(td);
                    print(new String[] { "Paikia Bot: ok i just help u added this todo -- " + td.toString(),
                            "Paikia Bot: now u got " + list.size() + " item(s) in your list ah" });
                } catch (NoToDoException e) {
                    print(e.getMessage());

                }
            } else if (inputArr[0].equals("event")) {

                try {
                    eventChecker(inputArr);
                    String[] temp = inputArr[1].split("/", 2);
                    Event e = new Event(LocalDate.parse(temp[1]), temp[0], false);
                    list.add(e);
                    print(new String[] { "Paikia Bot: ok i just help u added this event -- " + e.toString(),
                            "Paikia Bot: now u got " + list.size() + " item(s) in your list ah" });
                } catch (NoEventException e) {
                    print(e.getMessage());
                } catch (NoDateException e) {
                    print(e.getMessage());
                } catch (DateTimeParseException e) {
                    print("Paikia Bot: oi your date input got format error cannot parse sial: " + inputArr[1].split("/", 2)[1]);
                    print(new String[] {
                        "Paikia Bot: oi your date input got format error cannot parse sial: " + inputArr[1].split("/", 2)[1],
                        "Paikia Bot: the format should be liddis: yyyy-mm-dd, eg. 2020-03-02"
                    });
                }

            } else if (inputArr[0].equals("deadline")) {

                try {
                    deadlineChecker(inputArr);
                    String[] temp = inputArr[1].split("/", 2);
                    Deadline d = new Deadline(LocalDate.parse(temp[1]), temp[0], false);
                    list.add(d);
                    print(new String[] { "Paikia Bot: ok i just help u added this deadline -- " + d.toString(),
                            "Paikia Bot: now u got " + list.size() + " item(s) in your list ah" });
                } catch (NoDeadlineException e) {
                    print(e.getMessage());
                } catch (NoDateException e) {
                    print(e.getMessage());
                } catch (DateTimeParseException e) {
                    print("Paikia Bot: oi your date input got format error cannot parse sial: " + inputArr[1].split("/", 2)[1]);
                    print(new String[] {
                        "Paikia Bot: oi your date input got format error cannot parse sial: " + inputArr[1].split("/", 2)[1],
                        "Paikia Bot: the format should be liddis: yyyy-mm-dd, eg. 2020-03-02"
                    });
                }

            } else {
                print("Paikia Bot: wrong input format leh, can try again onot?");
            }

            System.out.print("You: ");
            input = sc.nextLine();
            System.out.println(input);
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

    static void indexChecker(String[] inputArr) throws NoIndexException {
        if (inputArr.length == 1) {
            throw new NoIndexException(
                    "Paikia Bot: you done what task, limpeh need more information ah. input 'done <task number>'. eg, done 3");
        }
    }

    static void todoChecker(String[] inputArr) throws NoToDoException {
        if (inputArr.length == 1) {
            throw new NoToDoException(
                    "Paikia Bot: you want to add what todo task, limpeh need more information ah. input 'todo <info>. eg, todo read book");
        }
    }

    static void eventChecker(String[] inputArr) throws NoEventException, NoDateException {
        if (inputArr.length == 1) {
            throw new NoEventException(
                    "Paikia Bot: you want to add what event task, limpeh need more information ah. input 'event <info> /<date>. eg, event bookfest /at 24 Aug 1pm");
        } else if (inputArr[1].split("/", 2).length == 1) {
            throw new NoDateException(
                    "Paikia Bot: i dun see any date inputs leh. to add date input, use '/<date>'. eg, event bookfest /at 24 Aug 1pm");
        }
    }

    static void deadlineChecker(String[] inputArr) throws NoDeadlineException, NoDateException {
        if (inputArr.length == 1) {
            throw new NoDeadlineException(
                    "Paikia Bot: you want to add what deadline task, limpeh need more information ah. input 'deadline <info> <date>. eg, deadline return book /by 10 Aug");
        } else if (inputArr[1].split("/", 2).length == 1) {
            throw new NoDateException(
                    "Paikia Bot: i dun see any date inputs leh. to add date input, use '/<date>'. eg, deadline return book /by 10 Aug");
        }
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
        super(s, b);
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
    LocalDate deadline;

    Deadline(LocalDate deadline, String s, boolean b) {
        super(s, b);
        this.deadline = deadline;
    }

    Deadline setAsDone() {
        return new Deadline(this.deadline, this.info, true);
    }

    Deadline setAsUndone() {
        return new Deadline(this.deadline, this.info, false);
    }

    String timeDisplay() { //format: MMM dd yyyy
        String year = Integer.toString(this.deadline.getYear());
        String month = this.deadline.getMonth().name().substring(0,3);
        String day = this.deadline.getDayOfMonth() > 9
            ? Integer.toString(this.deadline.getDayOfMonth())
            : "0" + Integer.toString(this.deadline.getDayOfMonth());

        return month + " " + day + " " + year;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.timeDisplay() + ")";
    }
}

class Event extends Task {
    LocalDate date;

    Event(LocalDate date, String s, boolean b) {
        super(s, b);
        this.date = date;
    }

    Event setAsDone() {
        return new Event(this.date, this.info, true);
    }

    Event setAsUndone() {
        return new Event(this.date, this.info, false);
    }

    String timeDisplay() { //format: MMM dd yyyy
        String year = Integer.toString(this.date.getYear());
        String month = this.date.getMonth().name().substring(0,3);
        String day = this.date.getDayOfMonth() > 9
            ? Integer.toString(this.date.getDayOfMonth())
            : "0" + Integer.toString(this.date.getDayOfMonth());

        return month + " " + day + " " + year;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.timeDisplay() + ")";
    }
}

class MyDukeException extends Exception {

    MyDukeException(String s) {
        super(s);
    }
}

class NoIndexException extends MyDukeException {
    NoIndexException(String s) {
        super(s);
    }
}

class NoToDoException extends MyDukeException {
    NoToDoException(String s) {
        super(s);
    }
}

class NoEventException extends MyDukeException {
    NoEventException(String s) {
        super(s);
    }
}

class NoDeadlineException extends MyDukeException {
    NoDeadlineException(String s) {
        super(s);
    }
}

class NoDateException extends MyDukeException {
    NoDateException(String s) {
        super(s);
    }
}