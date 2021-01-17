public class inputCommand {
    private final String command;
    private final String argument;

    enum predefinedCommand {
        list,
        bye,
        done
    }

    public inputCommand() {
        this.command = "";
        this.argument = "";
    }

    public inputCommand(String in) {
        String[] result = in.split("\\s");
        if (result[0].equals("done")) {
            this.command = result[0];
            this.argument = result[1];
        }else{
            this.command = in;
            this.argument = "";
        }
    }

    public String getCommand() {
        return this.command;
    }

    public String print(lists inputList) {
        try {
            predefinedCommand switchVal = predefinedCommand.valueOf(this.command);
            switch (switchVal) {
                case bye:
                    return "Bye. Hope to see you again soon!";
                case list:
                    String initStr = "";
                    for (int i = 0; i < inputList.getDukeList().size(); i++) {
                        initStr += ((i + 1) + "." + inputList.getDukeList().get(i) + "\n");
                    }
                    return initStr;
                case done:
                    inputList.updateItemMutable(Integer.parseInt(this.argument));
                    return "Nice! I've marked this task as done: \n" + inputList.getDukeList().get(Integer.parseInt(this.argument) - 1);
            }
            return "";

        } catch (IllegalArgumentException ex) {
            inputList.addCommandMutable(this);
            return "added: " + this.command;
        }
    }
}
