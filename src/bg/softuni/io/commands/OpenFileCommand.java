package bg.softuni.io.commands;

import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.staticData.SessionData;

import java.awt.*;
import java.io.File;

public class OpenFileCommand extends Command implements Executable {

    public OpenFileCommand(String line, String[] data, DirectoryManager ioManager, ContentComparer tester,
                           AsynchDownloader downloadManager, Database studentsRepository) {
        super(line, data, ioManager, tester, downloadManager, studentsRepository);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getLine());
        }
        String fullFilePath = SessionData.currentPath + "\\" + data[1];
        File file = new File(fullFilePath);
        Desktop.getDesktop().open(file);
    }


}
