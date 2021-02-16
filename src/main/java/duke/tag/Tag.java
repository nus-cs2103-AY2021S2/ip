package duke.tag;

import java.util.ArrayList;
import java.util.Iterator;

import duke.util.Tuple;

public class Tag {
    private String title;

    public Tag(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }


    /**
     * Splits an argument into an array of 2 elements, with the 1st element containing arguments
     * and 2nd element containing tags. If the argument contains no tags, it will return an array
     * with only one element.
     *
     * @param arguments the arguments to be split
     * @return String[] of 2 elements if there are tags or 1 element without.
     */
    public static String[] splitTag(String arguments) {
        return arguments.split("(?=#)", 2);
    }

    /**
     * Converts a String containing any number of tags into an ArrayList of Tag objects.
     *
     * @param tagString the String to be processed
     * @return ArrayList containing all the Tags in the String.
     */
    public static ArrayList<Tag> processTags(String tagString) {
        String[] tagArray = tagString.split("#");
        ArrayList<Tag> tags = new ArrayList<>();
        for (int i = 1; i < tagArray.length; i++) {
            tags.add(new Tag(tagArray[i].strip()));
        }
        return tags;
    }

    /**
     * Isolates and processes the tags from the given arguments.
     *
     * @param arguments the String to be processed.
     * @return Tuple containing the remaining arguments as a String and any Tags in an ArrayList.
     */
    public static Tuple<String, ArrayList<Tag>> retrieveTags(String arguments) {
        String[] splitByTag = splitTag(arguments);
        ArrayList<Tag> tags;
        if (splitByTag.length > 1) {
            assert splitByTag.length == 2;
            tags = processTags(splitByTag[1]);
        } else {
            tags = new ArrayList<>();
        }
        return new Tuple<>(splitByTag[0], tags);
    }

    /**
     * Converts an ArrayList of Tags into a string format for storage to a file
     *
     * @param tags the ArrayList to be converted
     * @return a String representing the ArrayList of Tags
     */
    public static String tagListToString(ArrayList<Tag> tags) {
        String result = "";
        Iterator<Tag> iterator = tags.iterator();
        while (iterator.hasNext()) {
            result = result + "#" + iterator.next().getTitle() + " ";
        }
        return result;
    }
}
