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
        ArrayList<String> parsedOuput = new ArrayList<>();
        try {
            String[] contents = input.split("\\s+");
            String command = contents[0];
            parsedOuput.add(command);
            StringBuilder sb = new StringBuilder();
            // Initialise a pointer
            int pointer = 0;
            // Appends the date into the string builder
            for (int i = 1; i < contents.length; i++) {
                // Checks whether the input is an event or deadline
                if (contents[i].equals("/by") || contents[i].equals("/at")) {
                    pointer = i;
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
            parsedOuput.add(sb.toString());
            // Reset the string builder to append the date.
            sb = new StringBuilder();
            if (command.equals("deadline") || command.equals("event")) {
                String duration;
                for (int i = pointer + 1; i < contents.length; i++) {
                    sb.append(contents[i]);
                    sb.append(" ");
                }
                parsedOuput.add(sb.toString());
            }
        } catch (Exception err) {
            Ui.printBox("☹ OOPS!!! Incorrect input, please check!");
        }
        return parsedOuput;
    }

}
