package bg.softuni.io.commands;

import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.IOManager;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;
public class ShowCourseCommand extends Command {

    public ShowCourseCommand(String line, String[] data, IOManager ioManager, Tester tester,
                             DownloadManager downloadManager, StudentsRepository studentsRepository) {
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
