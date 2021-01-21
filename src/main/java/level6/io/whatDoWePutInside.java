package level6.io;


import java.io.*;
import java.util.regex.*;
import java.util.stream.*;

/**
 * Input
 */
public class whatDoWePutInside { //public class for inputs
    //use buffer reader instead of scanner
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    //special patterns, again credits to stack exchange
    static Pattern painInTheAssEmptySpace = Pattern.compile("\\s+"); //no more " "
    static Pattern commandForSlashing = Pattern.compile("(?i)\\/((at)|(by)|(on))"); //credit to stack
    
    static String readInTheInputLine(){ //try catch is exception version of if and else
        try{ //try to read
            //if not
            return bufferedReader.readLine();
        }catch (Exception exception){//catch the exception
            //System.out.println("I/O exception occurred.");
            System.err.println("The input does not match the format");//err is better
            return "";
        }
    }

    protected String linezzzz; //declare string
    protected String[] tikam; //declare also string array
    //for those not proficient in malay, this means token
    
    public whatDoWePutInside(){ }

    public whatDoWePutInside theLinezzThatComesNext(){ //method to put in next line
        this.linezzzz = readInTheInputLine(); //read in the input line
        this.tikam = painInTheAssEmptySpace.split(this.linezzzz.trim()); //put in the empty space
        return this; //return it
    }

    //getter methods
    public String[] getTikam(){ //get the token
        this.tikam = this.tikam.length == 0 ? painInTheAssEmptySpace.split(this.linezzzz.trim())
                                            : this.tikam;
        return this.tikam; //return the token
    }
    
    public Stream<String> getTheStrx4DaTikam() { //return the stream
        return Stream.of(this.getTikam());
    }
    
    public String getDa1stTikam(){ //get teh first token
        //return this.tikam[1];
        return this.tikam[0]; //1 doesnt work
    }
    
    public String[] tikamNotNum1(){ //if we need not the first token
        return this.getTheStrx4DaTikam().skip(1).toArray(String[]::new);
        //return the corresponding stream
    }

    public String inputThatDontNeed1stTikam(){  //for inputs that do not require the 1st token
        return this.getTheStrx4DaTikam().skip(1).collect(Collectors.joining(""));
        //return the corresponding stream
    }
    
    public String getTheJuicyDetails(){ //get all the details we need
        return Stream.of(this.tikam).skip(1).takeWhile(commandForSlashing.asMatchPredicate().negate())
                .collect(Collectors.joining(" "));
        //stream magic
    }

    public String whatIsTheTime() { //get the temporal component
        return Stream.of(this.tikam).dropWhile(commandForSlashing.asMatchPredicate().negate()).skip(1)
                .collect(Collectors.joining(" "));
    }

    
    public void endItAll(){ //ending method
        try{ //we use this method to end the code
            bufferedReader.close();
        }catch (IOException exception){
            //System.out.println("I/O exception occurred.");
            System.err.println("Input does not match the format");
        }
    }
}