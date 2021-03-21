/**
 * Main class which brings all the other classes together.
 */
public class Luna {

    Luna() {
        TaskList.checkFileFolderSpecifications();
    }

    /**
     * Parses the user input and returns the appropriate response.
     * @param input the user input.
     * @return Luna's response.
     */
    public String getResponse(String input) {
        return Parser.parse(input);
    }
}
