package Firebase;

import Sessions.Course;
import Users.Student;
import Users.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Matcher {

    private CourseDatabase cb;
    private UserDatabase ub;

    public Matcher(CourseDatabase courseDatabase, UserDatabase userDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;
    }

    private ArrayList<Student> getSameSessionList(Student student){
        /*
            Helper method, stay private.
            returns list of students that has same session as "student". Duplicates expected.
         */
        ArrayList<Student> sameSessionStudents = new ArrayList<>();
        ArrayList<Course> enrolledCourse = student.getEnrolledCourses();
        for(Course course: enrolledCourse){
            try {
                ArrayList<Student> studentsEnrolled = cb.getCourse(ub, course.getCourseCode()).getEnrolledStudents();
                for(Student s: studentsEnrolled){
                    sameSessionStudents.add(s);
                }
            } catch (IOException e) {
                System.out.println("Matcher.java getSameSessionList error probably related to cb.getCourse()");
                throw new RuntimeException(e);
            }
        }
        return sameSessionStudents;
    }

    private HashMap<Student, Integer> getSameSessionMap(Student student){
        /*
            Helper method, stay private.
         */
        ArrayList<Student> sameSessionStudents = getSameSessionList(student);
        HashMap<Student, Integer> potentialStudents = new HashMap<>();
        for(Student s: sameSessionStudents){
            Integer rank;
            if(potentialStudents.containsKey(s)){
                rank = potentialStudents.get(s);
                potentialStudents.replace(s, rank, rank + 1);
            }
            else {
                rank = Integer.valueOf(1);
                potentialStudents.put(s, rank);
            }
        }
        return potentialStudents;
    }

    public ArrayList<Student> getSameSessionStudents(Student student, int numOfCommon){
        /*
            return an arraylist of student that are enrolled in "numOfCommon" same sessions as "student".
         */
        ArrayList<Student> sameSessionStudentsByNum = new ArrayList<>();
        HashMap<Student, Integer> potentialStudents = getSameSessionMap(student);
        for(Student s: potentialStudents.keySet()){
            if(potentialStudents.get(s) == Integer.valueOf(numOfCommon)){
                sameSessionStudentsByNum.add(s);
            }
        }
        return sameSessionStudentsByNum;
    }
}
