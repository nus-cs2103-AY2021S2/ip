public class Duke {

    /**
     *  Greeter for Duke
    */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("---------------------------------------");
        System.out.println(logo);
        System.out.println("---------------------------------------");
        System.out.println("Hello! This is Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------");
    }

    public static void main(String[] args) {
        greet();
    }
}
