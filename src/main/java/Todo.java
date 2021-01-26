public class Todo extends Task {

    private Todo (String name) {
        super(name);
    }
    
    public Todo (boolean done, String name) {
        super(done, name);
    }

    public static Todo createTodo (String str) throws ChatException {
        if (!str.startsWith("todo")) {
            //i.e. deadline
            throw new ChatException("wrong instruction for todo\n" +
                    "Please input with this format:\n" +
                    "todo [name]");
        } else if (str.strip().equals("todo")) {
            //i.e. todo
            //i.e. todo(followed by one or more empty spaces)
            throw new ChatException("todo name missing\n" +
                    "Please input with this format:\n" +
                    "todo [name]");
        } else if (!str.startsWith("todo ")) {
            //i.e. todoread book
            throw new ChatException("no spacing after todo\n" +
                    "Please input with this format:\n" +
                    "todo [name]");
        } else {
            return new Todo(str.replace("todo ", "").strip());
        }
    }

    public String allParameterStr() { 
        return String.format("T,%s,%s", this.getDone(), this.getName());
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
