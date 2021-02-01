package main.java.duke.unused;

public class Echo {
    public Echo(){

    }
    public String dukeResponse (String input) {
        return horizontalLine() + '\n' + indentedString(input) + '\n' + horizontalLine();
    }

    private String horizontalLine() {
        return "    ____________________________________________________________";
    }

    private String indentedString(String input) {
        return "     " + input;
    }
}
