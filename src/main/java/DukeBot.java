import java.util.ArrayList;

public class DukeBot {
    private static final String BORDER = "\t___________________________________\n";
    private String output;
    private final ArrayList<String> taskList = new ArrayList<>();

    public DukeBot() {
        greeting();
    }

    public void greeting() {
        output = BORDER + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n" + BORDER;
        System.out.println(output);
    }

    public boolean echo(String input) {
        boolean continueInput = true;

        switch (input) {
            case "bye":
                output = BORDER + "\t" + " Bye. Hope to see you again soon!\n" + BORDER;
                continueInput = false;
                break;
            case "list":
                output = retrieveList(taskList);
                break;
            default:
                output = BORDER + "\t added: " + input + "\n" + BORDER;
                taskList.add(input);
                break;
        }
        System.out.println(output);
        return continueInput;
    }

    public String retrieveList(ArrayList<String> taskList) {
        StringBuilder str = new StringBuilder(BORDER);

        for (int num = 1; num <= taskList.size(); num++) {
            str.append("\t ").append(num).append(". ").append(taskList.get(num - 1)).append("\n");
        }
        str.append(BORDER);
        return str.toString();
    }
}