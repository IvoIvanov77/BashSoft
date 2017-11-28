package bg.softuni.io.commands;

import bg.softuni.annotations.Alias;
import bg.softuni.annotations.Inject;
import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;


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
