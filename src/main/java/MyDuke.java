import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class MyDuke {

    public static String DASH = "____________________________________________________________";
    // public static String DATA_SAVE_FILE_DIR = "../../../data/saveFile.txt";
    public static String DATA_SAVE_FILE_DIR = "../data/saveFile.txt";

    private static List<Task> printFileContents(String filePath)
            throws FileNotFoundException, InvalidArgException, NumberFormatException, InvalidTypeException {
        File f = new File(filePath); // create a File for the given file path
        Scanner sc = new Scanner(f); // create a Scanner using the File as the source
        List<Task> l = new ArrayList<>();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            // System.out.println(input);
            String[] inputArr = input.split(" @ ");
            if (inputArr.length < 3 && inputArr.length > 4) {
                throw new InvalidArgException("Paikia Bot: eh walao something is wrong with this input: " + input
                        + ", pls double check and rectify");
            }
            // System.out.println(inputArr.length);
            boolean isDone = Integer.parseInt(inputArr[1]) == 1;
            if (inputArr[0].equals("T")) {
                l.add(new ToDo(inputArr[2], isDone));
            } else if (inputArr[0].equals("E")) {
                l.add(new Event(LocalDate.parse(inputArr[3]), inputArr[2], isDone));
            } else if (inputArr[0].equals("D")) {
                l.add(new Deadline(LocalDate.parse(inputArr[3]), inputArr[2], isDone));
            }
        }
        return l;
    }

    private static void writeToFile(String filePath, List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : list) {
            int doneStatus = t.isDone ? 1 : 0;
            if (t instanceof ToDo) {
                fw.write("T @ " + doneStatus + " @ " + t.info + System.lineSeparator());
            } else if (t instanceof Event) {
                Event e = (Event) t;
                fw.write("E @ " + doneStatus + " @ " + t.info + " @ " + e.date + System.lineSeparator());
            } else {
                Deadline d = (Deadline) t;
                fw.write("D @ " + doneStatus + " @ " + t.info + " @ " + d.deadline + System.lineSeparator());
            }
        }
        fw.close();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>(); // change from string to task
        try {
            list = printFileContents(DATA_SAVE_FILE_DIR);
            print(new String[] { "Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.",
                    "Pai Kia Bot: Got ur input file, baik la", "Pai Kia Bot: how u want me to help u?" });
        } catch (FileNotFoundException e) {
            print(new String[] { "Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.",
                    "Pai Kia Bot: I cannot find ur input file sia, could be becuz",
                    "1) u never create file because u r new user, in that case, just continue using this program and i "
                            + "will create the folder and save file for u in the directory [root]/data",
                    "2) ur saveFile.txt is not in the correct directory, pls input bye and shift the file to [root]/data",
                    "3) ur input file is not named saveFile.txt, pls input 'bye' and rename ur input file",
                    "Pai Kia Bot: What help you want?" });
        } catch (InvalidArgException e) {
            print(e.getMessage());
        } catch (InvalidTypeException e) {
            print(e.getMessage());
        }

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
                    print(new String[] { "Paikia Bot: ok i just help u deleted this task -- " + toRemove.toString(),
                            "Paikia Bot: now u got " + list.size() + " item(s) in your list ah" });
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
        print("Pai Kia Bot: Leave so soon ah? Ok can limpeh help u save your list in [root]/data as saveFile.txt, i go sleep first");

        try {
            writeToFile(DATA_SAVE_FILE_DIR, list);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        sc.close();
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
<<<<<<< HEAD
        return "[D]" + super.toString() + " (" + this.deadline + ")";
=======
        return "[D]" + super.toString() + "(" + this.timeDisplay() + ")";
>>>>>>> branch-Level-8
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
<<<<<<< HEAD
        return "[E]" + super.toString() + " (" + this.date + ")";
=======
        return "[E]" + super.toString() + "(" + this.timeDisplay() + ")";
>>>>>>> branch-Level-8
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

class InvalidArgException extends MyDukeException {
    InvalidArgException(String s) {
        super(s);
    }
}

class InvalidTypeException extends MyDukeException {
    InvalidTypeException(String s) {
        super(s);
    }
}