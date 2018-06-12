package oop.ex6.validations;

public class UnknownMethodException extends BadFormatException{
    public UnknownMethodException(String line) {
        super(line);
    }
}
