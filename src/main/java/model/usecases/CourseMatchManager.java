package model.usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.entities.Label;
import model.entities.Student;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Use case operations for course matcher.
 */
public class CourseMatchManager extends UseCase{

    /**
     * Initializer
     * @param courseDatabase the course database.
     * @param userDatabase the user database.
     */
    public CourseMatchManager(CourseDataAccess courseDatabase, UserDataAccess userDatabase, TagDataAccess tagDatabase){
        super(courseDatabase, userDatabase, tagDatabase);
    }

    private static final int MAX_SAME_COURSE = 17;

    private static final int RESULT_LIMIT = 5;

    private HashMap<String, Integer> matchCount = new HashMap<>();

    /**
     * Private helper method.
     * @param student to access information from this student.
     */
    private void updateMatchCount(Student student){
        matchCount = new HashMap<>();
        ArrayList<String> enrolledCourse = student.getEnrolledCourseCodes();
        for(String course: enrolledCourse){
            ArrayList<String> studentIDs = cb.getCourse(course, "LEC").getEnrolledIDList();

            for(String id : studentIDs){
                if(!id.equals(student.getUserID())){
                    if(matchCount.containsKey(id)){
                        int currentCount = matchCount.get(id);
                        currentCount ++;
                        matchCount.put(id, currentCount);
                    }else{
                        matchCount.put(id, 1);
                    }
                }

            }
        }
    }

    /**
     * return a ArrayList of matched ids
     * @return ArrayList of matched ids
     */
    private ArrayList<String>[] getMatchedArray(){
        String[] keys = this.matchCount.keySet().toArray(new String[0]);

        ArrayList<String>[] matchedList = new ArrayList[MAX_SAME_COURSE];

    //initialize matched list to be an array of empty arrayLists...
        for(int i = 0; i < matchedList.length; i++){
            matchedList[i] = new ArrayList<>();
        }
        for(String id: keys){
            int count = this.matchCount.get(id);
            matchedList[count].add(id);
        }
        return matchedList;
    }

    /**
     * To get the max common integer value in the matched array.
     * @param matchedArray ArrayList of matched courses.
     * @return the max int value of location in matched array.
     */
    private int maxCommon(ArrayList<String>[] matchedArray){

        int latestEmpty = -1;

        for(int i = 0; i< MAX_SAME_COURSE; i++){
            if(matchedArray[i].isEmpty()){
                latestEmpty = i;
            }
        }

        if(latestEmpty < 0){
            return MAX_SAME_COURSE;
        }else{
            return latestEmpty - 1;
        }
    }

    /**
     * Getting the same session students that are matched.
     * @param student the student that is being matched.
     * @param minNumOfCommon the minimum number of common courses.
     * @return Array list of students that are matched to the student parameter, in descending order by number of common.
     */
    public ArrayList<Student> getTopSameSessionStudents(Student student, int minNumOfCommon) {

        updateMatchCount(student);
        ArrayList<String>[] matchedList = this.getMatchedArray();
        int maxCommonCount = maxCommon(matchedList);

        ArrayList<String>[] targetMatchedList = Arrays.copyOfRange(matchedList, minNumOfCommon, maxCommonCount + 1);
        ArrayUtils.reverse(targetMatchedList);

        ArrayList<String> ids = new ArrayList<>();
        for (ArrayList<String> strings : targetMatchedList) {
            for (String string : strings) {
                if (!string.isEmpty()) {
                    ids.add(string);
                }

            }
        }

        ArrayList<Student> students = new ArrayList<>();
        int idCounts = ids.size();

        if(idCounts > RESULT_LIMIT){
            idCounts = RESULT_LIMIT;
        }

        for(int i = 0; i<idCounts; i++){
            if(!(ids.get(i).equals(student.getUserID()))){

                Student stu = (Student) this.ub.getUserByID(ids.get(i));
                students.add(stu);
            }
        }
        return students;
    }

    /**
     * Filter the match results by selected label.
     * @param matches ArrayList of students that are matched.
     * @param label The label to filter by.
     * @return The ArrayList of matched students that are of the same label.
     */
    public ArrayList<Student> filterByLabel(ArrayList<Student> matches, String label){
        ArrayList<Student> filtered = new ArrayList<>();
        for(Student s: matches){
            ArrayList<String> labelNames = new ArrayList<>();

            for(Label l: s.getLabels()){
                labelNames.add(l.getName());
            }
            if(labelNames.contains(label)){
                filtered.add(s);
            }
        }
        return filtered;
    }




}