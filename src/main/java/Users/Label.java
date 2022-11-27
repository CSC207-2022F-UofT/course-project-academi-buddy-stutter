package Users;

// Labels of status/moods/intents. We will create a list for students to choose from, and display them alongside matched results.
//e.g. Want to collaborate, Want to discuss, Want to review together, etc.
public class Label {
    String name;

    public Label(String n){
        this.name = n;
    }

    public String getName() {
        return name;
    }
}
