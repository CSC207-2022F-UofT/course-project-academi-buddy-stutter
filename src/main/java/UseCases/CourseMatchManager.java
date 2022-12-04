package UseCases;

import Entities.Label;
import Entities.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CourseMatchManager {

    private CourseDataManager cb;
    private UserDataManager ub;

    public CourseMatchManager(CourseDataManager courseDatabase, UserDataManager userDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;
    }

    private int maxSameCourses = 17;

    private int resultLimit = 5;

    private HashMap<String, Integer> matchCount = new HashMap<>();

    /**
     * Helper method, stay private.
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

    private ArrayList<String>[] getMatchedArray(){
        String[] keys = this.matchCount.keySet().toArray(new String[0]);

        ArrayList<String>[] matchedList = new ArrayList[this.maxSameCourses];

//      intialize matched list to be an array of empty arrayLists...
        for(int i = 0; i < matchedList.length; i++){
            matchedList[i] = new ArrayList<>();
        }

        for(String id: keys){
            int count = this.matchCount.get(id);
            matchedList[count].add(id);
        }
        return matchedList;
    }


    private int maxCommon(ArrayList<String>[] matchedArray){

        int latestEmpty = -1;

        for(int i =1; i<this.maxSameCourses; i++){
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


    public ArrayList<Student> getTopSameSessionStudents(Student student, int minNumOfCommon) throws IOException {

        updateMatchCount(student);
        ArrayList<String>[] matchedList = this.getMatchedArray();
        int maxCommonCount = maxCommon(matchedList);

        ArrayList<String>[] targetMatchedList = Arrays.copyOfRange(matchedList, minNumOfCommon, maxCommonCount + 1);

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