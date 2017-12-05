package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.*;
import main.bg.softuni.contracts.Database;
import main.bg.softuni.contracts.Executable;
import main.bg.softuni.exceptions.InvalidCommandException;

@Alias("show")
public class ShowCourseCommand extends Command implements Executable {

    @Inject
    private Database studentsRepository;

    public ShowCourseCommand(String line, String[] data) {
        super(line, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2 && data.length != 3) {
            throw new InvalidCommandException(this.getLine());

        }
        if (data.length == 2) {
            this.studentsRepository.getStudentsByCourse(data[1]);
        }
        if (data.length == 3) {
            this.studentsRepository.getStudentMarksInCourse(data[1], data[2]);
        }
    }
}
