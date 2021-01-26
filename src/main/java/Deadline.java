public class Deadline extends Task {

    private String end;

    private Deadline (String name, String end) {
        super(name);
        this.end = end;
    }
    
    public Deadline (boolean done, String name, String end) { 
        super(done, name);
        this.end = end;
    }

    public static Deadline createDeadline (String str) throws ChatException {
        if (!str.startsWith("deadline")) {
            //i.e. event
            throw new ChatException("wrong instruction for deadline\n" +
                    "Please input with this format:\n" +
                    "deadline [name] /by [end date/time]");
        } else if (str.strip().equals("deadline")) {
            //i.e. deadline
            //i.e. deadline(followed by one or more empty spaces)
            throw new ChatException("deadline name and end date/time missing\n" +
                    "Please input with this format:\n" +
                    "deadline [name] /by [end date/time]");
        } else if (!str.startsWith("deadline ")) {
            //i.e. deadlinereturn book
            throw new ChatException("no spacing after deadline\n" +
                    "Please input with this format:\n" +
                    "deadline [name] /by [end date/time]");
        }

        String tempStr = str.replace("deadline ", "").stripLeading();
        if (tempStr.startsWith("/by")) {
            //i.e. deadline /by
            //i.e. deadline /by night
            throw new ChatException("deadline name missing\n" +
                    "Please input with this format:\n" +
                    "deadline [name] /by [end date/time]");
        } else if (!tempStr.contains("/by")) {
            //i.e. deadline return book
            //i.e. deadline return book tmr
            throw new ChatException("/by missing\n" +
                    "Please input with this format:\n" +
                    "deadline [name] /by [end date/time]");
        } else if (!tempStr.contains(" /by ")) {
            //i.e. deadline return book/by
            //i.e. deadline return book/by tmr
            //i.e. deadline return book /bytmr
            //i.e. deadline return book/bytmr
            throw new ChatException("missing spaces before or after /by\n" +
                    "Please input with this format:\n" +
                    "deadline [name] /by [end date/time]");
        }

        String[] tempArr = tempStr.split(" /by ");
        if (tempArr.length < 2) {
            //i.e. deadline return book /by
            throw new ChatException("missing end date/time\n" +
                    "Please input with this format:\n" +
                    "deadline [name] /by [end date/time]");
        } else if (tempArr.length > 2 || tempArr[1].contains("/by")) {
            //i.e. deadline return book /by /by night
            throw new ChatException("not allowed to have more than 1 ' /by '\n" +
                    "Please input with this format:\n" +
                    "deadline [name] /by [end date/time]");
        } else {
            return new Deadline(tempArr[0].strip(), tempArr[1].strip());
        }
    }

    public String getEnd() {
        return this.end;
    }

    public String allParameterStr() {
        return String.format("D,%s,%s,%s", this.getDone(), this.getName(), this.getEnd());
    }
    
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getEnd());
    }

}
