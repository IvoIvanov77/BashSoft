package bg.softuni.io.commands;

import bg.softuni.annotations.Alias;
import bg.softuni.annotations.Inject;
import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

@Alias("downloadAsync")
public class DownloadAsynchCommand extends Command implements Executable {

    @Inject
    private AsynchDownloader downloadManager;

    public DownloadAsynchCommand(String line, String[] data) {
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
