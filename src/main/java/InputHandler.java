public class InputHandler {
    private final String input;

    public InputHandler(String input) {
        this.input = input;
    }

    public String getAction() {
        return this.input.contains(" ") ? this.input.split(" ")[0] : this.input;
    }

    public String getDescription() {
        if (this.input.contains(" ")) {
            String description = this.input.split(" ", 2)[1];

            if (description.contains("/at")) {
                return description.split("/at")[0].trim();
            } else if (description.contains("/by")) {
                return description.split("/by")[0].trim();
            } else {
                return description;
            }
        }

        return "";
    }

    public String getAt() {
        if (this.getAction().equals("event")) {
            return this.input.split("/at")[1].trim();
        }
        return "";
    }

    public String getBy() {
        if (this.getAction().equals("deadline")) {
            return this.input.split("/by")[1].trim();
        }
        return "";
    }
}
