package bg.softuni.io.commands;

import bg.softuni.annotations.Alias;
import bg.softuni.annotations.Inject;
import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

@Alias("ls")
public class TraverseFoldersCommand extends Command implements Executable {

    @Inject
    private DirectoryManager ioManager;

    public TraverseFoldersCommand(String line, String[] data) {
        super(line, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 1 && data.length != 2) {
            throw new InvalidCommandException(this.getLine());
        }

        if (data.length == 1) {
            this.ioManager.traverseDirectory(0);
        }

        if (data.length == 2) {
            this.ioManager.traverseDirectory(Integer.valueOf(data[1]));
        }

    }
}
