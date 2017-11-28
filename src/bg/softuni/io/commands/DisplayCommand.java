package bg.softuni.io.commands;

import bg.softuni.annotations.Alias;
import bg.softuni.annotations.Inject;
import bg.softuni.contracts.*;
import bg.softuni.dataStructures.SimpleSortedList;
import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.OutputWriter;

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
