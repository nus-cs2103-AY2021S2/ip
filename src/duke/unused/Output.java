package duke.unused;

public abstract class Output{

    private String horizontalLine(){
        return "    ____________________________________________________________";
    }

    private String indentedString(String input){
        return "     " + input;
    }

    public String defaultFormatting(String input){
        return horizontalLine() + '\n' + indentedString(input) + '\n' + horizontalLine();
    }

    public String padSpaces(String input, int numOfSpacesToPad){
        String toreturn = "";

        for (int i = 0; i < numOfSpacesToPad; i++) {
            toreturn = toreturn + ' ';
        }

        return toreturn + input;
    }
}
