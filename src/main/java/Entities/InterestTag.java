package Entities;
//TODO: to be implemented in stage 2. Need tagDataBase.
//Tags for of interests. We will decide on a list of interest tags for users to choose from. Students will also be able
// to see others who have the same tag selected.
//e.g. Classical Music, Sci-fi novels, Video Games, etc.
public class InterestTag {
    private String name;

    public InterestTag(String n){
        this.name = n;
    }

    public String getName(){return name;}
}