package bg.softuni.io.commands;

import bg.softuni.annotations.Alias;
import bg.softuni.annotations.Inject;
import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

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
