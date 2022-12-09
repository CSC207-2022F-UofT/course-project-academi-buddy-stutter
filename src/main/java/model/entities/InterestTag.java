package model.entities;

/**
 * Implements InterestTag that user can choose from
 */
public class InterestTag {
    private final String name;

    /**
     * Constructs an interest tag
     * @param n name of interest tag
     */
    public InterestTag(String n){
        this.name = n;
    }

    /**
     * @return name of an interest tag
     */
    public String getName(){return name;}
}