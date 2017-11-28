package bg.softuni.io.commands;

import bg.softuni.annotations.Alias;
import bg.softuni.annotations.Inject;
import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;
import bg.softuni.io.OutputWriter;

@Alias("dropdb")
public class DropDatabaseCommand extends Command implements Executable {

    @Inject
    private Database studentsRepository;

    public DropDatabaseCommand(String line, String[] data) {
        super(line, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 1) {
            throw new InvalidCommandException(this.getLine());
        }
        this.studentsRepository.unloadData();
        OutputWriter.writeMessageOnNewLine("Database dropped.");
    }
}
