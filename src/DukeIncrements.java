import java.util.Scanner;

public class DukeIncrements {
    public static void main(String[] args) throws DukeException {
        System.out.println("yo im Duke!");
        System.out.println("what can i do for ya ;)");

        String line = ":) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)";
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        int count = 0;
        Task[] arr = new Task[100];
        try {
            while (sc.hasNext()) {
                String input = sc.nextLine();
                input = input.trim();
                String[] str = input.split(" ", 2);

                if (input.equalsIgnoreCase("todo") ||
                        input.equalsIgnoreCase("event") ||
                            input.equalsIgnoreCase("deadline")) {
                    throw new DukeException("there is nothing to do!");
                }

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(line);
                    System.out.println("sayonara nerd! c ya soon ;)");
                    System.out.println(line);

                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println(line);
                    System.out.println("get to work! these are the tasks in your list: ");
                    for (int i = 0; i < count; i++) {
                        System.out.println(i + 1 + "." + arr[i].toString());
                    }
                    System.out.println(line);

                } else if (str[0].equalsIgnoreCase("done")) {
                    System.out.println(line);
                    int taskNum = Integer.parseInt(str[1]);
                    Task temp = arr[taskNum - 1];
                    temp.markDone();
                    System.out.println("cool! this task is marked as done:");
                    System.out.println(temp.toString());
                    System.out.println(line);

                } else if (str[0].equalsIgnoreCase("todo")) {
                    System.out.println(line);
                    ToDo tempT = new ToDo(str[1]);
                    arr[count] = tempT;
                    System.out.println("ok! i've added this task:");
                    System.out.println(tempT.toString());
                    count++;
                    System.out.println("you have " + count + " tasks in your list! keep working!");
                    System.out.println(line);

                } else if (str[0].equalsIgnoreCase("event")) {
                    System.out.println(line);
                    String[] strE = str[1].split("/at", 2);
                    Event tempE = new Event(strE[0], strE[1]);
                    arr[count] = tempE;
                    System.out.println("ok! i've added this task:");
                    System.out.println(tempE.toString());
                    count++;
                    System.out.println("you have " + count + " tasks in your list! keep working!");
                    System.out.println(line);

                } else if (str[0].equalsIgnoreCase("deadline")) {
                    System.out.println(line);
                    String[] strD = str[1].split("/by", 2);
                    Deadline tempD = new Deadline(strD[0], strD[1]);
                    arr[count] = tempD;
                    System.out.println("ok! i've added this task:");
                    System.out.println(tempD.toString());
                    count++;
                    System.out.println("you have " + count + " tasks in your list! keep working!");
                    System.out.println(line);

                } else {
                    throw new DukeException("invalid input! start with todo/event/deadline");
                }
            }
        }
        catch (DukeException e){
            System.out.println(e);
        }
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatus() {
        return (isDone ? "\u2718" : " ");
    }

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}

class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Event extends Task {
    protected String at;
    public Event (String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}