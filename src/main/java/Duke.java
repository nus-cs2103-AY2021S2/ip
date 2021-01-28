/*
Need to address:
- make a package
- add more j-unit tests
 */

/**
 * Main class which brings all the other classes together.
 */
public class Duke {
    /**
     * Program starts in the main method here.
     * @param args No user input in this main method.
     */
    public static void main(String[] args) {
        Ui.greet();
        TaskList.checkFileFolderSpecifications();
        Parser.parse();
    }
}
