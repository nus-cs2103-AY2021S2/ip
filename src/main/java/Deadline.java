public class Deadline extends Task {
    private String date;
    private String description;

    public Deadline(String input) {
        super(input);
    }

    public Deadline(String date, String description) {
        super(description + " " + date);
        this.date = date;
        this.description = description;
    }

    public void fillAttributes() {
        if (hasValidDeadline()) {
            String[] command = input.split(" ");
            int index = findDeadline();
            String descriptionStr = "";
            String dateStr = "";
            for (int i = 1; i < command.length; i++) {
                if (i < index) {
                    descriptionStr += " " + command[i];
                } else if (i > index) {
                    dateStr += " " + command[i];
                }
            }
            this.description = descriptionStr;
            this.date = dateStr;
        }
    }

    public int findDeadline() {
        String[] command = input.split(" ");
        boolean found = false;
        int index = 0;
        while (!found && index < command.length) {
            if (command[index].equals("/by")) {
                found = true;
            } else {
                index++;
            }
        }
        return index;
    }

    public boolean hasValidDeadline() {
        String[] command = input.split(" ");
        if (this.findDeadline() == command.length) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String formatToSave() {
        String str = "D | ";
        if (done) {
            str += "X |";
        } else {
            str += "O |";
        }
        fillAttributes();
        str += description + " | by:" + date;
        return str;
    }

    @Override
    public String toString() {
        String str = "[D]";
        if (done) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        fillAttributes();
        str += description + " (by:" + date + ")";
        return str;
    }
}