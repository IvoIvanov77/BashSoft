package bg.softuni.io.commands;

import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

public abstract class Command implements Executable{

    private String line;
    private String[] data;
    private DirectoryManager ioManager;
    private ContentComparer tester;
    private AsynchDownloader downloadManager;
    private Database studentsRepository;

    public Command(String line, String[] data, DirectoryManager ioManager, ContentComparer tester,
                   AsynchDownloader downloadManager, Database studentsRepository) {
        this.setLine(line);
        this.setData(data);
        this.setIoManager(ioManager);
        this.setTester(tester);
        this.setDownloadManager(downloadManager);
        this.setStudentsRepository(studentsRepository);
    }

    public abstract void execute() throws Exception;


    protected String getLine() {
        return line;
    }

    private void setLine(String line) {
        if (line == null || line.equals("")) {
            throw new InvalidCommandException(line);
        }
        this.line = line;
    }

    protected String[] getData() {
        return data;
    }

    private void setData(String[] data) {
        if (data == null || data.length < 1) {
            throw new InvalidCommandException(line);
        }
        this.data = data;
    }

    protected DirectoryManager getIoManager() {
        return ioManager;
    }

    private void setIoManager(DirectoryManager ioManager) {
        this.ioManager = ioManager;
    }

    protected ContentComparer getTester() {
        return tester;
    }

    private void setTester(ContentComparer tester) {
        this.tester = tester;
    }

    protected AsynchDownloader getDownloadManager() {
        return downloadManager;
    }

    private void setDownloadManager(AsynchDownloader downloadManager) {
        this.downloadManager = downloadManager;
    }

    protected Database getStudentsRepository() {
        return studentsRepository;
    }

    private void setStudentsRepository(Database studentsRepository) {
        this.studentsRepository = studentsRepository;
    }
}
