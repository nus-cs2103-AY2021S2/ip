package level6;

import level6.exception.exceptionsForLevel6;


public class Todo extends Task{ //similar to prev
    //simpler

    public Todo(String detailFromTask) throws exceptionsForLevel6{
        if (detailFromTask.isBlank()){ //if nothinf
            throw new exceptionsForLevel6(); //throw exception to deal with it
        }
        this.detailsOfTheMessage = detailFromTask; //else get details
    }

    @Override
     public char forIconGetWhatIsTaskType(){
        return 'T'; //follow the specification
    }
    
    @Override
    public String toString(){ //string to string method
        return String.format("[%c]%s", this.forIconGetWhatIsTaskType(), super.toString());
    }
}