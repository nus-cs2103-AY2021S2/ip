package level4;
import level4.IO.*;


public class Duke {
    static whatDoWePutInside reader; //reader goes inside
    static whatGoesOutside writer; //writer writes output from chatbot

    public static boolean mainProgramLoopBody() { //as long as we dont say bye
        boolean terminatingCondition = false; //by default the terminatinf condition is false
        Instruction instruction; //create instructions
        
        do {
            instruction = Instruction.whatIsTheCommd( //get teh command for instruction
                        reader.theLinezzThatComesNext(). //by reading in the line
                                getDa1stTikam()); //and getting the corresponding token

            terminatingCondition = instruction.equals(Instruction.BYE); //the terminating condition would be if we say bye

            switch (instruction)
            {
                // if we say todo
                case TODO:
                    Task.putInANewTask(new Todo(reader.inputThatDontNeed1stTikam())); //put todo
                    break; //and end it
                //if we say deadline
                //what is in a deadline, the details and time
                case DEADLINE: Task.putInANewTask(new Deadline(reader.getTheJuicyDetails(), //create a new deadline
                                                            reader.whatIsTheTime()));
                    break; //put in details for deadline and time of deadline
                //if we say event
                case EVENT:
                    //whats is in an event, the detaisk and time
                    Task.putInANewTask(new Event(reader.getTheJuicyDetails(), //create an event
                                                reader.whatIsTheTime()));
                    break;//put in deatils and time and break
                //if we say list
                case LIST:
                    //() -> Task.xsOfTasks()
                    Task.xsOfTasks(); //just print it out is sufficient
                    break;//end
                //if we are done
                //we need the input(what is done) and mark the list that we've done it
                case DONE:
                    Task.getTaskAtPosition(Integer.parseInt(reader.
                            inputThatDontNeed1stTikam())).markTaskAsCompleted();
                    break; //get what we need, change what we need and break
                //if invalid and empty, we just go on
                case EMPTY:
                case INVALID:
                    continue;
                case BYE: //if we say bye
                    System.out.println("good, farewell, adieu, till we meet again");
                    break;
                default: //to stop infinite loop in case i forgot anything
                    break;
            }
            
        } while (!terminatingCondition);
        return terminatingCondition;
    }

    public static void endgame() { //clean up and abstract the code
        writer.endingNote();
        writer.endItAll();
        reader.endItAll();
        //System.exit(1);
        System.exit(0);
    }
    
    public static void main(String[] args) {
        //initialise var
        reader = new whatDoWePutInside();  //what goes in the reader
        writer = new whatGoesOutside(); //what comes out from the writer
        writer.helloWorldTime();  //greeting msg
        Task.listMakingMethod();  //creates list of task
        if (!mainProgramLoopBody()) endgame(); //keep running untill endgame
    }
}
