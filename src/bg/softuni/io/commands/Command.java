package bg.softuni.io.commands;

import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.IOManager;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

public abstract class Command {

    private String line;
    private String[] data;
    private IOManager ioManager;
    private Tester tester;
    private DownloadManager downloadManager;
    private StudentsRepository studentsRepository;

    public Command(String line, String[] data, IOManager ioManager, Tester tester,
                   DownloadManager downloadManager, StudentsRepository studentsRepository) {
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

    protected IOManager getIoManager() {
        return ioManager;
    }

    private void setIoManager(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    protected Tester getTester() {
        return tester;
    }

    private void setTester(Tester tester) {
        this.tester = tester;
    }

    protected DownloadManager getDownloadManager() {
        return downloadManager;
    }

    private void setDownloadManager(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }

    protected StudentsRepository getStudentsRepository() {
        return studentsRepository;
    }

    private void setStudentsRepository(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }
}
