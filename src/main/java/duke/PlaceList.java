package duke;

import java.util.ArrayList;

import duke.place.Place;

/**
 * ArrayList adapted to Duke.
 */
public class PlaceList {

    private final ArrayList<Place> list;

    public PlaceList() {
        this.list = new ArrayList<>();
    }

    public void addPlace(Place p) {
        this.list.add(p);
    }

    public Place getPlace(int index) {
        return this.list.get(index);
    }

    public int getSize() {
        return this.list.size();
    }

    /**
     * Formats the list to its output format.
     *
     * @return String in correct format.
     */
    public String formatList() {
        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            resultStr.append(list.get(i).toString());
        }
        return resultStr.toString();
    }
}
