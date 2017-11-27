package bg.softuni.repository;

import bg.softuni.contracts.DataFilter;
import bg.softuni.io.OutputWriter;
import bg.softuni.staticData.ExceptionMessages;
import java.util.HashMap;
import java.util.function.Predicate;

public class RepositoryFilter implements DataFilter {

    public void printFilteredStudents(
            HashMap<String, Double> studentsWithMark,
            String filterType,
            Integer numberOfStudents) {

        Predicate<Double> filter = createFilter(filterType);

        if (filter == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_FILTER);
//            OutputWriter.displayException(ExceptionMessages.INVALID_FILTER);
//            return;
        }

        int studentsCount = 0;
        for (String student : studentsWithMark.keySet()) {
            if (studentsCount >= numberOfStudents) {
                break;
            }

            Double mark = studentsWithMark.get(student);

            if (filter.test(mark)) {
                OutputWriter.printStudent(student, mark);
                studentsCount++;
            }
        }
    }

    private Predicate<Double> createFilter(String filterType) {
        switch (filterType) {
            case "excellent":
                return mark -> mark >= 5.0;
            case "average":
                return mark -> 3.5 <= mark && mark < 5.0;
            case "poor":
                return mark -> mark < 3.5;
            default:
                return null;
        }
    }
}

