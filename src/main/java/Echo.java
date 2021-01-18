package main.java;

public class Echo extends Task{

    public Echo(){

    }
    public String DukeResponse(String input){
        return horizontalLine() + '\n' + indentedString(input) + '\n' + horizontalLine();
    }

}
