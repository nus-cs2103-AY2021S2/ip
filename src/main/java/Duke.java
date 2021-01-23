public class Duke {
    static String horizontalLine = "\t--------------------------------\n";

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        String greeting = "\t  Hello! I'm Duke\n" + "\t  What can I do for you?\n";
        String byeMessage = "\t  Bye. Hope to see you again soon!\n";

        System.out.print(horizontalLine + greeting);
        Chatbot chatbot = new Chatbot(fileHandler.readFile("./data.txt"));
        System.out.println(horizontalLine);
        chatbot.execute();
        String saveMessage = fileHandler.updateFile(chatbot.getTaskList(), "./data.txt");
        System.out.println(horizontalLine + saveMessage + byeMessage + horizontalLine);

    }

}
