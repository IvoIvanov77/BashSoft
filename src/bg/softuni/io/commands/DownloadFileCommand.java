package bg.softuni.io.commands;

import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

public class DownloadFileCommand extends Command implements Executable {

    public DownloadFileCommand(String line, String[] data, DirectoryManager ioManager, ContentComparer tester,
                               AsynchDownloader downloadManager, Database studentsRepository) {
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
