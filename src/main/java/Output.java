public class Output {
    public static String addLine() {
        return "    ----------------------------------------------------------------------------------------------------------------------------------------------";
    }

    public static void printWelcomeMsg() {
        System.out.println(addLine());
        System.out.println("    Hello! I'm Duke, your friendly chatbot.\n    What can I do for you today?\n");
        System.out.println("    1. list                                   Lists out all existing tasks                       (e.g. list)");
        System.out.println("    2. done <task number>                     Marks the specified task number as done/undone     (e.g. done 2)");
        System.out.println("    3. todo <todo message>                    Adds the specified todo to the list                (e.g. todo homework)");
        System.out.println("    4. deadline <deadline message> <date>     Adds the specified deadline to the list            (e.g. deadline return book /by 02/12/2020 4pm)");
        System.out.println("    5. event <event message> <date>           Adds the specified event to the list               (e.g. event project meeting /at 02/12/2020 4pm)");
        System.out.println("    6. delete <task number>                   Deletes the specified task number from the list    (e.g. delete 2");
        System.out.println("    7. bye                                    Terminate Duke                                     (e.g. bye)");
        System.out.println(addLine());
        System.out.println();
    }

    public static void printAddedTask(Task task, int numTasks) {
        System.out.println(addLine());
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + numTasks + " tasks in the list.");
        System.out.println(addLine());
        System.out.println();
    }

    public static void printDoneMsg(Task task) {
        System.out.println(addLine());
        if(task.getStatusIcon().equals("\u2713"))
            System.out.println("    Nice! I've marked this task as done:");
        else
            System.out.println("    Noted. I've marked this task as undone:");
        System.out.println("      " + task);
        System.out.println(addLine());
        System.out.println();
    }

    public static void printDeleteMsg(Task task, int numTasks) {
        System.out.println(addLine());
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + numTasks + " tasks in the list.");
        System.out.println(addLine());
        System.out.println();
    }

    public static void printByeMsg() {
        System.out.println(addLine() + "\n    Bye. Hope to see you again soon!\n" + addLine());
    }
}
