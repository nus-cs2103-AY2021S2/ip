public class Todo extends Task {
    private String description;

    public Todo(String input) {
        super(input);
        String[] command = input.split(" ");
        String description = "";
        for (int i = 1; i < command.length; i++) {
            description += command[i] + " ";
        }
        this.description = description;
    }

    @Override
    public String formatToSave() {
        String str = "T | ";
        if (done) {
            str += "X |";
        } else {
            str += "O |";
        }
        str += " " + description;
        return str;
    }

    @Override
    public String toString() {
        String str = "[T]";
        if (done) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        str += " " + description;
        return str;
    }
}