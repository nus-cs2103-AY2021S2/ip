import java.io.IOException;

/*
Need to address:
- IO exception in process_bye
- making task an abstract class
- upload from hard drive default expression
- declared get_date abstract even though todo doesn't use it
- support different date formats and time also
- split the code between the classes better
- Inconsistencies in exception classes
- default in switch-case is returning a random todo class -rectify
 */

public class Duke {
    public static void main(String[] args) throws IOException {
        Ui.greet();
        TaskList.checkFileFolderSpecifications();
        Parser.parse();
    }
}
