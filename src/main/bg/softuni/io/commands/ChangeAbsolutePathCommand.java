package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.*;
import main.bg.softuni.contracts.Executable;
import main.bg.softuni.exceptions.InvalidCommandException;
import main.bg.softuni.contracts.DirectoryManager;

@Alias("changeDirAbs")
public class ChangeAbsolutePathCommand extends Command implements Executable {

    @Inject
    private DirectoryManager ioManager;


    public ChangeAbsolutePathCommand(String line, String[] data) {
        super(line, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidCommandException(this.getLine());
        }
        this.ioManager.changeCurrentDirAbsolute(data[1]);
    }
}
