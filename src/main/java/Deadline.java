public class Deadline extends Task {
    protected String type = "D";
    protected StringBuilder date = new StringBuilder();

    public Deadline(String[] input) {
        super(input);
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("/by")) {
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
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(by: "
                + this.date.toString() + ")";
    }
}
