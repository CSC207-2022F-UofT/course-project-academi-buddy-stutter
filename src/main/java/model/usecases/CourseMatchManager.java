package model.usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.entities.Label;
import model.entities.Student;
import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import java.io.IOException;
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

    private final int maxSameCourses = 17;

    private final int resultLimit = 5;

    private HashMap<String, Integer> matchCount = new HashMap<>();

    private ArrayList<String> matchedID = new ArrayList<>();

    /**
     * Private helper method.
     * @param student to access information from this student.
     */
    private void updateMatchCount(Student student){
        matchCount = new HashMap<>();
        ArrayList<String> enrolledCourse = student.getEnrolledCourseCodes();
        for(String course: enrolledCourse){
            try {
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
            } catch (IOException e) {
                System.out.println("CourseMatchManager.java getSameSessionList error probably related to cb.getLecCourse()");
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * return a ArrayList of matched ids
     * @return ArrayList of matched ids
     */
    private ArrayList<String>[] getMatchedArray(){
        String[] keys = this.matchCount.keySet().toArray(new String[0]);

        ArrayList<String>[] matchedList = new ArrayList[this.maxSameCourses];

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

        for(int i = 0; i<this.maxSameCourses; i++){
            if(matchedArray[i].isEmpty()){
                latestEmpty = i;
            }
        }

        if(latestEmpty < 0){
            return this.maxSameCourses;
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
    private ArrayList<Student> getTopSameSessionStudents(Student student, int minNumOfCommon) throws IOException {

        updateMatchCount(student);
        ArrayList<String>[] matchedList = this.getMatchedArray();
        int maxCommonCount = maxCommon(matchedList);

        ArrayList<String>[] targetMatchedList = Arrays.copyOfRange(matchedList, minNumOfCommon + 1, maxCommonCount + 1);
        ArrayUtils.reverse(targetMatchedList);

        ArrayList<String> ids = new ArrayList<>();
        for(int i = 0; i<targetMatchedList.length; i++){
            for(int j = 0; j < targetMatchedList[i].size(); j++){
                if(!targetMatchedList[i].get(j).isEmpty()){
                    ids.add(targetMatchedList[i].get(j));
                }

            }
        }

        ArrayList<Student> students = new ArrayList<>();
        int idCounts = ids.size();

        if(idCounts > resultLimit){
            idCounts = resultLimit;
        }

        System.out.println(idCounts);
        System.out.println(ids);

        for(int i=0; i<idCounts; i++){

            System.out.println("id = " + ids.get(i));

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
    private ArrayList<Student> filterByLabel(ArrayList<Student> matches, String label){
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

    /**
     * Finds student matches
     * @param studentID id of current student
     * @param min_numCommon minimum number of common sessions
     * @return a list of matched students
     * @throws IOException fails to find matching students
     */
    private ArrayList<Student> getMatches(String studentID, int min_numCommon) throws IOException {
        Student stu = (Student) this.getUserByID(studentID);
        ArrayList<Student> matches = this.getTopSameSessionStudents(stu, min_numCommon);
        return matches;
    }

    /**
     * Find list of users by filtering labels
     * @param allMatches list of all matched students
     * @param label labels we want to have commons with
     * @return a list of users that share common labels
     */
    private ArrayList<Student> getLabeledMatches(ArrayList<Student> allMatches, String label){

        if(!(allMatches.size() == 0)){
            return this.filterByLabel(allMatches, label);
        }else{
            return new ArrayList<>();
        }
    }

    /**
     * Create model for JList depending on label
     * @param studentID id of current student
     * @param label labels we want to have commons with
     * @param numCommon the minimum number of matched courses
     * @return a Jlist model that can be used to up-dated the match results
     */
    public DefaultListModel<String> createModelByLabel(String studentID, String label, int numCommon) throws IOException {
        DefaultListModel<String> matchedStu = new DefaultListModel<>();
        ArrayList<Student> currMatches;
        if(label.equals("None")){
            currMatches = this.getMatches(studentID, numCommon);
        }else{
            currMatches = this.getMatches(studentID, numCommon);
            currMatches = this.getLabeledMatches(currMatches, label);
        }
        matchedID = new ArrayList<>();
        for(Student s: currMatches){
            matchedID.add(s.getUserID());
            matchedStu.addElement(s.getFullName());
        }
        return matchedStu;
    }

    /**
     * @param index helps get the index-th match
     * @return a student id at that index of the JList
     */
    public String getSelectedUserID(int index){
        return this.matchedID.get(index);
    }
}



