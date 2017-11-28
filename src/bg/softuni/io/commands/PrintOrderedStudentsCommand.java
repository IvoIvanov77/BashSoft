package bg.softuni.io.commands;

import bg.softuni.annotations.Alias;
import bg.softuni.annotations.Inject;
import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

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
