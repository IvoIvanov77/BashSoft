package bg.softuni.io.commands;

import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.IOManager;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

public class PrintOrderedStudentsCommand extends Command {

    public PrintOrderedStudentsCommand(String line, String[] data, IOManager ioManager, Tester tester,
                                       DownloadManager downloadManager, StudentsRepository studentsRepository) {
        super(line, data, ioManager, tester, downloadManager, studentsRepository);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3 && data.length != 4) {
            throw new InvalidCommandException(this.getLine());
        }
        if (data.length == 3) {
            this.getStudentsRepository().orderAndTake(data[1], data[2]);
        }
        if (data.length == 4) {
            this.getStudentsRepository().orderAndTake(data[1], data[2], Integer.parseInt(data[3]));
        }
    }
}
