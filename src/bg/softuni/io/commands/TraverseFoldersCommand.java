package bg.softuni.io.commands;

import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

public class TraverseFoldersCommand extends Command implements Executable {


    public TraverseFoldersCommand(String line, String[] data, DirectoryManager ioManager, ContentComparer tester,
                                  AsynchDownloader downloadManager, Database studentsRepository) {
        super(line, data, ioManager, tester, downloadManager, studentsRepository);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 1 && data.length != 2) {
            throw new InvalidCommandException(this.getLine());
        }

        if (data.length == 1) {
            this.getIoManager().traverseDirectory(0);
        }

        if (data.length == 2) {
            this.getIoManager().traverseDirectory(Integer.valueOf(data[1]));
        }

    }
}
