public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String tab = "     ";
        String line = "____________________________________________________________\n";
        System.out.println(tab + line
                + tab + "Hello! I'm Duke\n"
                + tab + "What can I do for you?\n"
                + tab + line);
    }
}
