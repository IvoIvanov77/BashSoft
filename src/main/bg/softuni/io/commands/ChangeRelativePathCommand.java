package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.*;
import main.bg.softuni.exceptions.InvalidCommandException;
import main.bg.softuni.contracts.DirectoryManager;
import main.bg.softuni.contracts.Executable;

@Alias("changeDirRel")
public class ChangeRelativePathCommand extends Command implements Executable {

    @Inject
    private DirectoryManager ioManager;

    public ChangeRelativePathCommand(String line, String[] data) {
        super(line, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidCommandException(this.getLine());
        }
        this.ioManager.changeCurrentDirRelativePath(data[1]);
    }
}
