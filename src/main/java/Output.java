public class Output {
    public static void addLine() {
        System.out.println("    ----------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void printWelcomeMsg() {
        addLine();
        System.out.println("    Hello! I'm Duke, your friendly chatbot.\n    What can I do for you today?\n");
        System.out.println("    1. list                                   Lists out all existing tasks                       (e.g. list)");
        System.out.println("    2. done <task number>                     Marks the specified task number as done/undone     (e.g. done 2)");
        System.out.println("    3. todo <todo message>                    Adds the specified todo to the list                (e.g. todo homework)");
        System.out.println("    4. deadline <deadline message> <date>     Adds the specified deadline to the list            (e.g. deadline return book /by Sunday)");
        System.out.println("    5. event <event message> <date>           Adds the specified event to the list               (e.g. event project meeting /at Mon 2-4pm");
        System.out.println("    6. delete <task number>                   Deletes the specified task number from the list    (e.g. delete 2");
        System.out.println("    7. bye                                    Terminate Duke                                     (e.g. bye)");
        addLine();
        System.out.println();
    }

    public static void printDoneMsg(String icon, String desc) {
        addLine();
        if(icon.equals("\u2713"))
            System.out.println("    Nice! I've marked this task as done:");
        else
            System.out.println("    Noted. I've marked this task as undone:");
        System.out.println("      " + "[" + icon + "] " + desc);
        addLine();
        System.out.println();
    }

    public static void printTodoMsg(String icon, String desc, int numTasks) {
        addLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + "[T]" + "[" + icon + "] " + desc);
        System.out.println("    Now you have " + numTasks + " tasks in the list.");
        addLine();
        System.out.println();
    }

    public static void printDeadlineMsg(String icon, String desc, int numTasks) {
        addLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + "[D]" + "[" + icon + "] " + desc);
        System.out.println("    Now you have " + numTasks + " tasks in the list.");
        addLine();
        System.out.println();
    }

    public static void printEventMsg(String icon, String desc, int numTasks) {
        addLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + "[E]" + "[" + icon + "] " + desc);
        System.out.println("    Now you have " + numTasks + " tasks in the list.");
        addLine();
        System.out.println();
    }

    public static void printAddedMsg(String input) {
        addLine();
        System.out.println("    added: " + input);
        addLine();
        System.out.println();
    }

    public static void printDeleteMsg(String icon, String desc, int numTasks) {
        addLine();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + "[E]" + "[" + icon + "] " + desc);
        System.out.println("    Now you have " + numTasks + " tasks in the list.");
        addLine();
        System.out.println();
    }

    public static void printByeMsg() {
        addLine();
        System.out.println("    Bye. Hope to see you again soon!");
        addLine();
    }

    public static void printErrorMsg(DukeException e) {
        addLine();
        System.out.println("    " + e.getMessage());
        addLine();
        System.out.println();
    }

    public static void printErrorMsg(Exception e) {
        addLine();
        System.out.println("    " + e.getMessage());
        addLine();
        System.out.println();
    }
}
