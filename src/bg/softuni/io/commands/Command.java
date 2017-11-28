package bg.softuni.io.commands;

import bg.softuni.contracts.*;
import bg.softuni.exceptions.InvalidCommandException;

public abstract class Command implements Executable{

    private String line;
    private String[] data;


    public Command(String line, String[] data) {
        this.setLine(line);
        this.setData(data);

    }

    public abstract void execute() throws Exception;


    protected String getLine() {
        return line;
    }

    private void setLine(String line) {
        if (line == null || line.equals("")) {
            throw new InvalidCommandException(line);
        }
        this.line = line;
    }

    protected String[] getData() {
        return data;
    }

    private void setData(String[] data) {
        if (data == null || data.length < 1) {
            throw new InvalidCommandException(line);
        }
        this.data = data;
    }
}
