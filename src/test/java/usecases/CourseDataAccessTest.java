package usecases;

import database.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import entities.Course;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


class CourseDataAccessTest extends LocalTempDataFactory {

    //FIXME this test is testing on LocalCourseData. Make it so that it tests CloudCourseData.


    ArrayList<?> managers;

    {
        try {
            managers = super.initializeStaticDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    LocalUserData ub = (LocalUserData) managers.get(0);
    LocalCourseData cb = (LocalCourseData) managers.get(1);
    LocalTagData tb = (LocalTagData) managers.get(2);

    @Test
    void getCourseCodeList() throws Exception {

        assertEquals(4, cb.getCourseCodeList().size());
    }

    @Test
    void updateCourse() throws Exception{
        Course course = new Course(
                "001",
                "LEC",
                "808",
                "Chemistry 202",
                "Tuesday",
                "12:00",
                "2003"
        );
        cb.updateCourse(course);
        Course addedCourse = cb.getCourse(course.getCourseCode(), course.getCourseType());
        assertEquals(course.getCourseCode(), addedCourse.getCourseCode());
        assertEquals(course.getCourseType(), addedCourse.getCourseType());
        assertEquals(course.getSessionNumber(), addedCourse.getSessionNumber());
        assertEquals(course.getCourseName(), addedCourse.getCourseName());
        assertEquals(course.getDayOfWeek(), addedCourse.getDayOfWeek());
        assertEquals(course.getStartTime(), addedCourse.getStartTime());
        assertEquals(course.getYear(), addedCourse.getYear());
    }

    @Test
    void addStudent() {

    }

    @Test
    void removeStudent() {
    }

    @Test
    void getCourse() {
    }
}