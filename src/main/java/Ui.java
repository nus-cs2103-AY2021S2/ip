public class Ui {
    String LINES = "    ____________________________________________________________";
    String INDENTATION = "     ";

    public void print(String text) {
        System.out.println(LINES);
        System.out.print(INDENTATION);
        System.out.println(text);
        System.out.println(LINES);
    }

    public void print(String[] texts) {
        System.out.println(LINES);
        for (String text : texts) {
            System.out.print(INDENTATION);
            System.out.println(text);
        }
        System.out.println(LINES);
    }

    public void printErr(String err) {
        System.out.println(LINES);
        System.out.print(INDENTATION);
        System.out.println("☹ OOPS!!! " + err);
        System.out.println(LINES);
    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINES);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(LINES);
    }

    public void bye() {
        System.out.println(LINES);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(LINES);
    }

    public void ioException() {
        System.out.println("☹ OOPS!!! An uncorrectable error occurred");
    }
}