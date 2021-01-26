public class Parser {

    private String[] input;

    public Parser(String input) {
        this.input = input.split(" ");
    }

    public Parser newInput(String input) {
        return new Parser(input);
    }

    public String getCommand() {
        return input[0];
    }

    public int getIndexToModify() throws DukeException {

        String index = "";

        try {
            index = input[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("delete");
        }

        return Integer.parseInt(index) - 1;
    }

    public String getTaskDescription() {
        String output = "";

        for (int counter = 1; counter < input.length; counter++) {
            if (input[counter].startsWith("/")) {
                break;
            } else {
                output = output + " " + input[counter];
            }
        }

        return output;
    }

    public String getDate() {
        if (getCommand().equals("todo")) {
            return "Task has no date";
        } else {
            return input[input.length - 2];
        }
    }

    public String getTime() {
        if (getCommand().equals("todo")) {
            return "Task has no time";
        } else {
            return input[input.length - 1];
        }
    }
}
