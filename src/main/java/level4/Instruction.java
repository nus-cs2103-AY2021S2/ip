package level4;

/**
 * A possible command to the Duke program.
 */
public enum Instruction
{
    BYE,                 //from level 1
    LIST,                //from level 2
    DONE,                //from level 3
    TODO,EVENT,DEADLINE, //from level 4
        BY,AT,ON,        //specifications of level 4
    EMPTY, INVALID;      //from level 5

    public static Instruction whatIsTheCommd(String input)
    {
        for (Instruction instruction : values()) //for every inst thats in the set of values
            //if it is what we want
            if (input.equalsIgnoreCase(instruction.toString()))
                return instruction; //output accordingly
            //if not
            else if (input.isBlank())
                return EMPTY; //then we must go to this case
        //and if all else fails, the is the master control
        return INVALID;
    }
}
