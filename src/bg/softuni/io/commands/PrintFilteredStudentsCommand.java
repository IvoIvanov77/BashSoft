package bg.softuni.io.commands;

import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

public class PrintFilteredStudentsCommand extends Command implements Executable {

    public PrintFilteredStudentsCommand(String line, String[] data, DirectoryManager ioManager, ContentComparer tester,
                                        AsynchDownloader downloadManager, Database studentsRepository) {
        super(line, data, ioManager, tester, downloadManager, studentsRepository);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3 && data.length != 4) {
            throw new InvalidCommandException(this.getLine());
        }
        if (data.length == 3) {
            this.getStudentsRepository().filterAndTake(data[1], data[2]);
        }
        if (data.length == 4) {
            this.getStudentsRepository().filterAndTake(data[1], data[2], Integer.parseInt(data[3]));
        }
    }


}
