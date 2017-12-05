package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.*;
import main.bg.softuni.contracts.ContentComparer;
import main.bg.softuni.exceptions.InvalidCommandException;
import main.bg.softuni.contracts.Executable;


@Alias("cmp")
public class CompareFilesCommand extends Command implements Executable {

    @Inject
    private ContentComparer tester;

    public CompareFilesCommand(String line, String[] data) {
        super(line, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3) {
            throw new InvalidCommandException(this.getLine());
        }
        this.tester.compareContent(data[1], data[2]);
    }
}
