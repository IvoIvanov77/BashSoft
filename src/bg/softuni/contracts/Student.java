package bg.softuni.contracts;

import java.util.Map;

public interface Student {

    String getUserName();

    Map<String, Course> getEnrolledCourses();

    Map<String, Double> getMarksByCourse();

    void enrollInCourse(Course course);

    void setMarkOnCourse(String courseName, int... scores);
}
