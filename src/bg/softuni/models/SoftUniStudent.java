package bg.softuni.models;

import bg.softuni.contracts.Course;
import bg.softuni.contracts.Student;
import bg.softuni.exceptions.DuplicateEntryInStructureException;
import bg.softuni.exceptions.InvalidStringException;
import bg.softuni.exceptions.KeyNotFoundException;
import bg.softuni.staticData.ExceptionMessages;

import java.util.*;

public class SoftUniStudent implements Student {

    private String userName;
    private Map<String, Course> enrolledCourses;
    private Map<String, Double> marksByCourse;

    public SoftUniStudent(String userName) {
        this.setUserName(userName);
        this.enrolledCourses = new LinkedHashMap<>();
        this.marksByCourse = new LinkedHashMap<>();
    }

    @Override
    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        if(userName == null || userName.trim().isEmpty()){
            throw new InvalidStringException();
        }
        this.userName = userName;
    }

    @Override
    public Map<String, Course> getEnrolledCourses() {
        return Collections.unmodifiableMap(this.enrolledCourses);
    }


    @Override
    public Map<String, Double> getMarksByCourse() {
        return Collections.unmodifiableMap(this.marksByCourse);
    }



    @Override
    public void enrollInCourse(Course course) {
        if (this.enrolledCourses.containsKey(course.getName())) {
            throw new DuplicateEntryInStructureException(this.userName, course.getName());
        }
        this.enrolledCourses.put(course.getName(), course);
    }

    @Override
    public void setMarkOnCourse(String courseName, int... scores) {
        if (!this.enrolledCourses.containsKey(courseName)) {
            throw new KeyNotFoundException();
        }

        if (SoftUniCourse.NUMBER_OF_TASKS_ON_EXAM < scores.length) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);
//            OutputWriter.displayException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);
//            return;
        }

        double mark = calculateMark(scores);
        this.marksByCourse.put(courseName, mark);
    }

    private double calculateMark(int[] scores) {
        double percentageOfSolvedExam = Arrays.stream(scores).sum() /
                (double) (SoftUniCourse.NUMBER_OF_TASKS_ON_EXAM * SoftUniCourse.MAX_SCORE_ON_EXAM_TASK);
        return percentageOfSolvedExam * 4 + 2;
    }

    @Override
    public int compareTo(Student other) {
        return this.getUserName().compareTo(other.getUserName());
    }

    @Override
    public String toString() {
        return this.getUserName();
    }
}
