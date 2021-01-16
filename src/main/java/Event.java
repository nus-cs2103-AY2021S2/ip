public class Event extends Task {
    protected String type = "E";
    protected StringBuilder date = new StringBuilder();

    public Event(String[] input) throws DukeException {
        super(input);
        if (input.length != 0) {
            for (int i = 0; i < input.length; i++) {
                if (input[i].equals("/at")) {
                    for (int j = i; j < input.length - 2; j++) {
                        this.date.append(input[j + 1] + " ");
                    }
                    this.date.append(input[input.length - 1]);
                    break;
                } else {
                    this.description.append(input[i] + " ");
                }
            }

        } else {
            throw new DukeException("☹ OOPS!!! Description of todo cannot be empty, please check!");
        }

    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(at: "
                + this.date.toString() + ")";
    }

}
