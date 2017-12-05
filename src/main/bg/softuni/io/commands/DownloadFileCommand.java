package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.*;
import main.bg.softuni.exceptions.InvalidCommandException;
import main.bg.softuni.contracts.AsynchDownloader;
import main.bg.softuni.contracts.Executable;

@Alias("download")
public class DownloadFileCommand extends Command implements Executable {

    @Inject
    private AsynchDownloader downloadManager;

    public DownloadFileCommand(String line, String[] data) {
        super(line, data);
    }


    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidCommandException(this.getLine());
        }
        this.downloadManager.downloadOnNewThread(data[1]);
    }
}
