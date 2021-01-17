public class Duke {
    static String horizontalLine = "\t--------------------------------\n";

    public static void main(String[] args) {
        String greeting = "\t  Hello! I'm Duke\n" + "\t  What can I do for you?\n";
        String byeMessage = "\t  Bye. Hope to see you again soon!\n";

        System.out.println(horizontalLine + greeting + horizontalLine);
        Chatbot chatbot = new Chatbot();
        chatbot.execute();
        System.out.println(horizontalLine + byeMessage + horizontalLine);

    }

}
