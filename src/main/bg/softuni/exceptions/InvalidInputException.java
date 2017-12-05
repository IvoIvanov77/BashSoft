package main.bg.softuni.exceptions;

public class InvalidInputException extends  RuntimeException {

    public InvalidInputException(String line) {
        super(String.format("The command %s is invalid.", line));
    }
}
