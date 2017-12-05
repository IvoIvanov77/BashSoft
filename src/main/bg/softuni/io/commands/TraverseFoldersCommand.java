package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.*;
import main.bg.softuni.contracts.Executable;
import main.bg.softuni.exceptions.InvalidCommandException;
import main.bg.softuni.contracts.DirectoryManager;

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
