import java.io.IOException;

/*
Need to address:
- IO exception in process_bye
- making task an abstract class
- upload from hard drive default expression
- declared get_date abstract even though todo doesn't use it
- support different date formats and time also
 */

/**
 * Main class which brings all the other classes together.
 */
public class Duke {
    /**
     * Program starts in the main method here.
     * @param args No user input in this main method.
     * @throws IOException In case of input errors.
     */
    public static void main(String[] args) throws IOException {
        Ui.greet();
        TaskList.checkFileFolderSpecifications();
        Parser.parse();
    }
}
