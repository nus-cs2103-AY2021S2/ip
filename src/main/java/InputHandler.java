public class InputHandler {
    private final String input;

    public InputHandler(String input) {
        this.input = input;
    }

    public String getAction() {
        return (input + " ").split(" ")[0];
    }

    private String getRemainingTokens() {
        if (!input.contains(" ")) {
            return "";
        }
        return this.input.split(" ", 2)[1];
    }

    public String getDescription() {
        String remainingTokens = this.getRemainingTokens();
        if (remainingTokens.contains("/by")) {
            return remainingTokens.split("/by")[0].trim();
        } else if (remainingTokens.contains("/at")) {
            return remainingTokens.split("/at")[0].trim();
        } else {
            return remainingTokens;
        }
    }

    public String getBy() {
        String remainingTokens = this.getRemainingTokens();
        if (!remainingTokens.contains("/by")) {
            return "";
        }
        return remainingTokens.split("/by", 2)[1].trim();
    }

    public String getAt() {
        String remainingTokens = this.getRemainingTokens();
        if (!remainingTokens.contains("/at")) {
            return "";
        }
        return remainingTokens.split("/at", 2)[1].trim();
    }
}
