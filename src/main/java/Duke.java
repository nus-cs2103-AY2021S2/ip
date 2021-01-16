public class Duke {
    private static String LOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String EXITCOMMAND = "bye";

    public static void main(String[] args) {
        IO.printBotMessage("Hello from\n" + LOGO +"What can I do for you?");

        while(true){
            String reply = IO.readLine();
            if(reply.equals(EXITCOMMAND)) {
                break;
            }
            IO.printBotMessage(reply);
        }
        IO.printBotMessage("Bye. Hope to see you again soon!");
    }
}
