package main.bg.softuni.contracts;

import main.bg.softuni.dataStructures.SimpleSortedList;

import java.util.Comparator;

public interface Requester{

    void getStudentMarksInCourse(String course, String student);

    void getStudentsByCourse(String course);

    SimpleSortedList<Course>  getAllCoursesSorted(Comparator<Course> comparator);

    SimpleSortedList<Student>  getAllStudentsSorted(Comparator<Student> comparator);
}
