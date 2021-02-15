import java.io.File;

public class Ui {

    public static final String primer = "Hello";

    public void printStarter() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public String printStart() {
        return String.format("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public String outBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String outNotRecognized() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public String outInvalidDeadline() {
        return "☹ OOPS!!! The description of a deadline " +
                "cannot be empty.";
    }

    public String outInvalidEvent() {
        return "☹ OOPS!!! The description of an event cannot be empty.";
    }

    public String outInvalidTodo() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }

    public String outInvalidDelete() {
        return "OOPS!!! Please ensure that you have correctly indicated the index of " +
                "the item " +
                "you want to find.";
    }

    public String outInvalidFind() {
        return "OOPS!!! Please ensure that you have correctly indicated the keyword " +
                "of the task(s) you want to search for.";
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void commandNotRecognized() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void invalidDeadline() {
        System.out.println("☹ OOPS!!! The description of a deadline " +
                "cannot be empty.");
    }

    public void invalidEvent() {
        System.out.println("☹ OOPS!!! The description of an event cannot " +
                "be empty.");
    }

    public void invalidTodo() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

}
