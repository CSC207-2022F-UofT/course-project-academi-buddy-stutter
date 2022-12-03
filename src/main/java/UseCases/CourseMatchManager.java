package UseCases;

import Entities.Student;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseMatchManager {

    private CourseDataManager cb;
    private UserDataManager ub;

    public CourseMatchManager(CourseDataManager courseDatabase, UserDataManager userDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;
    }

    /**
     * Helper method, stay private.
     * @return a list of students that has same session as "student". Duplicates expected.
     */
    private ArrayList<Student> getSameSessionList(Student student){
        ArrayList<Student> sameSessionStudents = new ArrayList<>();
        ArrayList<String> enrolledCourse = student.getEnrolledCourseCodes();
<<<<<<< HEAD
        System.out.println(enrolledCourse);
        for(String course: enrolledCourse){
            try {
                System.out.println(course);
                ArrayList<String> studentIDs = cb.getCourse(course, "LEC").getEnrolledIDList();
=======
        for(String course: enrolledCourse){
            try {
                ArrayList<String> studentIDs = cb.getCourse(course).getEnrolledStudentID();
>>>>>>> cf8a37a (Fixed an infinite loop in database)
                ArrayList<Student> students = new ArrayList<>();
                for (String s: studentIDs){
                    students.add((Student) ub.getUserByID(s));
                }
                sameSessionStudents.addAll(students);
            } catch (IOException e) {
                System.out.println("CourseMatchManager.java getSameSessionList error probably related to cb.getLecCourse()");
                throw new RuntimeException(e);
            }
        }
        return sameSessionStudents;
    }

    /**
     * Helper method, stay private.
     */
    private HashMap<String, Integer> getSameSessionMap(Student student){
        ArrayList<Student> sameSessionStudents = getSameSessionList(student);
        HashMap<String, Integer> potentialStudents = new HashMap<>();
        for(Student s: sameSessionStudents){
            Integer rank;
            if(potentialStudents.containsKey(s.getUserID())){
                rank = potentialStudents.get(s.getUserID());
                potentialStudents.replace(s.getUserID(), rank, rank + 1);
            }
            else {
                rank = 1;
                potentialStudents.put(s.getUserID(), rank);
            }
        }
        return potentialStudents;
    }

    /**
     * get an arraylist of student that are enrolled in "numOfCommon" same sessions as "student".
     * @param numOfCommon number of common sessions
     * @return an arraylist of student
     */
    public ArrayList<String> getSameSessionStudents(Student student, int numOfCommon){
        ArrayList<String> sameSessionStuByNum = new ArrayList<>();
        HashMap<String, Integer> potentialStudents = getSameSessionMap(student);
        for(String s: potentialStudents.keySet()){
            if(potentialStudents.get(s).equals(numOfCommon)){
                sameSessionStuByNum.add(s);
            }
        }
        return sameSessionStuByNum;
    }

    /**
     * get an arraylist of the top "topNum" of students that are enrolled in "numOfCommon" same sessions as "student".
     * @param numOfCommon number of common sessions
     * @param topNum number of student of the returned arraylist.
     * @return an arraylist of student
     */
    public ArrayList<String> getTopSameSessionStudents(Student student, int numOfCommon, int topNum){
        ArrayList<String> sameSessionStu = getSameSessionStudents(student, numOfCommon);
        ArrayList<String> topSameSessionStu = new ArrayList<>();
        System.out.println(sameSessionStu.size());
        if(topNum < sameSessionStu.size()){
            for(int i = 0; i < topNum; i++){
                topSameSessionStu.add(sameSessionStu.get(i));
            }
        }
        for(String s: topSameSessionStu){
            System.out.println(s);
        }
        return topSameSessionStu;
    }
}
