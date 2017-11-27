package bg.softuni.io;

import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.commands.*;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

import java.io.IOException;

public class CommandInterpreter implements Interpreter {

    private DirectoryManager ioManager;
    private ContentComparer tester;
    private AsynchDownloader downloadManager;
    private Database studentsRepository;
//    private RepositoryFilter repositoryFilter;
//    private RepositorySorter repositorySorter;

    public CommandInterpreter(DirectoryManager ioManager, ContentComparer tester,
                              AsynchDownloader downloadManager,
                              Database studentsRepository) {
        this.ioManager = ioManager;
        this.tester = tester;
        this.downloadManager = downloadManager;
        this.studentsRepository = studentsRepository;
    }

    public void interpretCommand(String input) throws IOException {
        String[] data = input.split("\\s+");
        String commandName = data[0].toLowerCase();
        try {
            Executable command = parseCommand(input, data, commandName);
            command.execute();
        } catch (Throwable t) {
            OutputWriter.displayException(t.getMessage());
        }

    }

    private Executable parseCommand(String line, String[] data, String command) throws IOException {
        switch (command) {
            case "mkdir":
                return new MakeDirectoryCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "Is":
                return new TraverseFoldersCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "cmp":
                return new CompareFilesCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "changeDirRel":
                return new ChangeRelativePathCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "changeDirAbs":
                return new ChangeAbsolutePathCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "readDb":
                return new ReadDatabaseCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "download":
                return new DownloadFileCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "downloadAsync":
                return new DownloadAsynchCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "open":
                return new OpenFileCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "help":
                return new GetHelpCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "show":
                return new ShowCourseCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "filter":
                return new PrintFilteredStudentsCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "order":
                return new PrintOrderedStudentsCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            case "dropdb":
                return new DropDatabaseCommand(line, data, this.ioManager, this.tester, this.downloadManager, this.studentsRepository);
            default:
                throw new InvalidInputException(line);
        }
    }


}
