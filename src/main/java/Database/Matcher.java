package Database;

import Sessions.Course;
import Users.Student;

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
        /**
         * Helper method, stay private.
         * @return a list of students that has same session as "student". Duplicates expected.
         */
        ArrayList<Student> sameSessionStudents = new ArrayList<>();
        ArrayList<Course> enrolledCourse = student.getEnrolledCourses();
        for(Course course: enrolledCourse){
            try {
                ArrayList<Student> studentsEnrolled = cb.getCourse(course.getCourseCode()).getEnrolledStudents();
                sameSessionStudents.addAll(studentsEnrolled);
            } catch (IOException e) {
                System.out.println("Matcher.java getSameSessionList error probably related to cb.getCourse()");
                throw new RuntimeException(e);
            }
        }
        return sameSessionStudents;
    }

    private HashMap<Student, Integer> getSameSessionMap(Student student){
        /**
         * Helper method, stay private.
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
                rank = 1;
                potentialStudents.put(s, rank);
            }
        }
        return potentialStudents;
    }

    public ArrayList<Student> getSameSessionStudents(Student student, int numOfCommon){
        /**
         * get an arraylist of student that are enrolled in "numOfCommon" same sessions as "student".
         * @param numOfCommon number of common sessions
         * @return an arraylist of student
         */
        ArrayList<Student> sameSessionStuByNum = new ArrayList<>();
        HashMap<Student, Integer> potentialStudents = getSameSessionMap(student);
        for(Student s: potentialStudents.keySet()){
            if(potentialStudents.get(s).equals(numOfCommon)){
                sameSessionStuByNum.add(s);
            }
        }
        return sameSessionStuByNum;
    }

    public ArrayList<Student> getTopSameSessionStudents(Student student, int numOfCommon, int topNum){
        /**
         * get an arraylist of the top "topNum" of students that are enrolled in "numOfCommon" same sessions as "student".
         * @param numOfCommon number of common sessions
         * @param topNum number of student of the returned arraylist.
         * @return an arraylist of student
         */
        ArrayList<Student> topSameSessionStu = getSameSessionStudents(student, numOfCommon);
        if(topNum < topSameSessionStu.size()){
            topSameSessionStu = (ArrayList<Student>) topSameSessionStu.subList(0, topNum);
        }
        return topSameSessionStu;
    }

    //TODO in Stage2: getSameTagStudents(); getTopSameTagStudents();
}
