import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.Exception;

class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
class InvalidCommandException extends DukeException {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}

class Task {
    public String name;
    public String checkBox;

    public Task(String name){
        this.name = name;
        this.checkBox = "[ ]";
    }

    public void completeTask(){
        this.checkBox = "[X]";
    }

    @Override
    public String toString(){
        return this.checkBox + " " + this.name;
    }
}

class ToDo extends Task {
    public String type;

    public ToDo (String name){
        super(name);
        this.type = "[T]";
    }

    @Override
    public String toString(){
        return this.type + this.checkBox + " " + this.name;
    }
}

class Deadline extends Task {
    public String type;
    public String date;

    public Deadline (String name, String date){
        super(name);
        this.date = date;
        this.type = "[D]";
    }

    @Override
    public String toString(){
        return this.type + this.checkBox + " " + this.name + " " + this.date;
    }
}

class Event extends Task {
    public String type;
    public String time;

    public Event(String name, String time){
        super(name);
        this.time = time;
        this.type = "[E]";
    }

    @Override
    public String toString(){
        return this.type + this.checkBox + " " + this.name + " " + this.time;
    }
}

public class Duke {
    public static Scanner input = new Scanner(System.in);
    public static List<Task> list = new ArrayList<>();

    public static void printList(){
        for(int i = 0; i < Duke.list.size(); i++){
            System.out.println((i+1) + ": " + Duke.list.get(i));
        }
    }

    public static String commandBuilder(String[] commandArray){
        String result = "";
        for(int i = 1; i < commandArray.length; i++){
            result += commandArray[i] + " ";
        }
        return result;
    }

    public static void main(String[] args) throws EmptyDescriptionException, InvalidCommandException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while(true) {
            String command = input.nextLine();
            if(command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                Duke.printList();
            } else if(command.contains("done")){
                String[] doneCommand = command.split(" ");
                Duke.list.get(Integer.parseInt(doneCommand[1]) - 1).completeTask();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(Duke.list.get(Integer.parseInt(doneCommand[1]) - 1));
            } else if(command.contains("todo")){
                String[] commandArray = command.split( " ");
                if(commandArray.length == 1){
                    throw new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
                }
                ToDo newTodo = new ToDo(commandBuilder(commandArray));
                Duke.list.add(newTodo);
                System.out.println("Got it. I've added this task: ");
                System.out.println(newTodo);
                System.out.println("Now you have " + Duke.list.size() + " tasks in the list.");
            } else if (command.contains("deadline")){
                String[] commandArray = command.split( " ");
                String[] commandArray2 = commandBuilder(commandArray).split("/");
                Deadline newDeadline = new Deadline(commandArray2[0], commandArray2[1]);
                Duke.list.add(newDeadline);
                System.out.println("Got it. I've added this task: ");
                System.out.println(newDeadline);
                System.out.println("Now you have " + Duke.list.size() + " tasks in the list.");
            } else if (command.contains("event")) {
                String[] commandArray = command.split(" ");
                String[] commandArray2 = commandBuilder(commandArray).split("/");
                Event newEvent = new Event(commandArray2[0], commandArray2[1]);
                Duke.list.add(newEvent);
                System.out.println("Got it. I've added this task: ");
                System.out.println(newEvent);
                System.out.println("Now you have " + Duke.list.size() + " tasks in the list.");
            } else if (command.contains("delete")) {
                String[] deleteCommand = command.split(" ");
                System.out.println("Noted. I've removed this task: ");
                System.out.println(Duke.list.get(Integer.parseInt(deleteCommand[1]) - 1));
                Duke.list.remove(Integer.valueOf(deleteCommand[1]) - 1);
            } else {
                throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        System.exit(0);
    }
}
