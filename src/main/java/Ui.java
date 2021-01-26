import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Handles the interaction the user and the program.
 */
class Ui {
    Scanner scanner;
    Ui(){
        scanner = new Scanner(System.in);
    }

    protected String getLine() throws NoSuchElementException {
        return scanner.nextLine();
    }

    /**
     * Generates the string representation of the introduction.
     *
     * @return String representing the introduction to Duke.
     */
    protected String getIntro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro ="Hello I'm\n" + logo +"\nWhat can I do for you?\n";

        return intro;
    }

    /**
     * Command that prints the list existing in TaskList.
     * (Only applicable if inputs are fed via file direction)
     *
     * @return String representing the error when no lines are inputted.
     */
    public String showNoMoreLinesError(){
        return "Error. No more lines detected. Exiting...";
    }
}
