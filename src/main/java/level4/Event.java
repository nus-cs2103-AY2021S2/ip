package level4;

public class Event extends Task {
    //declare var
    protected String howLongDuration; //how long
    protected String timeToEnd; //when we end
    protected String timeToStart; //when we start

    private Event(){
        super(); //sublass things
        this.timeToEnd = ""; //ednign
        this.timeToStart = ""; //starting
    }

    //constructor
    public Event(String detailsFromtheString, String howLongDuration){ //takes in 2 inputs
        // input the paticular details from string and and how long it is
        super(detailsFromtheString); //inheritance
        this.howLongDuration = howLongDuration; //set duration
    }

    public Event(String detailsFromtheString, String timeToStart, String timeToEnd){ //takes in 3 inputs
        //inputs : details, start and end time
        //this(;);
        this();
        this.timeToEnd = timeToEnd; //ending
        this.timeToStart = timeToStart; //statring

    }
    
    public char forIconGetWhatIsTaskType(){ //get the specofied icon
        return 'E'; //return following format
    }

    @Override
    public String toString(){ //string to string method
        return String.format("[%c]%s (at: %s)", this.forIconGetWhatIsTaskType(),
                super.toString(), this.howLongDuration);

    }
}