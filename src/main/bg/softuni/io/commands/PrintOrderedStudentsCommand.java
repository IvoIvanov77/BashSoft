package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.*;
import main.bg.softuni.contracts.Database;
import main.bg.softuni.exceptions.InvalidCommandException;
import main.bg.softuni.contracts.Executable;

@Alias("order")
public class PrintOrderedStudentsCommand extends Command implements Executable {

    @Inject
    private Database studentsRepository;

    public PrintOrderedStudentsCommand(String line, String[] data) {
        super(line, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3 && data.length != 4) {
            throw new InvalidCommandException(this.getLine());
        }
        if (data.length == 3) {
            this.studentsRepository.orderAndTake(data[1], data[2]);
        }
        if (data.length == 4) {
            this.studentsRepository.orderAndTake(data[1], data[2], Integer.parseInt(data[3]));
        }
    }
}
