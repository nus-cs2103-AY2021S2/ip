public class Parser {
    String command;
    String description;
    String deadline;

    public Parser() {
        this.command = "";
        this.description = "";
        this.deadline = "";

    }

    public void processInput(String userInput) {
        String[] starr = userInput.split(" ");
        if (starr.length == 1) {
            this.command = starr[0];
        } else {
            this.command = starr[0];
            switch (starr[0]) {
                case "done":
                    this.description = starr[1];
                    break;
                case "todo":
                    String m = "";
                    for (int i = 1; i < starr.length; i++) {
                        m = m.concat(starr[i]);
                        if (i != starr.length - 1) {
                            m = m.concat(" ");
                        }
                    }
                    this.description = m;
                    break;
                case "deadline":
                case "event":
                    String[] arr = userInput.split("/");
                    this.deadline = arr[1];
                    String message = "";
                    String[] s = arr[0].split(" ");
                    for (int i = 1; i < s.length; i++) {
                        message = message.concat(s[i]);
                        if (i != s.length - 1) {
                            message = message.concat(" ");
                        }

                    }
                    this.description = message;
                    break;
            }
        }
    }

    public boolean isEquals(String type) {
        return command.equals(type);
    }
}
