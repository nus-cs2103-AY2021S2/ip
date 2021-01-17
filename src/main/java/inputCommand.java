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

    public inputCommand(String in) {
        String[] result = in.split("\\s");
        String msg = "";
        if (result[0].equals("done")) {
            this.command = result[0];
            msg = result[1];
            this.date = null;
        } else if (result[0].equals("todo")) {
            String temp = in.substring(in.indexOf(" ") + 1);
            msg = temp;
            if (temp.equals("todo")) {
                this.command = "error";
            } else {
                this.command = result[0];
            }
            this.date = null;
        } else if (result[0].equals("deadline") || result[0].equals("event")) {
            String firstParam = in.substring(in.indexOf("/") + 1);
            if (firstParam.equals("deadline") || firstParam.equals("event")) {
                this.command = "error";
                this.date = null;
                msg = result[0];
            } else {
                int dateIndex = firstParam.indexOf("by ");
                System.out.println(dateIndex);
                if(dateIndex == -1){
                    this.command = "error";
                    this.date = null;
                    msg = result[0];
                }else {
                    this.command = result[0];
                    this.date = firstParam.substring(dateIndex + 1);
                    firstParam = in.substring(in.indexOf(" ") + 1);
                    msg = firstParam.substring(0, firstParam.indexOf("/") - 1);
                }
            }
        } else {
            this.command = "error";
            this.date = null;
        }
        if (this.command == "error" && !msg.isEmpty()) {
            this.argument = "☹ OOPS!!! The description of a " + msg + " cannot be empty.";
        } else if (this.command == "error" && msg.isEmpty()) {
            this.argument = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }else{
            this.argument = msg;
        }
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
