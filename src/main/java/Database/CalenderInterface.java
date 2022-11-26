package Database;


import java.util.ArrayList;

public interface CalenderInterface {
    int getLength(String calendar_raw);
    ArrayList<String> getCourseInfo(String calendar_raw, int num);


}
