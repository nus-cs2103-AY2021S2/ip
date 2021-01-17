public class Duke {
    static String horizontalLine = "\t--------------------------------\n";

    public static void main(String[] args) {
        String logo = "\t   ____        _        \n"
                + "\t  |  _ \\ _   _| | _____ \n"
                + "\t  | | | | | | | |/ / _ \\\n"
                + "\t  | |_| | |_| |   <  __/\n"
                + "\t  |____/ \\__,_|_|\\_\\___|\n";
        String greeting = "\t  Hello! I'm Duke\n" + "\t  What can I do for you?\n";
        String byeMessage = "\t  Bye. Hope to see you again soon!\n";

        System.out.println(horizontalLine + logo + greeting + horizontalLine);
        Chatbot chatbot = new Chatbot();
        chatbot.execute();
        System.out.println(horizontalLine + byeMessage + horizontalLine);

    }

}
