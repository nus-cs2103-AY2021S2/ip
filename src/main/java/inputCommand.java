public class inputCommand {
    private final String command;
    private final String argument;
    private final String date;

    private final String line = "\n____________________________________________________________";

    enum predefinedCommand {
        list,
        bye,
        done,
        todo,
        deadline,
        event,
        error
    }

    public inputCommand() {
        this.command = "";
        this.date = null;
        this.argument = "";
    }

    public inputCommand(String in) throws DukeException.NoDescriptionException {
        String tempDate = "";
        String tempCommand = "";
        String[] result = in.split("\\s");
        String tempArg = "";
        try {
            if (result[0].equals("done")) {
                tempCommand = result[0];
                tempArg = result[1];
                tempDate = null;
            } else if (result[0].equals("todo")) {
                String temp = in.substring(in.indexOf(" ") + 1);
                tempArg = temp;
                if (temp.equals("todo")) {
                    throw new DukeException.NoDescriptionException(result[0]);
                } else {
                    tempCommand = result[0];
                }
                tempDate = null;
            } else if (result[0].equals("deadline") || result[0].equals("event")) {
                String firstParam = in.substring(in.indexOf("/") + 1);
                if (firstParam.equals("deadline") || firstParam.equals("event")) {
                    tempDate = null;
                    throw new DukeException.NoDescriptionException(result[0]);
                } else {
                    int dateIndex = Math.max(firstParam.indexOf("by "), firstParam.indexOf("at "));
                    if (dateIndex == -1) {
                        tempDate = null;
                        throw new DukeException.NoDescriptionException(result[0]);
                    } else {
                        tempCommand = result[0];
                        tempDate = firstParam.substring(dateIndex + 1);
                        firstParam = in.substring(in.indexOf(" ") + 1);
                        tempArg = firstParam.substring(0, firstParam.indexOf("/") - 1);
                    }
                }
            } else {
                tempDate = null;
                throw new DukeException.UnknownCommandException();
            }
        }catch(DukeException ex){
            tempCommand = "error";
            tempArg = ex.getMessage();
//            System.out.println(tempArg);
        }
        this.command = tempCommand;
        this.argument = tempArg;
        this.date = tempDate;
    }

    public String getCommand() {
        return this.command;
    }

    public String getArgument() {
        return this.argument;
    }

    public String getDate() {
        return this.argument;
    }

    public String print(lists inputList) {
        predefinedCommand switchVal = predefinedCommand.valueOf(this.command);
        switch (switchVal) {
            case bye:
                return "Bye. Hope to see you again soon!";
            case list:
                String initStr = "Here are the tasks in your list:";
                for (int i = 0; i < inputList.getDukeList().size(); i++) {
                    initStr += "\n" + ((i + 1) + "." + inputList.getDukeList().get(i));
                }
                return initStr + line;
            case done:
                inputList.updateItemMutable(Integer.parseInt(this.argument));
                return "Nice! I've marked this task as done: \n" + inputList.getDukeList().get(Integer.parseInt(this.argument) - 1) + line;
            case event:
                event newEvent = new event(this.argument, this.date);
                inputList.addCommandMutable(newEvent);
                return printPredefinedMessage(newEvent.toString(), inputList);
            case deadline:
                deadline newDeadline = new deadline(this.argument, this.date);
                inputList.addCommandMutable(newDeadline);
                return printPredefinedMessage(newDeadline.toString(), inputList);
            case todo:
                todo newTodo = new todo(this.argument);
                inputList.addCommandMutable(newTodo);
                return printPredefinedMessage(newTodo.toString(), inputList);
            case error:
                return this.argument;
        }
        return "";
    }

    public String printPredefinedMessage(String typeOfTask, lists inputList) {
        return "Got it. I've added this task: \n" + typeOfTask + "\nNow you have " + inputList.getDukeList().size() + " tasks in the list" + line;
    }
}
