public class Event extends Task {
    private String date;
    private String description;

    public Event(String input) {
        super(input);
    }

    public Event(String date, String description) {
        super(description + " " + date);
        this.date = date;
        this.description = description;
    }

    public void fillAttributes() {
        if (hasValidDate()) {
            String[] command = input.split(" ");
            int index = findDate();
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
    public int findDate() {
        String[] command = input.split(" ");
        boolean found = false;
        int index = 0;
        while (!found && index < command.length) {
            if (command[index].equals("/at")) {
                found = true;
            } else {
                index++;
            }
        }
        return index;
    }

    public boolean hasValidDate() {
        String[] command = input.split(" ");
        if (this.findDate() == command.length) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String formatToSave() {
        String str = "E | ";
        if (done) {
            str += "X |";
        } else {
            str += "O |";
        }
        fillAttributes();
        str += description + " | at:" + date;
        return str;
    }

    @Override
    public String toString() {
        String str = "[E]";
        if (done) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        fillAttributes();
        str += description + " (at:" + date + ")";
        return str;
    }
}