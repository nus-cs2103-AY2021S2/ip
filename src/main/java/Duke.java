import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws TodoException, DeadlineException, EventException, UnknownException {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        while (true) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).toString());
                }
            } else if (command.contains("todo")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new TodoException(":( OOPS!!! The description of a todo cannot be empty.");
                }
                System.out.println("Got it. I've added this task:");
                String description = "";
                for (int i = 1; i < length; i++) {
                    if (i + 1 != length) {
                        description += line[i] + " ";
                    } else {
                        description += line[i];
                    }
                }
                Todo todo = new Todo(description);
                tasks.add(todo);
                System.out.println(todo.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.contains("deadline")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new DeadlineException(":( OOPS!!! The description of a deadline cannot be empty.");
                }
                System.out.println("Got it. I've added this task:");
                String description = "";
                String date = "";
                boolean isDesc = true;
                for (int i = 1; i < length; i++) {
                    if (line[i].equals("/by")) {
                        isDesc = false;
                    } else if (i + 1 == length) {
                        if (isDesc) {
                            description += line[i];
                        } else {
                            date += line[i];
                        }
                    } else {
                        if (isDesc) {
                            description += line[i] + " ";
                        } else {
                            date += line[i] + " ";
                        }
                    }
                }
                Deadline deadline = new Deadline(description, date);
                tasks.add(deadline);
                System.out.println(deadline.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.contains("event")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new EventException(":( OOPS!!! The description of a event cannot be empty.");
                }
                System.out.println("Got it. I've added this task:");
                String description = "";
                String time = "";
                boolean isDesc = true;
                for (int i = 1; i < length; i++) {
                    if (line[i].equals("/at")) {
                        isDesc = false;
                    } else if (i + 1 == length) {
                        if (isDesc) {
                            description += line[i];
                        } else {
                            time += line[i];
                        }
                    } else {
                        if (isDesc) {
                            description += line[i] + " ";
                        } else {
                            time += line[i] + " ";
                        }
                    }
                }
                Event event = new Event(description, time);
                tasks.add(event);
                System.out.println(event.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.contains("done")) {
                String[] line = command.split(" ");
                int number = Integer.parseInt(line[1]) - 1;
                tasks.get(number).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(number).toString());
            } else if (command.contains("delete")) {
                System.out.println("Noted. I've removed this task:");
                String[] line = command.split(" ");
                int number = Integer.parseInt(line[1]) - 1;
                Task removedTask = tasks.remove(number);
                System.out.println(removedTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else {
                throw new UnknownException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        System.exit(0);
    }
}

class Task {
    private String description;
    private Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.date + ")";
    }
}

class Event extends Task {
    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time + ")";
    }
}

class DukeException extends Exception {
    public DukeException(String error) {
        super(error);
    }
}

class TodoException extends DukeException {
    public TodoException(String error) {
        super(error);
    }
}

class DeadlineException extends DukeException {
    public DeadlineException(String error) {
        super(error);
    }
}

class EventException extends DukeException {
    public EventException(String error) {
        super(error);
    }
}

class UnknownException extends DukeException {
    public UnknownException(String error) {
        super(error);
    }
}