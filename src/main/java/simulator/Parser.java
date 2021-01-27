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
            String[] content = input.split("\\s+");
            String command = content[0];
            result.add(command);
            StringBuilder sb = new StringBuilder();
            int pointer = 0;
            for (int i = 1; i < content.length; i++) {
                if (content[i].equals("/by") || content[i].equals("/at")) {
                    pointer = i;
                    break;
                } else {
                    if (i != content.length - 1) {
                        sb.append(content[i]);
                        sb.append(" ");
                    } else {
                        sb.append(content[i]);
                    }
                }
            }
            result.add(sb.toString());
            sb = new StringBuilder();
            if (command.equals("deadline") || command.equals("event")) {
                String duration;
                for (int i = pointer + 1; i < content.length; i++) {
                    sb.append(content[i]);
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
