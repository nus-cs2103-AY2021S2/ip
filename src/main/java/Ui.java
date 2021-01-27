public class Ui {
    String lines;
    String indentation;

    public Ui() {
        this.lines = "    ____________________________________________________________";
        this.indentation = "     ";
    }

    public void print(String text) {
        System.out.println(lines);
        System.out.print(indentation);
        System.out.println(text);
        System.out.println(lines);
    }

    public void print(String[] texts) {
        System.out.println(lines);
        for (String text : texts) {
            System.out.print(indentation);
            System.out.println(text);
        }
        System.out.println(lines);
    }

    public void printErr(String err) {
        System.out.println(lines);
        System.out.print(indentation);
        System.out.println("☹ OOPS!!! " + err);
        System.out.println(lines);
    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(lines);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(lines);
    }

    public void bye() {
        System.out.println(lines);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(lines);
    }

    public void ioException() {
        System.out.println("☹ OOPS!!! An uncorrectable error occurred");
    }
}