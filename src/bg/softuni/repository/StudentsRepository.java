package bg.softuni.repository;

import bg.softuni.contracts.*;
import bg.softuni.dataStructures.SimpleSortedList;
import bg.softuni.exceptions.DataAlreadyInitializedException;
import bg.softuni.exceptions.DataNotInitializedException;
import bg.softuni.io.OutputWriter;
import bg.softuni.models.SoftUniCourse;
import bg.softuni.models.SoftUniStudent;
import bg.softuni.staticData.ExceptionMessages;
import bg.softuni.staticData.SessionData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentsRepository implements Database {

    private boolean isDataInitialized = false;
    private Map<String, Student> students;
    private Map<String, Course> courses;
    private DataFilter filter;
    private DataSorter sorter;

    public StudentsRepository(DataFilter filter, DataSorter sorter) {
        this.filter = filter;
        this.sorter = sorter;
    }

    @Override
    public void loadData(String fileName) throws IOException {
        if (isDataInitialized) {
            throw new DataAlreadyInitializedException();

        }

        this.students = new LinkedHashMap<>();
        this.courses = new LinkedHashMap<>();
        readData(fileName);
    }

    @Override
    public void unloadData()  {
        if (!isDataInitialized) {
            throw new DataNotInitializedException();
        }
        this.students = null;
        this.courses = null;
        this.isDataInitialized = false;
    }

    private void readData(String fileName) throws IOException {
        String regex = "([A-Z][a-zA-Z#\\+]*_[A-Z][a-z]{2}_\\d{4})\\s+([A-Za-z]+\\d{2}_\\d{2,4})\\s([\\s0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        String path = SessionData.currentPath + "\\" + fileName;
        List<String> lines = Files.readAllLines(Paths.get(path));

        for (String line : lines) {
            matcher = pattern.matcher(line);

            if (!line.isEmpty() && matcher.find()) {

                String courseName = matcher.group(1);
                String studentName = matcher.group(2);
                String scoresStr = matcher.group(3);

                try {
                    String[] splitScores = scoresStr.split("\\s+");
                    int[] scores = new int[splitScores.length];

                    for (int i = 0; i < scores.length; i++) {
                        scores[i] = Integer.parseInt(splitScores[i]);
                    }

                    if (Arrays.stream(scores).anyMatch(x -> x > 100 || x < 0)) {
                        OutputWriter.displayException(ExceptionMessages.INVALID_SCORE);
                        continue;
                    }
                    if (scores.length > SoftUniCourse.NUMBER_OF_TASKS_ON_EXAM) {
                        OutputWriter.displayException(ExceptionMessages.INVALID_NUMBER_OF_SCORES);
                        continue;
                    }

                    if (!this.students.containsKey(studentName)) {
                        this.students.put(studentName, new SoftUniStudent(studentName));
                    }
                    if (!this.courses.containsKey(courseName)) {
                        this.courses.put(courseName, new SoftUniCourse(courseName));
                    }

                    Student student = this.students.get(studentName);
                    Course course = this.courses.get(courseName);
                    student.enrollInCourse(course);
                    student.setMarkOnCourse(courseName, scores);
                    course.enrollStudent(student);

                }catch (NumberFormatException nfe) {
                    OutputWriter.displayException(nfe.getMessage());
                }
            }
        }

        isDataInitialized = true;
        OutputWriter.writeMessageOnNewLine("Data read.");
    }


    @Override
    public void getStudentMarksInCourse(String course, String student) {
        if (!isQueryForStudentPossible(course, student)) {
            return;
        }

        Double mark = this.courses.get(course).getStudentsByName().get(student).getMarksByCourse().get(course);
        OutputWriter.printStudent(student, mark);
    }

    @Override
    public void getStudentsByCourse(String course) {
        if (!isQueryForCoursePossible(course)) {
            return;
        }

        OutputWriter.writeMessageOnNewLine(course + ":");
        for (Map.Entry<String, Student> student : this.courses.get(course).getStudentsByName().entrySet()) {
            this.getStudentMarksInCourse(course, student.getKey());
        }
    }

    @Override
    public SimpleSortedList<Course> getAllCoursesSorted(Comparator<Course> comparator) {
        SimpleSortedList<Course> courseSortedList = new SimpleSortedList<>(Course.class, comparator);
        courseSortedList.addAll(this.courses.values());
        return courseSortedList;
    }

    @Override
    public SimpleSortedList<Student> getAllStudentsSorted(Comparator<Student> comparator) {
        SimpleSortedList<Student> studentSortedList = new SimpleSortedList<>(Student.class, comparator);
        studentSortedList.addAll(this.students.values());
        return studentSortedList;
    }

    private boolean isQueryForCoursePossible(String courseName) {
        if (!isDataInitialized) {
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }

        if (!this.courses.containsKey(courseName)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_COURSE);
            return false;
        }

        return true;
    }

    private boolean isQueryForStudentPossible(String courseName, String studentName) {
        if (!isQueryForCoursePossible(courseName)) {
            return false;
        }

        if (!this.courses.get(courseName).getStudentsByName().containsKey(studentName)) {
            OutputWriter.displayException(ExceptionMessages.NON_EXISTING_STUDENT);
            return false;
        }

        return true;
    }

    @Override
    public void filterAndTake(String courseName, String filter) {
        int studentsToTake = this.courses.get(courseName).getStudentsByName().size();
        filterAndTake(courseName, filter, studentsToTake);
    }

    @Override
    public void filterAndTake(String courseName, String filter, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        LinkedHashMap<String, Double> marks = new LinkedHashMap<>();
        for (Map.Entry<String, Student> entry :
                this.courses.get(courseName).getStudentsByName().entrySet()) {
            marks.put(entry.getKey(), entry.getValue()
                    .getMarksByCourse().get(courseName));
        }

        this.filter.printFilteredStudents(marks, filter, studentsToTake);
    }

    @Override
    public void orderAndTake(
            String courseName, String orderType, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        LinkedHashMap<String, Double> marks = new LinkedHashMap<>();
        for (Map.Entry<String, Student> entry :
                this.courses.get(courseName).getStudentsByName().entrySet()) {
            marks.put(entry.getKey(), entry.getValue().getMarksByCourse().get(courseName));
        }

        this.sorter.printSortedStudents(marks, orderType, studentsToTake);
    }

    @Override
    public void orderAndTake(String courseName, String orderType) {
        int studentsToTake = this.courses.get(courseName).getStudentsByName().size();
        this.orderAndTake(courseName, orderType, studentsToTake);
    }
}
