package simulator;

import ui.Ui;

import java.util.ArrayList;

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
        ArrayList<String> result = new ArrayList<>();
        try {
            String[] contents = input.split("\\s+");
            String command = contents[0];
            result.add(command);
            StringBuilder sb = new StringBuilder();
            int pointer = 0;

            for (int i = 1; i < contents.length; i++) {
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

            result.add(sb.toString());
            sb = new StringBuilder();

            if (command.equals("deadline") || command.equals("event")) {
                String duration;
                for (int i = pointer + 1; i < contents.length; i++) {
                    sb.append(contents[i]);
                    sb.append(" ");
                }
                result.add(sb.toString());
            }

        } catch (Exception err) {
            Ui.printBox("☹ OOPS!!! Incorrect input, please check!");
        }
        
        return result;
    }

}
