public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        try {
            Storage.readTasks("./data/duke.txt");
        }
        catch (Exception e){
            Chatbox.chatbotDisplay("I can't find duke.txt file, so I create a new one for you.");
        }
        Chatbox.run();
        try {
          Storage.saveTasks("./data/duke.txt");
        }
        catch (Exception e){
          Chatbox.chatbotDisplay(e.getMessage());
        }


    }
}
