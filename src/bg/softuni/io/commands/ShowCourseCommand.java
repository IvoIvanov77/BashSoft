package bg.softuni.io.commands;

import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

public class ShowCourseCommand extends Command implements Executable {

    public ShowCourseCommand(String line, String[] data, DirectoryManager ioManager, ContentComparer tester,
                             AsynchDownloader downloadManager, Database studentsRepository) {
        super(line, data, ioManager, tester, downloadManager, studentsRepository);

    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2 && data.length != 3) {
            throw new InvalidCommandException(this.getLine());

        }
        if (data.length == 2) {
            this.getStudentsRepository().getStudentsByCourse(data[1]);
        }
        if (data.length == 3) {
            this.getStudentsRepository().getStudentMarksInCourse(data[1], data[2]);
        }
    }
}
