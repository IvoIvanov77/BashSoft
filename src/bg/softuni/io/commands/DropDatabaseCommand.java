package bg.softuni.io.commands;

import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.OutputWriter;

public class DropDatabaseCommand extends Command implements Executable {

    public DropDatabaseCommand(String line, String[] data, DirectoryManager ioManager, ContentComparer tester,
                               AsynchDownloader downloadManager, Database studentsRepository) {
        super(line, data, ioManager, tester, downloadManager, studentsRepository);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 1) {
            throw new InvalidCommandException(this.getLine());
        }
        this.getStudentsRepository().unloadData();
        OutputWriter.writeMessageOnNewLine("Database dropped.");
    }
}
