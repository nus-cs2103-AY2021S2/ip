public class Ui {


    public static final String primer = "Hello";

    public void printStarter() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public String printStart() {
        String res = String.format("Hello! I'm Duke" + "\n" + "What can I do for " +
                        "you?\n");

        res += outHelp();
        return res;
    }

    public String outHelp() {
        final String commandTypes = String.format("Here are the list of all commands:" +
                " \n(Type help again to repeat these commands)\n\n" +
                "list - lists all existing tasks\n" +
                "todo [insert task] - creates a todo task\n" +
                "deadline [insert deadline] - creates a deadline\n" +
                "event [insert event] - creates an event\n" +
                "delete [insert index] - deletes event based on index\n" +
                "find [insert keyword] [search type(optional)] - finds tasks " +
                "corresponding\n to keyword\n" +
                "done [insert index] - marks task as done");
        return commandTypes;
    }

    public String outBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String outNotRecognized() {
        return ":( OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public String outInvalidDeadline() {
        return ":( OOPS!!! The description of a deadline " +
                "cannot be empty.";
    }

    public String outInvalidEvent() {
        return ":( OOPS!!! The description of an event cannot be empty.";
    }

    public String outInvalidTodo() {
        return ":( OOPS!!! The description of a todo cannot be empty.";
    }

    public String outInvalidDelete() {
        return ":( OOPS!!! Please ensure that you have correctly indicated the index " +
                "of the item you want to find.";
    }

    public String outInvalidFind() {
        return ":( OOPS!!! Please ensure that you have correctly indicated the " +
                "keyword of the task(s) you want to search for.";
    }


}
