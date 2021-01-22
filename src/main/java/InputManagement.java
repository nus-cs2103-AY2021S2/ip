import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManagement {
    public Scanner sc;

    public InputManagement(Scanner sc) {
        this.sc = sc;
    }

    public void scannerNextLine() {
        this.sc.nextLine();
    }

    public void closeScanner() {
        this.sc.close();
    }

    /**
     * Used to obtain the first one-word command from input line.
     * @return command - command for Meme Man
     */
    public String getInputCommand() {
        String command = this.sc.next();
        return command;
    }

    /**
     * Used to obtain the task description from remainder of input line
     * @return taskDescription - Description used to create task
     */
    public String getInputDescription() {
        String taskDescription = this.sc.nextLine();
        return taskDescription; //Note: Every task description comes with a space
    }

    /**
     * Used to obtain number from a given description
     * @param description - The raw description
     * @return taskNumber - The number to find task in the array
     */
    public int getInputNumber(String description) {
        description = description.trim();
        if (description.isEmpty()) {
            throw new NoSuchElementException("Did you forget to put a number for the command you just typed in? Not stonks!");
        } else {
            try {
                Integer taskNumber = Integer.valueOf(description);
                return taskNumber;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Did you put something other than a number or did you put a number incorrectly? Not stonks!");
            }
        }
    }
}
