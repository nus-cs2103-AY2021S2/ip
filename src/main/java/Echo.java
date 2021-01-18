package main.java;

public class Echo {

    public Echo(){

    }
    public String DukeResponse(String input){
        return horizontalLine() + '\n' + indentedString(input) + '\n' + horizontalLine();
    }

    public String horizontalLine(){
        return "    __________________________________________________________________________";
    }

    public String indentedString(String input){
        return "     " + input;
    }
}
