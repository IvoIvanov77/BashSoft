package bg.softuni.io.commands;

import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

public class ChangeRelativePathCommand extends Command implements Executable {

    public ChangeRelativePathCommand(String line, String[] data, DirectoryManager ioManager, ContentComparer tester,
                                     AsynchDownloader downloadManager, Database studentsRepository) {
        super(line, data, ioManager, tester, downloadManager, studentsRepository);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidCommandException(this.getLine());
        }
        this.getIoManager().changeCurrentDirRelativePath(data[1]);
    }
}
