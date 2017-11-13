package bg.softuni.io.commands;

import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.IOManager;
import bg.softuni.io.OutputWriter;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

public class GetHelpCommand extends Command {
    public GetHelpCommand(String line, String[] data, IOManager ioManager, Tester tester,
                          DownloadManager downloadManager, StudentsRepository studentsRepository) {
        super(line, data, ioManager, tester, downloadManager, studentsRepository);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 1) {
            throw new InvalidCommandException(this.getLine());
        }

        displayHelp();

    }

    private  void displayHelp() {
        String helpBuilder = "make directory - mkdir nameOfFolder" +
                System.lineSeparator() +
                "traverse directory - ls depth" +
                System.lineSeparator() +
                "comparing files - cmp absolutePath1 absolutePath2" +
                System.lineSeparator() +
                "change directory - cdRel relativePath or \"..\" for level up" +
                System.lineSeparator() +
                "change directory - cdAbs absolutePath" +
                System.lineSeparator() +
                "read students data base - readDb fileName" +
                System.lineSeparator() +
                "filter students - filter {courseName} excellent/average/poor take 2/5/all" +
                System.lineSeparator() +
                "order students - order {courseName} ascending/descending take 20/10/all" +
                System.lineSeparator() +
                "download file - download URL (saved in current directory)" +
                System.lineSeparator() +
                "download file on new thread - downloadAsynch URL (saved in the current directory)" +
                System.lineSeparator() +
                "get help – help" +
                System.lineSeparator();
        OutputWriter.writeMessage(helpBuilder);
        OutputWriter.writeEmptyLine();
    }
}
