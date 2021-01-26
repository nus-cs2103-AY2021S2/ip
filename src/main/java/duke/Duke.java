package duke;

import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        
		chatBot();
    }

    public static void chatBot(){
		
		Ui ui = new Ui();
		ui.greeting();
		
		Storage st = new Storage("tasklist");
		TaskList tl = st.loadTaskList();
		
		ui.getInput(tl);
		
		st.saveTaskList(tl);
        
    }
	
	
}
