package bg.softuni.io.commands;

import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;


public class CompareFilesCommand extends Command implements Executable {

    public CompareFilesCommand(String line, String[] data, DirectoryManager ioManager, ContentComparer tester,
                               AsynchDownloader downloadManager, Database studentsRepository) {
        super(line, data, ioManager, tester, downloadManager, studentsRepository);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3) {
            throw new InvalidCommandException(this.getLine());
        }
        this.getTester().compareContent(data[1], data[2]);
    }
}
