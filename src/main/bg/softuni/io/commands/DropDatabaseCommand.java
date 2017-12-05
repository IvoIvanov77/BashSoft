package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.*;
import main.bg.softuni.contracts.Database;
import main.bg.softuni.exceptions.InvalidCommandException;
import main.bg.softuni.io.OutputWriter;
import main.bg.softuni.contracts.Executable;

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
