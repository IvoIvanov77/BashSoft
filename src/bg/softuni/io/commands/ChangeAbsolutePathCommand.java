package bg.softuni.io.commands;

import bg.softuni.annotations.Alias;
import bg.softuni.annotations.Inject;
import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

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
