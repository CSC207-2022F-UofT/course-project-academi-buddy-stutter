package Database;

import Sessions.Course;
import Users.Student;

interface iCourseDatabase {

    void getData();

    void refreshData();

    void addCourse(Course course);

    boolean addStudentToCourse(Course course, Student student);

    boolean removeStudentFromCourse(Course course, Student student);

    Course getCourseByCourseCode(UserDatabase ud, String courseCode);


}
