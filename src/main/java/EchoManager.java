package main.java;

public class EchoManager extends Manager {

    public EchoManager(){

    }
    public String DukeResponse(String input){
        return horizontalLine() + '\n' + indentedString(input) + '\n' + horizontalLine();
    }

}
