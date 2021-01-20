public class Parser {
    private String typeOfTask;
    private String description;
    private String time;

    Parser() {
        this.typeOfTask = "";
        this.description = "";
        this.time = "";
    }

    Parser(String typeOfTask, String description, String time) {
        this.typeOfTask = typeOfTask;
        this.description = description;
        this.time = time;
    }

    public Parser parse(String input) {
        String[] inputSplit = input.split(" ", 2);
        typeOfTask = inputSplit[0];
        if (inputSplit.length >= 2) {
            if (inputSplit[1].equals("")) {
            } else {
                if (inputSplit[1].split("/by").length == 2) {
                    String[] descSplit = inputSplit[1].split("/by ");
                    description = descSplit[0];
                    time = descSplit[1];
                } else if (inputSplit[1].split("/at").length == 2) {
                    String[] descSplit = inputSplit[1].split("/at ");
                    description = descSplit[0];
                    time = descSplit[1];
                } else {
                    description = inputSplit[1];
                }
            }
        }
        return new Parser(typeOfTask, description, time);
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "|" + typeOfTask + "|" + description + "|" + time + "|";
    }
}
