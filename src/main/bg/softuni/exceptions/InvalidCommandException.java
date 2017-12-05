package main.bg.softuni.exceptions;

public class InvalidCommandException extends RuntimeException{

    public InvalidCommandException(String command) {
        super(String.format("The command '%s' is invalid.", command));
    }
}
