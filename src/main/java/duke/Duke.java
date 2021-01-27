package duke;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        
        startChatBot();
    }
    
    /**
    * This method initiates the chatbot of the Duke program. It call upon various 
    * classes responsibile for different functionalities of Duke.
    */
    public static void startChatBot(){
        
        Ui ui = new Ui();
        ui.greeting();
        
        Storage st = new Storage("tasklist");
        TaskList tl = st.loadTaskList();
        
        ui.getInput(tl);
        
        st.saveTaskList(tl);

    }
    
    
}
