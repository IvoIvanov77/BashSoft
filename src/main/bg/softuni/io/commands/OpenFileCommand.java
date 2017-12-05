package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.contracts.*;
import main.bg.softuni.contracts.Executable;
import main.bg.softuni.exceptions.InvalidInputException;
import main.bg.softuni.staticData.SessionData;

import java.awt.*;
import java.io.File;

@Alias("open")
public class OpenFileCommand extends Command implements Executable {


    public OpenFileCommand(String line, String[] data) {
        super(line, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getLine());
        }
        String fullFilePath = SessionData.currentPath + "\\" + data[1];
        File file = new File(fullFilePath);
        Desktop.getDesktop().open(file);
    }


}
