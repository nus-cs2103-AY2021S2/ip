package level6;

import level6.exception.exceptionsForLevel6;
import level6.io.*;

public class Duke{
    static whatDoWePutInside reader; //reader goes inside
    static whatGoesOutside writer; //writer writes output from chatbot

    public static boolean mainProgramLoopBody(){ //as long as we dont say bye
        boolean terminatingCondition = false; //by default the terminatinf condition is false
        Instruction instruction; //create instructions
        
        do {
            instruction = Instruction.whatIsTheCommd //get teh command for instruction
                    (reader.theLinezzThatComesNext(). //by reading in the line
                                    getDa1stTikam()); //and getting the corresponding token
            terminatingCondition = instruction.equals(Instruction.BYE); //consider if terminatinf, say bye
            switch (instruction){

                // if we say ToDo
                case TODO:
                    try{ ///if we dont need the 1st one
                        Task.putInANewTask(new Todo(reader.inputThatDontNeed1stTikam()));
                    }
                    catch (exceptionsForLevel6 exceptionsForLevel6){ //exception for corner case
                        System.err.println("todo detail cannot be empty.");
                    }
                    break; //end it

                //if we say deadline
                //what is in a deadline, the details and time
                case DEADLINE:
                    Task.putInANewTask(new Deadline //create a new deadline
                            (reader.getTheJuicyDetails(), reader.whatIsTheTime()));
                    break; //put in details for deadline and time of deadline

                //if we say event
                case EVENT:
                    Task.putInANewTask(new Event //whats is in an event, the detaisk and time
                            (reader.getTheJuicyDetails(), reader.whatIsTheTime()));
                    break; //put in deatils and time and break

                //if we say list
                case LIST:
                    Task.xsOfTasks(); //just print it out is sufficient
                    break;

                //if we are done
                //we need the input(what is done) and mark the list that we've done it
                case DONE:
                    Task.getTaskAtPosition(Integer.parseInt(reader.inputThatDontNeed1stTikam())).markTaskAsCompleted();
                    break; //get what we need, change what we need and break

                //if we want to delete
                case DELETE:
                    Task.atThePositionDeleteTask //do a delete task
                            (Integer.parseInt(reader.inputThatDontNeed1stTikam())); //get where

                case EMPTY:
                    continue;

                case INVALID:
                    try {
                        throw new exceptionsForLevel6();
                    }
                    catch (exceptionsForLevel6 exceptionsForLevel6) {
                        writer.tellMeSomething("Invalid command; try again.");
                    }
                    //if we say bye
                case BYE:
                default:
                    break;
            }
            
        } while (!terminatingCondition);
        return terminatingCondition;
    }

    public static void endgame(){ //clean up and abstract the code
        writer.sayGoodBye();
        reader.endItAll();
        writer.endItAll();
        //System.exit(1);
        System.exit(0);
    }
    
    public static void main(String[] args){
        //initialise var
        reader = new whatDoWePutInside(); //what goes in the reader
        writer = new whatGoesOutside(); //what comes out from the writer
        writer.helloWorldTime(); //greeting msg
        Task.listMakingMethod(); //creates list of task
        if (!mainProgramLoopBody()) endgame(); //keep running untill endgame
    }
}
