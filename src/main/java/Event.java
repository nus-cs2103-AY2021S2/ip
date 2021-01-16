public class Event extends Task {
    protected String type = "E";
    protected StringBuilder date = new StringBuilder();

    public Event(String[] input) {
        super(input);
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("/at")) {
                for (int j = i; j < input.length - 1; j++) {
                    if (j == input.length - 2) {
                        this.date.append(input[j + 1]);
                    } else {
                        this.date.append(input[j + 1] + " ");
                    }
                }
                break;
            } else {
                this.description.append(input[i] + " ");
            }
        }

    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(at: "
                + this.date.toString() + ")";
    }

}
