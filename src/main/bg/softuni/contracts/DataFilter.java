package main.bg.softuni.contracts;

import java.util.HashMap;

public interface DataFilter {

    void printFilteredStudents(
            HashMap<String, Double> studentsWithMark,
            String filterType,
            Integer numberOfStudents);
}
