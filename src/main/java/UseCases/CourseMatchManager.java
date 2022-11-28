package UseCases;

import Entities.Course;
import Entities.Student;

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
        for(String course: enrolledCourse){
            try {
                ArrayList<String> studentIDs = cb.getCourse(course).getEnrolledStudentID();
                ArrayList<Student> students = new ArrayList<>();
                for (String s: studentIDs){
                    students.add((Student) ub.getUserByID(s));
                }
                sameSessionStudents.addAll(students);
            } catch (IOException e) {
                System.out.println("CourseMatchManager.java getSameSessionList error probably related to cb.getCourse()");
                throw new RuntimeException(e);
            }
        }
        return sameSessionStudents;
    }

    /**
     * Helper method, stay private.
     */
    private HashMap<Student, Integer> getSameSessionMap(Student student){
        ArrayList<Student> sameSessionStudents = getSameSessionList(student);
        HashMap<Student, Integer> potentialStudents = new HashMap<>();
        for(Student s: sameSessionStudents){
            Integer rank;
            if(potentialStudents.containsKey(s)){
                rank = potentialStudents.get(s);
                potentialStudents.replace(s, rank, rank + 1);
            }
            else {
                rank = 1;
                potentialStudents.put(s, rank);
            }
        }
        return potentialStudents;
    }

    /**
     * get an arraylist of student that are enrolled in "numOfCommon" same sessions as "student".
     * @param numOfCommon number of common sessions
     * @return an arraylist of student
     */
    public ArrayList<Student> getSameSessionStudents(Student student, int numOfCommon){
        ArrayList<Student> sameSessionStuByNum = new ArrayList<>();
        HashMap<Student, Integer> potentialStudents = getSameSessionMap(student);
        for(Student s: potentialStudents.keySet()){
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
    public ArrayList<Student> getTopSameSessionStudents(Student student, int numOfCommon, int topNum){
        ArrayList<Student> topSameSessionStu = getSameSessionStudents(student, numOfCommon);
        if(topNum < topSameSessionStu.size()){
            topSameSessionStu = (ArrayList<Student>) topSameSessionStu.subList(0, topNum);
        }
        return topSameSessionStu;
    }
}
