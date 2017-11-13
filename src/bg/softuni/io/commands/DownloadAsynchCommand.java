package bg.softuni.io.commands;

import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.IOManager;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

public class DownloadAsynchCommand extends Command {

    public DownloadAsynchCommand(String line, String[] data, IOManager ioManager, Tester tester,
                                 DownloadManager downloadManager, StudentsRepository studentsRepository) {
        super(line, data, ioManager, tester, downloadManager, studentsRepository);

    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidCommandException(this.getLine());
        }
        this.getDownloadManager().downloadOnNewThread(data[1]);
    }
}
