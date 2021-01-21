package level6;

public class Deadline extends Task{
    //var declaration
    String stringForBY;
    private Deadline(){ //subclass things
        super();
    }

    Deadline(String byMessage){ //constructor wth byMessage as input
        this();
        this.stringForBY = byMessage;
    }

    public Deadline(String detailsNeeded, String byMessage) { //constructor
        //this constructor takes in 2 params
        this(byMessage);
        this.detailsOfTheMessage = detailsNeeded;
    }
    
    @Override
    public char forIconGetWhatIsTaskType(){ //get teh icon we need
        return 'D'; //specified.  can be changed if needed
    }
    
    @Override
    public String toString(){ //toString methof
        return String.format("[%c]%s (by: %s)", this.forIconGetWhatIsTaskType(), super.toString(),
                this.stringForBY);
    }
}