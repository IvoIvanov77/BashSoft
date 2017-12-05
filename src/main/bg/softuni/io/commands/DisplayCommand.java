package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.*;
import main.bg.softuni.contracts.Course;
import main.bg.softuni.contracts.Database;
import main.bg.softuni.contracts.Executable;
import main.bg.softuni.contracts.Student;
import main.bg.softuni.dataStructures.SimpleSortedList;
import main.bg.softuni.exceptions.InvalidInputException;
import main.bg.softuni.io.OutputWriter;

import java.util.Comparator;

@Alias("display")
public class DisplayCommand extends  Command implements Executable {

    @Inject
    private Database studentsRepository;

    public DisplayCommand(String line, String[] data) {
        super(line, data);
    }


    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3) {
            throw new InvalidInputException(this.getLine());
        }

        String entityToDisplay = data[1];
        String sortType = data[2];
        if (entityToDisplay.equalsIgnoreCase("students")) {
            Comparator<Student> studentComparator =
                    this.createStudentComparator(sortType);
            SimpleSortedList<Student> list =
                    this.studentsRepository.getAllStudentsSorted(studentComparator);
            OutputWriter.writeMessageOnNewLine(
                    list.joinWith(System.lineSeparator()));
        } else if (entityToDisplay.equalsIgnoreCase("courses")) {
            Comparator<Course> courseComparator =
                    this.createCourseComparator(sortType);
            SimpleSortedList<Course> list =
                    this.studentsRepository.getAllCoursesSorted(courseComparator);
            OutputWriter.writeMessageOnNewLine(
                    list.joinWith(System.lineSeparator()));
        } else {
            throw new InvalidInputException(this.getLine());
        }

    }

    private Comparator<Course> createCourseComparator(String sortType) {
        if (sortType.equalsIgnoreCase("ascending")) {
            return (Comparable::compareTo);
        } else if (sortType.equalsIgnoreCase("descending")) {
            return Comparator.reverseOrder();
        } else {
            throw new InvalidInputException(this.getLine());
        }
    }

    private Comparator<Student> createStudentComparator(String sortType) {
        if (sortType.equalsIgnoreCase("ascending")) {
            return Comparable::compareTo;
        } else if (sortType.equalsIgnoreCase("descending")) {
            return Comparator.reverseOrder();
        } else {
            throw new InvalidInputException(this.getLine());
        }
    }
}
