package main.java;

public abstract class Manager {

    public String horizontalLine(){
        return "    __________________________________________________________________________";
    }

    public String indentedString(String input){
        return "     " + input;
    }

    public String defaultFormatting(String input){
        return horizontalLine() + '\n' + indentedString(input) + '\n' + horizontalLine();
    }
}
