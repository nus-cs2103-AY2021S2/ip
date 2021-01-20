public class Parser {
    String command;
    String description;
    String deadline;

    public Parser() {
        this.command = "";
        this.description = "";
        this.deadline = "";

    }

    public void processInput(String userInput, Duke bot) throws InvalidCommandException, InvalidArgumentException{
        String[] starr = userInput.split(" ", 2);
        if (starr.length == 1) {
            if(!(starr[0].equals("bye") || starr[0].equals("list"))) {
                if(starr[0].equals("todo") || starr[0].equals("done") ||
                        starr[0].equals("deadline") || starr[0].equals("event") || starr[0].equals("delete")) {
                    throw new InvalidCommandException("☹ OOPS!!! "
                            + "The description of a " + starr[0] + " cannot be empty.");
                } else {
                    throw new InvalidCommandException("☹ OOPS!!! " +
                            "I'm sorry, but I don't know what that means :-()");
                }
            }
            this.command = starr[0];
        } else {
            this.command = starr[0];
            switch (starr[0]) {
                case "done":
                case "delete":
                    try {
                        Integer.parseInt(starr[1]);
                    } catch(NumberFormatException ex) {
                        ex.printStackTrace();
                    }
                    if(Integer.parseInt(starr[1]) > bot.list.lst.size()) {
                        throw new InvalidArgumentException("Please input argument <= to " + bot.list.lst.size() + "!");
                    }
                    this.description = starr[1];
                    break;
                case "todo":
                    this.description = starr[1];
                    break;
                case "deadline":
                case "event":
                    String[] arr = starr[1].split("/");
                    if(arr.length == 1) {
                        throw new InvalidArgumentException("Please input task due date!");
                    }

                    String[] s = arr[0].split(" ", 2);

                    if(s.length == 1) {
                        throw new InvalidArgumentException("Please input task description!");
                    }

                    this.deadline = arr[1];
                    this.description = arr[0];
                    break;
            }
        }
    }

    public boolean isEquals(String type) {
        return command.equals(type);
    }
}
