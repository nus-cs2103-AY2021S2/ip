package duke.task;

import duke.exception.EmptyArgumentException;

import java.util.Locale;

public abstract class Task {
    private final String description;
    private boolean isDone; //TODO: Figure out if I can restrict access

    public Task(String description) throws EmptyArgumentException {
        description = description.trim();
        if (description.isEmpty()){
            throw new EmptyArgumentException();
        }
        this.description = description;
        this.isDone = false;
    }

    public void setDone(){
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "*" : " "); //Don't use unicode, cause it can't test properly
    }

    @Override
    public String toString() {
        return "["+ this.getStatusIcon()+"]: " +
                description;
    }

    public abstract String toFileString();

    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    String toBaseFileString(){
        return (isDone ? "1" : "0") + "," + description.length() + "," + description;
    }

    public boolean containsSearch(String search){
        String targetString = description;
        boolean isCaseSensitive = search.toLowerCase().contains(search);
        if (isCaseSensitive){
            targetString = targetString.toLowerCase();
        }
        if (search.contains(" ")){//Literal multi word matching
            return targetString.contains(search);
        } else {//Smart per word start matching
            String[] words = targetString.split(" ");
            for(String word: words){
                String subWord = word.substring(0, search.length());
                if (subWord.equals(search)){
                    return true;
                }
            }
            return false;
        }
    }
}