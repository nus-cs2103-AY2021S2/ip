package simulator;

import java.util.ArrayList;

import ui.Ui;

/**
 * Class <code>Parser</code> deals with making sense of the user command.
 * Contains method <code>parseInput</code>.
 */
public class Parser {

    /**
     * Returns an <code>ArrayList</code> of strings that contains the parsed <code>input</code>.
     *
     * @param input input parsed.
     * @return list of parsed input.
     */
    public ArrayList<String> parseInput(String input) {
        ArrayList<String> parsedOutput = new ArrayList<>();
        try {
            String[] contents = input.split("\\s+");
            String command = contents[0];

            parsedOutput.add(command);
            parsedOutput.add(getDescription(contents));
            if (command.equals("event") || command.equals("deadline")) {
                parsedOutput.add(getDateAndTime(contents));
            }
        } catch (Exception err) {
            Ui.printBox("☹ OOPS!!! Incorrect input, please check!");
        }
        return parsedOutput;
    }

    /**
     * Gets the task description from the specified <code>content</code>.
     * @param contents input from user.
     * @return String of task description.
     */
    public String getDescription(String[] contents) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < contents.length; i++) {
            if (contents[i].equals("/by") || contents[i].equals("/at")) {
                break;
            } else {
                if (i != contents.length - 1) {
                    sb.append(contents[i]);
                    sb.append(" ");
                } else {
                    sb.append(contents[i]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Gets the task date and time from the specified <code>content</code>.
     * @param contents input from user.
     * @return String of task date and time.
     */
    public String getDateAndTime(String[] contents) {
        StringBuilder sb = new StringBuilder();
        for (int i = contents.length - 1; i >= 0; i--) {
            if (contents[i - 1].equals("/by") || contents[i - 1].equals("/at")) {
                sb.insert(0, contents[i]);
                break;
            } else {
                sb.insert(0, contents[i]);
                sb.insert(0, " ");
            }
        }
        return sb.toString();
    }

}
