package Entities;

/**
 * Implements labels
 */
public class Label {
    String name;

    /**
     * Constructs a label
     * @param n name of label
     */
    public Label(String n){
        this.name = n;
    }

    /**
     * @return name of a label
     */
    public String getName() {
        return name;
    }
}
